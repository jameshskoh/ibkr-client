package com.jameshskoh;

import com.jameshskoh.client.DataJob;
import com.jameshskoh.client.IbkrClient;
import com.jameshskoh.constants.ExchangeRateTicker;
import com.jameshskoh.constants.IndexTicker;
import com.jameshskoh.constants.StockTicker;
import com.jameshskoh.secrets.SecretClient;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

  public static void main(String[] args) throws InterruptedException, IOException, SQLException {

    String projectId = parseProjectIdArgument(args);

    IbkrClient ibkrClient = prepareIbkrClient(projectId);
    ibkrClient.connectAndListen("localhost", 7499, 0);
    ibkrClient.waitForConnectionAndMakeRequests();
    ibkrClient.waitForCompletionAndDisconnect();
  }

  private static String parseProjectIdArgument(String[] args) {
    for (String arg : args) {
      if (arg.startsWith("--projectId=")) {
        int valueStartIndex = arg.indexOf("=") + 1;
        return arg.substring(valueStartIndex);
      }
    }

    throw new IllegalArgumentException("projectId argument is missing.");
  }

  private static Queue<DataJob> prepareJobQueue() {
    Queue<DataJob> waitingJobQueue = new LinkedList<>();

    // Exchange rates

    // USD.SGD good data starting from 1 Jan 2010
    // waitingJobQueue.offer(new DataJob(ExchangeRateTicker.USDSGD, LocalDate.of(2025, 12, 31), 16));

    // EUR.USD good data starting from 1 Jan 2006
    // waitingJobQueue.offer(new DataJob(ExchangeRateTicker.EURUSD, LocalDate.of(2025, 12, 31), 20));

    // USD.CNH good data starting from 1 Jan 2013
    // waitingJobQueue.offer(new DataJob(ExchangeRateTicker.USDCNH, LocalDate.of(2025, 12, 31), 13));

    // USD.JPY good data starting from 1 Jan 2006
    // waitingJobQueue.offer(new DataJob(ExchangeRateTicker.USDJPY, LocalDate.of(2025, 12, 31), 20));

     // EUR.SGD good data starting from 1 Jan 2010
     // waitingJobQueue.offer(new DataJob(ExchangeRateTicker.EURSGD, LocalDate.of(2025, 12, 31), 16));

    // SGD.CNH good data starting from 1 Jan 2013
    // waitingJobQueue.offer(new DataJob(ExchangeRateTicker.SGDCNH, LocalDate.of(2025, 12, 31), 13));

    // SGD.JPY good data starting from 1 Jan 2010
    // waitingJobQueue.offer(new DataJob(ExchangeRateTicker.SGDJPY, LocalDate.of(2025, 12, 31), 16));

    // Indexes
    // waitingJobQueue.offer(new DataJob(IndexTicker.SP500, LocalDate.of(2025, 12, 31), 21));

    // ETFs and stocks
    // waitingJobQueue.offer(new DataJob(StockTicker.G3B, LocalDate.of(2024, 12, 31), 1));

    return waitingJobQueue;
  }

  private static IbkrClient prepareIbkrClient(String projectId) throws IOException, SQLException {

    SecretClient secretClient = new SecretClient();
    String stocksDatabaseUrl =
        secretClient.getSecret(projectId, SecretClient.SecretType.STOCKS_DATABASE_URL);
    String stocksDatabaseUser =
        secretClient.getSecret(projectId, SecretClient.SecretType.STOCKS_DATABASE_USER);
    String stocksDatabasePasswrd =
        secretClient.getSecret(projectId, SecretClient.SecretType.STOCKS_DATABASE_PASSWORD);

    Queue<DataJob> jobQueue = prepareJobQueue();

    return new IbkrClient(jobQueue, stocksDatabaseUrl, stocksDatabaseUser, stocksDatabasePasswrd);
  }
}
