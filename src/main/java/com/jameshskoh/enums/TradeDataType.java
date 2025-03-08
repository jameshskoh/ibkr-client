package com.jameshskoh.enums;

public enum TradeDataType {
    MIDPOINT("MIDPOINT"),
    TRADES("TRADES");

    private final String label;

    TradeDataType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
