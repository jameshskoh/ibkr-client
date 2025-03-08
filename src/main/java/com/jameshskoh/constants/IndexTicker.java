package com.jameshskoh.constants;

import com.jameshskoh.enums.Currency;
import com.jameshskoh.enums.Exchange;

public record IndexTicker(String symbol, Exchange exchange, Currency currency) implements Ticker {
    
    public static IndexTicker SP500 = new IndexTicker("SPX", Exchange.CBOE, Currency.USD);
}
