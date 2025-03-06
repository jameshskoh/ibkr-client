package com.jameshskoh.constants;

import com.jameshskoh.enums.Currency;

public record ExchangeRateTicker(Currency baseCurrency, Currency targetCurrency) implements Ticker {

    public static ExchangeRateTicker USDCNY = new ExchangeRateTicker(Currency.USD, Currency.CNY);
    public static ExchangeRateTicker USDEUR = new ExchangeRateTicker(Currency.USD, Currency.EUR);
    public static ExchangeRateTicker USDHKD = new ExchangeRateTicker(Currency.USD, Currency.HKD);
    public static ExchangeRateTicker USDJPY = new ExchangeRateTicker(Currency.USD, Currency.JPY);
    public static ExchangeRateTicker USDMYR = new ExchangeRateTicker(Currency.USD, Currency.MYR);
    public static ExchangeRateTicker USDSGD = new ExchangeRateTicker(Currency.USD, Currency.SGD);

    public static ExchangeRateTicker SGDCNY = new ExchangeRateTicker(Currency.SGD, Currency.CNY);
    public static ExchangeRateTicker EURSGD = new ExchangeRateTicker(Currency.EUR, Currency.SGD);
    public static ExchangeRateTicker SGDHKD = new ExchangeRateTicker(Currency.SGD, Currency.HKD);
    public static ExchangeRateTicker SGDJPY = new ExchangeRateTicker(Currency.SGD, Currency.JPY);
    public static ExchangeRateTicker SGDMYR = new ExchangeRateTicker(Currency.SGD, Currency.MYR);
}
