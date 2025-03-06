package com.jameshskoh.constants;

import com.jameshskoh.enums.Currency;
import com.jameshskoh.enums.Exchange;
import com.jameshskoh.enums.SecurityType;

public record StockTicker(
        String symbol, SecurityType securityType, Exchange exchange, Currency currency) implements Ticker {

  public static StockTicker AAPL =
      new StockTicker("AAPL", SecurityType.STOCK_ETF, Exchange.NASDAQ, Currency.USD);
  public static StockTicker BOEING =
      new StockTicker("BA", SecurityType.STOCK_ETF, Exchange.SMART, Currency.USD);
  public static StockTicker G3B = new StockTicker("G3B", SecurityType.STOCK_ETF, Exchange.SGX, Currency.SGD);
}
