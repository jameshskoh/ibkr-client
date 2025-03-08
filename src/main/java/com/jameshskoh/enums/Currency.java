package com.jameshskoh.enums;

public enum Currency {
  CNH("CNH"),
  EUR("EUR"),
  HKD("HKD"),
  JPY("JPY"),
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
