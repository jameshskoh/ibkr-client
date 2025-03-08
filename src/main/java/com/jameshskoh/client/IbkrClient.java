package com.jameshskoh.client;

import com.ib.client.*;
import com.jameshskoh.constants.ExchangeRateTicker;
import com.jameshskoh.constants.IndexTicker;
import com.jameshskoh.constants.StockTicker;
import com.jameshskoh.handlers.DatabaseHistoricalDataHandler;
import com.jameshskoh.handlers.HistoricalDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

public class IbkrClient {

  private static final Logger logger = LoggerFactory.getLogger(IbkrClient.class);

  // connection related
  private final AtomicBoolean isConnected = new AtomicBoolean(false);

  // job related
  private final Queue<DataJob> jobQueue;
  private final ConcurrentHashMap<Integer, DataJob> processingJobRecords =
      new ConcurrentHashMap<>();

  // only works when there's only 1 client connecting to IB
  private final AtomicInteger orderId = new AtomicInteger(0);

  // database connection related
  private final Connection connection;

  // other dependencies
  private final IbkrRequest ibkrRequest;
  private final IbkrResponse ibkrResponse;
  private final EReaderSignal readerSignal;
  private final EClientSocket clientSocket;
  private final ExecutorService readerExecutorService;

  public IbkrClient(
      Queue<DataJob> jobQueue, String databaseUrl, String databaseUser, String databasePassword)
      throws SQLException {

    this.jobQueue = jobQueue;
    this.connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);

    IntFunction<DataJob> getJobInfoCallback = processingJobRecords::get;
    IntConsumer removeJobCallback = processingJobRecords::remove;

    HistoricalDataHandler historicalDataHandler =
        new DatabaseHistoricalDataHandler(connection, getJobInfoCallback, removeJobCallback);

    //    HistoricalDataHandler historicalDataHandler =
    //        new LoggerHistoricalDataHandler(getJobInfoCallback, removeJobCallback);

    Runnable setConnectedCallback = () -> isConnected.set(true);

    ibkrRequest = new IbkrRequest();
    ibkrResponse = new IbkrResponse(setConnectedCallback, historicalDataHandler);
    readerSignal = new EJavaSignal();
    clientSocket = new EClientSocket(ibkrResponse, readerSignal);
    readerExecutorService = Executors.newSingleThreadExecutor();
  }

  public boolean isConnected() {
    return isConnected.get();
  }

  public void connectAndListen(String host, int port, int clientId) {
    clientSocket.eConnect(host, port, clientId);

    final EReader reader = new EReader(clientSocket, readerSignal);

    reader.start();

    readerExecutorService.submit(
        () -> {
          while (clientSocket.isConnected()) {
            readerSignal.waitForSignal();
            try {
              reader.processMsgs();
            } catch (Exception e) {
              logger.error("Reader thread failed with exception:", e);
            }
          }
        });
  }

  public void waitForConnectionAndMakeRequests() throws InterruptedException {
    while (!isConnected()) {
      Thread.sleep(100);
    }

    ingestJob(jobQueue);
  }

  private void ingestJob(Queue<DataJob> jobQueue) throws InterruptedException {
    while (!jobQueue.isEmpty()) {
      while (isProcessingJob()) {
        Thread.sleep(15000);
      }

      // critical section?
      int jobId = orderId.incrementAndGet();

      DataJob job = jobQueue.poll();
      processingJobRecords.put(jobId, job);
      makeRequest(jobId, job);
      // critical section?
    }
  }

  private void makeRequest(int jobId, DataJob job) {

    switch (job.ticker()) {
      case ExchangeRateTicker t ->
          ibkrRequest.requestDailyHistoricalExchangeRate(
              jobId, clientSocket, t, job.endPeriod(), job.backPeriodInYears());
      case IndexTicker t ->
          ibkrRequest.requestDailyHistoricalIndexPrice(
              clientSocket, jobId, t, job.endPeriod(), job.backPeriodInYears());
      case StockTicker t ->
          ibkrRequest.requestDailyHistoricalStockPrice(
              clientSocket, jobId, t, job.endPeriod(), job.backPeriodInYears());
    }
  }

  private boolean isProcessingJob() {
    return !processingJobRecords.isEmpty();
  }

  public void waitForCompletionAndDisconnect() throws InterruptedException, SQLException {
    while (!jobQueue.isEmpty() || isProcessingJob()) {
      Thread.sleep(15000);
    }

    clientSocket.eDisconnect();
    readerExecutorService.shutdown();
    connection.close();
  }
}
