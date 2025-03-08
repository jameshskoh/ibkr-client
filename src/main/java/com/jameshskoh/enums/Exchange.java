package com.jameshskoh.enums;

import java.time.ZoneId;

public enum Exchange {
  CBOE("CBOE", TimeZones.UTC),
  IDEALPRO("IDEALPRO", TimeZones.UTC),
  SMART("SMART", TimeZones.US_EASTERN),
  SMART_EU("SMART", TimeZones.UTC),

  NASDAQ("NASDAQ", TimeZones.US_EASTERN),
  SEHK("SEHK", TimeZones.ASIA_SINGAPORE),
  SGX("SGX", TimeZones.ASIA_SINGAPORE);

  private final String label;
  private final ZoneId zoneId;

  Exchange(String label, ZoneId zoneId) {
    this.label = label;
    this.zoneId = zoneId;
  }

  public String getLabel() {
    return label;
  }

  public ZoneId getZoneId() {
    return zoneId;
  }
}
