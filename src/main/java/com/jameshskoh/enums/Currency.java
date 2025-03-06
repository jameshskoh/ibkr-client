package com.jameshskoh.enums;

public enum Currency {
  CNY("CNY"),
  EUR("EUR"),
  HKD("HKD"),
  JPY("JPY"),
  MYR("MYR"),
  SGD("SGD"),
  USD("USD");

  private final String label;

  Currency(String label) {
    this.label = label;
  }

  public String getLabel() {
    return label;
  }
}
