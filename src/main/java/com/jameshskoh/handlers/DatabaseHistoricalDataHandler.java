package com.jameshskoh.handlers;

import com.ib.client.Bar;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHistoricalDataHandler implements HistoricalDataHandler {

  private static final String URL =
      "jdbc:postgresql://ep-broad-glitter-a11oiif3-pooler.ap-southeast-1.aws.neon.tech/neondb";
  private static final String USERNAME = "neondb_owner";
  private static final String PASSWORD = "npg_Jm38gwsiactj";

  // concerns about connection creation + query overhead
  @Override
  public void handleHistoricalData(int reqId, Bar bar) {
    String insertSQL =
        """
INSERT INTO daily_stock_price
(symbol, security_type, exchange, currency, date, open, high, low, close, wap, volume)
VALUES (?, ?, ?, ?, ?, ?, ?, ?)
""";

    try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
      System.out.println("Connection created!");

      try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
        System.out.println("Statement created!");

        preparedStatement.setInt(1, reqId);
        preparedStatement.setString(2, bar.time());
        preparedStatement.setBigDecimal(3, BigDecimal.valueOf(bar.open()));
        preparedStatement.setBigDecimal(4, BigDecimal.valueOf(bar.high()));
        preparedStatement.setBigDecimal(5, BigDecimal.valueOf(bar.low()));
        preparedStatement.setBigDecimal(6, BigDecimal.valueOf(bar.close()));
        preparedStatement.setBigDecimal(7, bar.wap().value());
        preparedStatement.setBigDecimal(8, bar.volume().value());

        preparedStatement.executeUpdate();
        System.out.println("Data inserted successfully!");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void handleHistoricalDataEnd(int reqId, String start, String end) {
    System.out.println("Historical Data End: " + start + ", " + end);
  }
}
