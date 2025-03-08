package com.jameshskoh.client;

import com.ib.client.Contract;
import com.ib.client.EClientSocket;
import com.jameshskoh.constants.ExchangeRateTicker;
import com.jameshskoh.constants.StockTicker;
import com.jameshskoh.enums.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

// https://interactivebrokers.github.io/tws-api/historical_bars.html
// https://www.interactivebrokers.com/campus/ibkr-quant-news/retrieving-historical-data-from-ibkr/
public class IbkrRequest {

  private static final String MIDPOINT = "MIDPOINT";
  private static final String TRADES = "TRADES";

  public void requestDailyHistoricalExchangeRate(
      int jobId,
      EClientSocket clientSocket,
      ExchangeRateTicker ticker,
      LocalDate endPeriod,
      int backPeriodInYears) {

    Contract contract = new Contract();
    contract.symbol(ticker.baseCurrency().getLabel());
    contract.secType(SecurityType.FOREX_PAIR.getLabel());
    contract.currency(ticker.targetCurrency().getLabel());
    contract.exchange(Exchange.IDEALPRO.getLabel());

    // exchange rate data does not have TRADES data, nor volume, nor volume weighted average
    requestDailyHistoricalData(
        clientSocket, jobId, contract, endPeriod, TimeZones.UTC, backPeriodInYears, MIDPOINT);
  }

  public void requestDailyHistoricalStockPrice(
      EClientSocket clientSocket,
      int jobId,
      StockTicker ticker,
      LocalDate endPeriod,
      int backPeriodInYears) {

    Contract contract = new Contract();
    contract.symbol(ticker.symbol());
    contract.secType(ticker.securityType().getLabel());
    contract.currency(ticker.currency().getLabel());
    contract.exchange(ticker.exchange().getLabel());

    requestDailyHistoricalData(
        clientSocket,
        jobId,
        contract,
        endPeriod,
        ticker.exchange().getZoneId(),
        backPeriodInYears,
        TRADES);
  }

  private void requestDailyHistoricalData(
      EClientSocket clientSocket,
      int requestId,
      Contract contract,
      LocalDate cutoffDate,
      ZoneId zoneId,
      int backPeriodInYears,
      String whatToShow) {

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss VV");
    String endDateTimeString =
        dtf.format(ZonedDateTime.of(cutoffDate, TimeZones.END_OF_DAY, zoneId));

    String backPeriods = "%d Y".formatted(backPeriodInYears);

    /**
     * tickerId: A unique identifier which will serve to identify the incoming data. <br>
     * contract: The IBApi.Contract you are interested in. <br>
     * endDateTime: The request's end date and time (the empty string indicates current present
     * moment). <br>
     * durationString: The amount of time (or Valid Duration String units) to go back from the
     * request's given end date and time. <br>
     * barSizeSetting: The data's granularity or Valid Bar Sizes <br>
     * whatToShow: The type of data to retrieve. See Historical Data Types <br>
     * useRTH: Whether (1) or not (0) to retrieve data generated only within Regular Trading Hours
     * (RTH) <br>
     * formatDate: The format in which the incoming bars' date should be presented. Note that for
     * day bars, only yyyyMMdd format is available. <br>
     * keepUpToDate: Whether a subscription is made to return updates of unfinished real time bars
     * as they are available (True), or all data is returned on a one-time basis (False). Available
     * starting with API v973.03+ and TWS v965+. If True, and endDateTime cannot be specified.
     */
    clientSocket.reqHistoricalData(
        requestId,
        contract,
        endDateTimeString,
        backPeriods,
        Interval.DAY.getLabel(),
        whatToShow,
        1,
        1,
        false,
        null);
  }
}
