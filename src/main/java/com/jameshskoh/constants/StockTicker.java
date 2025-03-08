package com.jameshskoh.constants;

import com.jameshskoh.enums.Currency;
import com.jameshskoh.enums.Exchange;
import com.jameshskoh.enums.SecurityType;

public record StockTicker(
        String symbol, SecurityType securityType, Exchange exchange, Currency currency) implements Ticker {

  public static StockTicker A35 = new StockTicker("A35", SecurityType.STOCK_ETF, Exchange.SGX, Currency.SGD);
  public static StockTicker HK2801 = new StockTicker("2801", SecurityType.STOCK_ETF, Exchange.SEHK, Currency.HKD);
  public static StockTicker G3B = new StockTicker("G3B", SecurityType.STOCK_ETF, Exchange.SGX, Currency.SGD);
  public static StockTicker MBH = new StockTicker("MBH", SecurityType.STOCK_ETF, Exchange.SGX, Currency.SGD);
  public static StockTicker SPLG = new StockTicker("SPLG", SecurityType.STOCK_ETF, Exchange.NASDAQ, Currency.USD);
  public static StockTicker STQ = new StockTicker("STQ", SecurityType.STOCK_ETF, Exchange.SMART_EU, Currency.EUR);

  public static StockTicker SP500 = new StockTicker("SPX", SecurityType.INDEX, Exchange.CBOE, Currency.USD);
}
