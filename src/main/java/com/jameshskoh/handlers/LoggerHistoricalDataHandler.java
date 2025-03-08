package com.jameshskoh.handlers;

import com.ib.client.Bar;
import com.jameshskoh.client.DataJob;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

public class LoggerHistoricalDataHandler implements HistoricalDataHandler {

  private final IntFunction<DataJob> getJobInfoCallback;
  private final IntConsumer removeJobCallback;

  public LoggerHistoricalDataHandler(
      IntFunction<DataJob> getJobInfoCallback, IntConsumer removeJobCallback) {

    this.getJobInfoCallback = getJobInfoCallback;
    this.removeJobCallback = removeJobCallback;
  }

  @Override
  public void handleHistoricalData(int reqId, Bar bar) {

    DataJob job = getJobInfoCallback.apply(reqId);

    String historicalDataTemplate =
        """
        Historical data of %s:
        %s - Open: %s, High: %s, Low: %s, Close: %s, Weighted average price: %s, Volume: %s
        """;

    System.out.printf(
        historicalDataTemplate,
        job.ticker(),
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
    removeJobCallback.accept(reqId);
  }
}
