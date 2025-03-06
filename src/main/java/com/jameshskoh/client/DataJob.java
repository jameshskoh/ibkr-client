package com.jameshskoh.client;

import com.jameshskoh.constants.Ticker;

import java.time.LocalDate;

public record DataJob(Ticker ticker, LocalDate endPeriod, int backPeriodInYears) {}
