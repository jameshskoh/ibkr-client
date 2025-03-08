package com.jameshskoh.handlers;

import com.ib.client.Bar;
import com.jameshskoh.client.DataJob;
import com.jameshskoh.constants.ExchangeRateTicker;
import com.jameshskoh.constants.IndexTicker;
import com.jameshskoh.constants.StockTicker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;

public class DatabaseHistoricalDataHandler implements HistoricalDataHandler {

  private static final Logger logger = LoggerFactory.getLogger(DatabaseHistoricalDataHandler.class);

  private final Connection connection;

  private final IntFunction<DataJob> getJobInfoCallback;
  private final IntConsumer removeJobCallback;

  public DatabaseHistoricalDataHandler(
      Connection connection,
      IntFunction<DataJob> getJobInfoCallback,
      IntConsumer removeJobCallback) {

    this.connection = connection;
    this.getJobInfoCallback = getJobInfoCallback;
    this.removeJobCallback = removeJobCallback;
  }

  @Override
  public void handleHistoricalData(int reqId, Bar bar) {
    DataJob job = getJobInfoCallback.apply(reqId);

    switch (job.ticker()) {
      case ExchangeRateTicker ticker -> handleExchangeRateHistoricalData(ticker, bar);
      case IndexTicker ticker -> handleIndexHistoricalData(ticker, bar);
      case StockTicker ticker -> handleStockHistoricalData(ticker, bar);
    }
  }

  private void handleIndexHistoricalData(IndexTicker ticker, Bar bar) {

    String insertSQL =
        """
        INSERT INTO daily_index
        (symbol, exchange, currency, date, open, high, low, close)
        VALUES (?, ?, ?, ?, ? ,? ,? ,?)
        ON CONFLICT (symbol, exchange, currency, date)
        DO UPDATE SET open = EXCLUDED.open, high = EXCLUDED.high, low = EXCLUDED.low, close = EXCLUDED.close
        """;

    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
      LocalDate localDate = LocalDate.parse(bar.time(), dtf);

      preparedStatement.setString(1, ticker.symbol());
      preparedStatement.setString(2, ticker.exchange().getLabel());
      preparedStatement.setString(3, ticker.currency().getLabel());
      preparedStatement.setDate(4, java.sql.Date.valueOf(localDate));

      preparedStatement.setBigDecimal(5, BigDecimal.valueOf(bar.open()));
      preparedStatement.setBigDecimal(6, BigDecimal.valueOf(bar.high()));
      preparedStatement.setBigDecimal(7, BigDecimal.valueOf(bar.low()));
      preparedStatement.setBigDecimal(8, BigDecimal.valueOf(bar.close()));

      preparedStatement.executeUpdate();
      logger.info("Index data inserted successfully!");
    } catch (SQLException e) {
      logger.error("Index data insertion failed", e);
    }
  }

  private void handleStockHistoricalData(StockTicker ticker, Bar bar) {

    String insertSQL =
        """
        INSERT INTO daily_stock_price
        (symbol, exchange, currency, date, open, high, low, close, wap, volume)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        ON CONFLICT (symbol, exchange, currency, date)
        DO UPDATE SET open = EXCLUDED.open, high = EXCLUDED.high, low = EXCLUDED.low, close = EXCLUDED.close, wap = EXCLUDED.wap, volume = EXCLUDED.volume
        """;

    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
      LocalDate localDate = LocalDate.parse(bar.time(), dtf);

      preparedStatement.setString(1, ticker.symbol());
      preparedStatement.setString(2, ticker.exchange().getLabel());
      preparedStatement.setString(3, ticker.currency().getLabel());
      preparedStatement.setDate(4, java.sql.Date.valueOf(localDate));

      preparedStatement.setBigDecimal(5, BigDecimal.valueOf(bar.open()));
      preparedStatement.setBigDecimal(6, BigDecimal.valueOf(bar.high()));
      preparedStatement.setBigDecimal(7, BigDecimal.valueOf(bar.low()));
      preparedStatement.setBigDecimal(8, BigDecimal.valueOf(bar.close()));
      preparedStatement.setBigDecimal(9, bar.wap().value());
      preparedStatement.setBigDecimal(10, bar.volume().value());

      preparedStatement.executeUpdate();
      logger.info("Stock data inserted successfully!");
    } catch (SQLException e) {
      logger.error("Stock data insertion failed", e);
    }
  }

  private void handleExchangeRateHistoricalData(ExchangeRateTicker ticker, Bar bar) {

    String insertSQL =
        """
        INSERT INTO daily_exchange_rate
        (base_currency, target_currency, date, open, high, low, close)
        VALUES (?, ?, ?, ?, ?, ?, ?)
        ON CONFLICT (base_currency, target_currency, date)
        DO UPDATE SET open = EXCLUDED.open, high = EXCLUDED.high, low = EXCLUDED.low, close = EXCLUDED.close
        """;

    try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
      DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
      LocalDate localDate = LocalDate.parse(bar.time(), dtf);

      preparedStatement.setString(1, ticker.baseCurrency().getLabel());
      preparedStatement.setString(2, ticker.targetCurrency().getLabel());
      preparedStatement.setDate(3, java.sql.Date.valueOf(localDate));

      preparedStatement.setBigDecimal(4, BigDecimal.valueOf(bar.open()));
      preparedStatement.setBigDecimal(5, BigDecimal.valueOf(bar.high()));
      preparedStatement.setBigDecimal(6, BigDecimal.valueOf(bar.low()));
      preparedStatement.setBigDecimal(7, BigDecimal.valueOf(bar.close()));

      preparedStatement.executeUpdate();
      logger.info("Exchange rate data inserted successfully!");
    } catch (SQLException e) {
      logger.error("Exchange rate data insertion failed", e);
    }
  }

  @Override
  public void handleHistoricalDataEnd(int reqId, String start, String end) {
    logger.info("Historical data transmission complete: {} - {}", start, end);
    removeJobCallback.accept(reqId);
  }
}
