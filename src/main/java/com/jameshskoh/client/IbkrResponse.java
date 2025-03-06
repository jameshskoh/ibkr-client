package com.jameshskoh.client;

import com.ib.client.*;
import com.jameshskoh.handlers.HistoricalDataHandler;

public class IbkrResponse extends AbstractIbkrResponse {

  private final IbkrClient ibkrClient;
  private final HistoricalDataHandler historicalDataHandler;

  public IbkrResponse(
      IbkrClient ibkrClient, HistoricalDataHandler historicalDataHandler) {
    this.ibkrClient = ibkrClient;
    this.historicalDataHandler = historicalDataHandler;
  }

  @Override
  public void connectAck() {
    System.out.println("Connection is acknowledged!");
  }

  @Override
  public void connectionClosed() {
    System.out.println("Connection is closed!");
  }

  @Override
  public void nextValidId(int orderId) {
    System.out.println("Order ID received: " + orderId);
    ibkrClient.setIsConnected();
  }

  @Override
  public void managedAccounts(String s) {
    System.out.println("Managed Accounts: " + s);
  }

  @Override
  public void userInfo(int i, String s) {
    System.out.println("User Info: " + s);
  }

  @Override
  public void error(int id, int errorCode, String errorMessage, String advancedOrderRejectJson) {
    String messageTemplate =
        """
            ID: %d
            Error Code: %d
            Error Message: %s
            Advanced Order Reject JSON:
            %s
            """;

    System.out.printf(messageTemplate, id, errorCode, errorMessage, advancedOrderRejectJson);
  }

  /**
   * Returns the requested historical data bars.
   * @param reqId
   * @param bar
   */
  @Override
  public void historicalData(int reqId, Bar bar) {
    historicalDataHandler.handleHistoricalData(reqId, bar);
  }

  /**
   * Marks the ending of the historical bars reception.
   * @param reqId
   * @param start
   * @param end
   */
  @Override
  public void historicalDataEnd(int reqId, String start, String end) {
    historicalDataHandler.handleHistoricalDataEnd(reqId, start, end);
  }
}
