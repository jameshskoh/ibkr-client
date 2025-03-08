package com.jameshskoh.constants;

import com.jameshskoh.enums.Currency;

public record ExchangeRateTicker(Currency baseCurrency, Currency targetCurrency) implements Ticker {

  public static ExchangeRateTicker EURSGD = new ExchangeRateTicker(Currency.EUR, Currency.SGD);
  public static ExchangeRateTicker EURUSD = new ExchangeRateTicker(Currency.EUR, Currency.USD);

  public static ExchangeRateTicker SGDCNH = new ExchangeRateTicker(Currency.SGD, Currency.CNH);
  public static ExchangeRateTicker SGDHKD = new ExchangeRateTicker(Currency.SGD, Currency.HKD);
  public static ExchangeRateTicker SGDJPY = new ExchangeRateTicker(Currency.SGD, Currency.JPY);

  public static ExchangeRateTicker USDCNH = new ExchangeRateTicker(Currency.USD, Currency.CNH);
  public static ExchangeRateTicker USDHKD = new ExchangeRateTicker(Currency.USD, Currency.HKD);
  public static ExchangeRateTicker USDJPY = new ExchangeRateTicker(Currency.USD, Currency.JPY);
  public static ExchangeRateTicker USDSGD = new ExchangeRateTicker(Currency.USD, Currency.SGD);
}
