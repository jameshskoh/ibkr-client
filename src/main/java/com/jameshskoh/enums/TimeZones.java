package com.jameshskoh.enums;

import java.time.LocalTime;
import java.time.ZoneId;

public class TimeZones {

  public static final ZoneId UTC = ZoneId.of("UTC");
  public static final ZoneId ASIA_SINGAPORE = ZoneId.of("Asia/Singapore");
  public static final ZoneId ASIA_TOKYO = ZoneId.of("Asia/Tokyo");
  public static final ZoneId US_EASTERN = ZoneId.of("US/Eastern");

  public static final LocalTime END_OF_DAY = LocalTime.of(23, 59, 59);

  public static void main(String[] args){
    var ids = ZoneId.getAvailableZoneIds();

    for (var id : ids) {
      System.out.println(id);
    }
  }
}
