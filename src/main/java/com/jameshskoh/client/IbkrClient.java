package com.jameshskoh.client;

import com.ib.client.*;
import com.jameshskoh.constants.ExchangeRateTicker;
import com.jameshskoh.constants.StockTicker;
import com.jameshskoh.handlers.HistoricalDataHandler;
import com.jameshskoh.handlers.LoggerHistoricalDataHandler;

import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class IbkrClient {

  private final AtomicBoolean isConnected = new AtomicBoolean(false);
  private final Queue<DataJob> jobQueue;
  private final ConcurrentHashMap<Integer, DataJob> processingJobRecords;

  /*
   * only works when there's only 1 client connecting to IB, else you have to retrieve orderId from IB every time you send a request
   */
  private final AtomicInteger orderId = new AtomicInteger(0);

  private final HistoricalDataHandler historicalDataHandler;

  private final IbkrRequest ibkrRequest;
  private final IbkrResponse ibkrResponse;
  private final EReaderSignal readerSignal;
  private final EClientSocket clientSocket;
  private final ExecutorService readerExecutorService;

  public IbkrClient(Queue<DataJob> jobQueue) {
    this.jobQueue = jobQueue;
    processingJobRecords = new ConcurrentHashMap<>();

    historicalDataHandler = new LoggerHistoricalDataHandler(this);
    ibkrRequest = new IbkrRequest();
    ibkrResponse = new IbkrResponse(this, historicalDataHandler);
    readerSignal = new EJavaSignal();
    clientSocket = new EClientSocket(ibkrResponse, readerSignal);
    readerExecutorService = Executors.newSingleThreadExecutor();
  }

  public boolean isConnected() {
    return isConnected.get();
  }

  public void setIsConnected() {
    isConnected.set(true);
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
              System.out.println("Exception: " + e.getMessage());
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
        Thread.sleep(100);
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
      case StockTicker t ->
          ibkrRequest.requestDailyHistoricalStockPrice(
              clientSocket, jobId, t, job.endPeriod(), job.backPeriodInYears());
    }
  }

  private boolean isProcessingJob() {
    return !processingJobRecords.isEmpty();
  }

  public void removeJob(int requestId) {
    processingJobRecords.remove(requestId);
  }

  public void waitForCompletionAndDisconnect() throws InterruptedException {
    while (!jobQueue.isEmpty() || isProcessingJob()) {
      Thread.sleep(100);
    }

    clientSocket.eDisconnect();
    readerExecutorService.shutdown();
  }
}
