package com.jameshskoh;

import com.jameshskoh.client.DataJob;
import com.jameshskoh.client.IbkrClient;
import com.jameshskoh.constants.ExchangeRateTicker;
import com.jameshskoh.constants.StockTicker;
import com.jameshskoh.secrets.SecretClient;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

  public static void main(String[] args) throws InterruptedException, IOException, SQLException {
    IbkrClient ibkrClient = prepareIbkrClient();
    ibkrClient.connectAndListen("localhost", 7499, 0);
    ibkrClient.waitForConnectionAndMakeRequests();
    ibkrClient.waitForCompletionAndDisconnect();
  }

  private static Queue<DataJob> prepareJobQueue() {
    Queue<DataJob> waitingJobQueue = new LinkedList<>();

    //    waitingJobQueue.offer(new DataJob(StockTicker.A35, LocalDate.of(2024, 12, 31), 1));
    //    waitingJobQueue.offer(new DataJob(StockTicker.HK2801, LocalDate.of(2024, 12, 31), 1));
    waitingJobQueue.offer(new DataJob(StockTicker.G3B, LocalDate.of(2024, 12, 31), 1));
    //    waitingJobQueue.offer(new DataJob(StockTicker.MBH, LocalDate.of(2024, 12, 31), 1));
    //    waitingJobQueue.offer(new DataJob(StockTicker.SPLG, LocalDate.of(2024, 12, 31), 1));
    //    waitingJobQueue.offer(new DataJob(StockTicker.STQ, LocalDate.of(2024, 12, 31), 1));
    waitingJobQueue.offer(new DataJob(ExchangeRateTicker.USDSGD, LocalDate.of(2024, 12, 31), 1));
    //    waitingJobQueue.offer(new DataJob(ExchangeRateTicker.EURSGD, LocalDate.of(2024, 12, 31),
    // 1));
    //    waitingJobQueue.offer(new DataJob(StockTicker.SP500, LocalDate.of(2024, 12, 31), 1));

    return waitingJobQueue;
  }

  private static IbkrClient prepareIbkrClient() throws IOException, SQLException {
    SecretClient secretClient = new SecretClient();
    String stocksDatabaseUrl = secretClient.getSecret(SecretClient.SecretType.STOCKS_DATABASE_URL);
    String stocksDatabaseUser =
        secretClient.getSecret(SecretClient.SecretType.STOCKS_DATABASE_USER);
    String stocksDatabasePasswrd =
        secretClient.getSecret(SecretClient.SecretType.STOCKS_DATABASE_PASSWORD);

    Queue<DataJob> jobQueue = prepareJobQueue();

    return new IbkrClient(jobQueue, stocksDatabaseUrl, stocksDatabaseUser, stocksDatabasePasswrd);
  }
}
