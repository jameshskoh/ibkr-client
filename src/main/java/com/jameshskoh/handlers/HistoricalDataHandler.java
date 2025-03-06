package com.jameshskoh.handlers;

import com.ib.client.Bar;

public interface HistoricalDataHandler {

    void handleHistoricalData(int reqId, Bar bar);
    void handleHistoricalDataEnd(int reqId, String start, String end);
}
