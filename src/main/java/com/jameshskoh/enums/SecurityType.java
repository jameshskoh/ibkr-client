package com.jameshskoh.enums;

public enum SecurityType {
  STOCK_ETF("STK"),
  OPTION("OPT"),
  FUTURES("FUT"),
  INDEX("IND"),
  FUTURES_OPTION("FOP"),
  FOREX_PAIR("CASH"),
  COMBO("BAG"),
  WARRANT("WAR"),
  BOND("BOND"),
  COMMODITY("CMDTY"),
  NEWS("NEWS"),
  MUTUAL_FUND("FUND");

  private final String label;

  SecurityType(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
