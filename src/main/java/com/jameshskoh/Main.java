package com.jameshskoh;

import com.jameshskoh.client.DataJob;
import com.jameshskoh.client.IbkrClient;
import com.jameshskoh.constants.ExchangeRateTicker;
import com.jameshskoh.constants.StockTicker;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    Queue<DataJob> jobQueue = prepareJobQueue();

    IbkrClient ibkrClient = new IbkrClient(jobQueue);
    ibkrClient.connectAndListen("localhost", 7499, 0);

    // when received data, do work
    // when received end signal, remove from queue
    // ignore failed queue object for now (further impl. e.g. retry/timeout)
    // properly end the program
    // end program when waiting job queue is 0, and processing job queue is 0
    ibkrClient.waitForConnectionAndMakeRequests();

    ibkrClient.waitForCompletionAndDisconnect();
  }

  private static Queue<DataJob> prepareJobQueue() {
    Queue<DataJob> waitingJobQueue = new LinkedList<>();

//    waitingJobQueue.offer(new DataJob(StockTicker.AAPL, LocalDate.of(2024, 12, 31), 1));
//    waitingJobQueue.offer(new DataJob(StockTicker.G3B, LocalDate.of(2024, 12, 31), 1));
    waitingJobQueue.offer(new DataJob(ExchangeRateTicker.USDSGD, LocalDate.of(2024, 12, 31), 1));

    return waitingJobQueue;
  }
}
