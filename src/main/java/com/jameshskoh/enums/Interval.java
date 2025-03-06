package com.jameshskoh.enums;

// all possible values: 1 secs, 5 secs, 10 secs, 15 secs, 30 secs, 1 min, 2 mins, 3 mins, 5 mins, 10 mins, 15 mins, 20 mins, 30 mins, 1 hour, 2 hours, 3 hours, 4 hours, 8 hours, 1 day, 1W, 1M
public enum Interval {
    SEC_1("1 secs"),
    SEC_15("10 secs"),
    MIN_1("1 min"),
    MIN_15("10 mins"),
    HOUR_1("1 hour"),
    DAY("1 day"),
    WEEK("1W"),
    MONTH("1M");

    private final String label;

    Interval(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
