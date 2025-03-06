package com.jameshskoh.client;

import com.ib.client.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class AbstractIbkrResponse implements EWrapper {

  // Not implemented yet

  @Override
  public void tickPrice(int i, int i1, double v, TickAttrib tickAttrib) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickSize(int i, int i1, Decimal decimal) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickOptionComputation(
      int i,
      int i1,
      int i2,
      double v,
      double v1,
      double v2,
      double v3,
      double v4,
      double v5,
      double v6,
      double v7) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickGeneric(int i, int i1, double v) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickString(int i, int i1, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickEFP(
      int i, int i1, double v, String s, double v1, int i2, String s1, double v2, double v3) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void orderStatus(
      int i,
      String s,
      Decimal decimal,
      Decimal decimal1,
      double v,
      int i1,
      int i2,
      double v1,
      int i3,
      String s1,
      double v2) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void openOrder(int i, Contract contract, Order order, OrderState orderState) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void openOrderEnd() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void updateAccountValue(String s, String s1, String s2, String s3) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void updatePortfolio(
      Contract contract,
      Decimal decimal,
      double v,
      double v1,
      double v2,
      double v3,
      double v4,
      String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void updateAccountTime(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void accountDownloadEnd(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void contractDetails(int i, ContractDetails contractDetails) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void bondContractDetails(int i, ContractDetails contractDetails) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void contractDetailsEnd(int i) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void execDetails(int i, Contract contract, Execution execution) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void execDetailsEnd(int i) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void updateMktDepth(int i, int i1, int i2, int i3, double v, Decimal decimal) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void updateMktDepthL2(
      int i, int i1, String s, int i2, int i3, double v, Decimal decimal, boolean b) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void updateNewsBulletin(int i, int i1, String s, String s1) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void receiveFA(int i, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void scannerParameters(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void scannerData(
      int i, int i1, ContractDetails contractDetails, String s, String s1, String s2, String s3) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void scannerDataEnd(int i) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void realtimeBar(
      int i,
      long l,
      double v,
      double v1,
      double v2,
      double v3,
      Decimal decimal,
      Decimal decimal1,
      int i1) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void currentTime(long l) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void fundamentalData(int i, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void deltaNeutralValidation(int i, DeltaNeutralContract deltaNeutralContract) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickSnapshotEnd(int i) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void marketDataType(int i, int i1) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void commissionReport(CommissionReport commissionReport) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void position(String s, Contract contract, Decimal decimal, double v) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void positionEnd() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void accountSummary(int i, String s, String s1, String s2, String s3) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void accountSummaryEnd(int i) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void verifyMessageAPI(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void verifyCompleted(boolean b, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void verifyAndAuthMessageAPI(String s, String s1) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void verifyAndAuthCompleted(boolean b, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void displayGroupList(int i, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void displayGroupUpdated(int i, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void error(Exception e) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void error(String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void positionMulti(
      int i, String s, String s1, Contract contract, Decimal decimal, double v) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void positionMultiEnd(int i) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void accountUpdateMulti(int i, String s, String s1, String s2, String s3, String s4) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void accountUpdateMultiEnd(int i) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void securityDefinitionOptionalParameter(
      int i, String s, int i1, String s1, String s2, Set<String> set, Set<Double> set1) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void securityDefinitionOptionalParameterEnd(int i) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void softDollarTiers(int i, SoftDollarTier[] softDollarTiers) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void familyCodes(FamilyCode[] familyCodes) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void symbolSamples(int i, ContractDescription[] contractDescriptions) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void mktDepthExchanges(DepthMktDataDescription[] depthMktDataDescriptions) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickNews(int i, long l, String s, String s1, String s2, String s3) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void smartComponents(int i, Map<Integer, Map.Entry<String, Character>> map) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickReqParams(int i, double v, String s, int i1) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void newsProviders(NewsProvider[] newsProviders) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void newsArticle(int i, int i1, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void historicalNews(int i, String s, String s1, String s2, String s3) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void historicalNewsEnd(int i, boolean b) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void headTimestamp(int i, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void histogramData(int i, List<HistogramEntry> list) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void historicalDataUpdate(int i, Bar bar) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void rerouteMktDataReq(int i, int i1, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void rerouteMktDepthReq(int i, int i1, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void marketRule(int i, PriceIncrement[] priceIncrements) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void pnl(int i, double v, double v1, double v2) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void pnlSingle(int i, Decimal decimal, double v, double v1, double v2, double v3) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void historicalTicks(int i, List<HistoricalTick> list, boolean b) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void historicalTicksBidAsk(int i, List<HistoricalTickBidAsk> list, boolean b) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void historicalTicksLast(int i, List<HistoricalTickLast> list, boolean b) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickByTickAllLast(
      int i,
      int i1,
      long l,
      double v,
      Decimal decimal,
      TickAttribLast tickAttribLast,
      String s,
      String s1) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickByTickBidAsk(
      int i,
      long l,
      double v,
      double v1,
      Decimal decimal,
      Decimal decimal1,
      TickAttribBidAsk tickAttribBidAsk) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void tickByTickMidPoint(int i, long l, double v) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void orderBound(long l, int i, int i1) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void completedOrder(Contract contract, Order order, OrderState orderState) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void completedOrdersEnd() {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void replaceFAEnd(int i, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void wshMetaData(int i, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void wshEventData(int i, String s) {
    throw new UnsupportedOperationException("Not implemented");
  }

  @Override
  public void historicalSchedule(
      int i, String s, String s1, String s2, List<HistoricalSession> list) {
    throw new UnsupportedOperationException("Not implemented");
  }
}
