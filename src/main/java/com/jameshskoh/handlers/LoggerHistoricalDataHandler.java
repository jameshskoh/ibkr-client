package com.jameshskoh.handlers;

import com.ib.client.Bar;
import com.jameshskoh.client.DataJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.IntConsumer;
import java.util.function.IntFunction;

public class LoggerHistoricalDataHandler implements HistoricalDataHandler {

  private static final Logger logger = LoggerFactory.getLogger(LoggerHistoricalDataHandler.class);

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

    logger.info(
        """
        Historical data of {}:
          {} - Open: {}, High: {}, Low: {}, Close: {}, Weighted average price: {}, Volume: {}
        """,
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
    logger.info("Historical data transmission complete: {} - {}", start, end);
    removeJobCallback.accept(reqId);
  }
}
