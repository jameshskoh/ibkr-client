package com.jameshskoh.constants;

import com.jameshskoh.enums.Currency;
import com.jameshskoh.enums.Exchange;

public record StockTicker(
        String symbol, Exchange exchange, Currency currency) implements Ticker {

  public static StockTicker A35 = new StockTicker("A35", Exchange.SGX, Currency.SGD);
  public static StockTicker HK2801 = new StockTicker("2801", Exchange.SEHK, Currency.HKD);
  public static StockTicker G3B = new StockTicker("G3B", Exchange.SGX, Currency.SGD);
  public static StockTicker MBH = new StockTicker("MBH", Exchange.SGX, Currency.SGD);
  public static StockTicker SPLG = new StockTicker("SPLG", Exchange.NASDAQ, Currency.USD);
  public static StockTicker STQ = new StockTicker("STQ", Exchange.SMART_EU, Currency.EUR);
}
