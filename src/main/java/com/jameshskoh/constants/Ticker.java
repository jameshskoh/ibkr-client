package com.jameshskoh.constants;

public sealed interface Ticker permits IndexTicker, ExchangeRateTicker, StockTicker {}
