package com.jameshskoh.client;

import com.ib.client.*;
import com.jameshskoh.handlers.HistoricalDataHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IbkrResponse extends AbstractIbkrResponse {

  private static final Logger logger = LoggerFactory.getLogger(IbkrResponse.class);

  private final Runnable setConnectedCallback;
  private final HistoricalDataHandler historicalDataHandler;

  public IbkrResponse(Runnable setConnectedCallback, HistoricalDataHandler historicalDataHandler) {
    this.setConnectedCallback = setConnectedCallback;
    this.historicalDataHandler = historicalDataHandler;
  }

  @Override
  public void connectAck() {
    logger.info("TWS connection is acknowledged!");
  }

  @Override
  public void connectionClosed() {
    logger.info("TWS connection closed successfully!");
  }

  @Override
  public void nextValidId(int orderId) {
    logger.info("Order ID received: {}", orderId);
    setConnectedCallback.run();
  }

  @Override
  public void managedAccounts(String s) {
    logger.info("Managed account: {}", s);
  }

  @Override
  public void userInfo(int i, String s) {
    logger.info("User info: {}", s);
  }

  @Override
  public void error(int id, int errorCode, String errorMessage, String advancedOrderRejectJson) {
    if (id == -1) {
      logger.info("Keep alive: connection alive.");
    } else {
      logger.error(
          """
          TWS error:
            ID: {}
            Error code: {}
            Error message: {}
            Advanced order reject JSON:
          {}
          """,
          id,
          errorCode,
          errorMessage,
          advancedOrderRejectJson);
    }
  }

  /**
   * Returns the requested historical data bars.
   *
   * @param reqId
   * @param bar
   */
  @Override
  public void historicalData(int reqId, Bar bar) {
    historicalDataHandler.handleHistoricalData(reqId, bar);
  }

  /**
   * Marks the ending of the historical bars reception.
   *
   * @param reqId
   * @param start
   * @param end
   */
  @Override
  public void historicalDataEnd(int reqId, String start, String end) {
    historicalDataHandler.handleHistoricalDataEnd(reqId, start, end);
  }
}
