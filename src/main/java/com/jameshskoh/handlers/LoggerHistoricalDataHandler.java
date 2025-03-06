package com.jameshskoh.handlers;

import com.ib.client.Bar;
import com.jameshskoh.client.IbkrClient;

public class LoggerHistoricalDataHandler implements HistoricalDataHandler {

  private final IbkrClient ibkrClient;

  public LoggerHistoricalDataHandler(IbkrClient ibkrClient) {
    this.ibkrClient = ibkrClient;
  }

  @Override
  public void handleHistoricalData(int reqId, Bar bar) {
    String historicalDataTemplate =
        """
        Historical data:
        %s - Open: %s, High: %s, Low: %s, Close: %s, Weighted average price: %s, Volume: %s
        """;

    System.out.printf(
        historicalDataTemplate,
        bar.time(),
        bar.open(),
        bar.high(),
        bar.low(),
        bar.close(),
        bar.wap(),
        bar.volume());
  }

  @Override
  public void handleHistoricalDataEnd(int reqId, String start, String end) {
    System.out.println("Historical Data End: " + start + ", " + end);
    ibkrClient.removeJob(reqId);
  }
}
