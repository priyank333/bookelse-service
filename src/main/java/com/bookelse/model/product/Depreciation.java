package com.bookelse.model.product;

import com.bookelse.model.calculation.DepreciationCalculation;
import com.bookelse.model.values.BigDecimalValue;
import com.bookelse.model.values.BigIntegerValue;

public class Depreciation {
  private final BigDecimalValue depreciationPercentage;
  private final BigIntegerValue months;
  private final BigDecimalValue monthlyDepreciationAmount;
  private final BigDecimalValue calculateOnAmount;
  private final BigDecimalValue totalDepreciation;

  public Depreciation(
      BigDecimalValue depreciationPercentage,
      BigDecimalValue calculateOnAmount,
      BigIntegerValue months) {
    this.depreciationPercentage = depreciationPercentage;
    this.calculateOnAmount = calculateOnAmount;
    this.months = months;
    DepreciationCalculation depreciationCalculationMonthly =
        DepreciationCalculation.createDepreciationCalculationObject(
            calculateOnAmount, depreciationPercentage, months);
    this.monthlyDepreciationAmount = depreciationCalculationMonthly.calculate().round(2);
    DepreciationCalculation totalDepreciationCalculation =
        DepreciationCalculation.createDepreciationCalculationObject(
            calculateOnAmount, depreciationPercentage, BigIntegerValue.createBigIntegerValue("1"));
    this.totalDepreciation = totalDepreciationCalculation.calculate().round(2);
  }

  public Depreciation(BigDecimalValue depreciationPercentage, BigDecimalValue calculateOnAmount) {
    this.depreciationPercentage = depreciationPercentage;
    this.calculateOnAmount = calculateOnAmount;
    this.months = BigIntegerValue.createBigIntegerValue("1");
    DepreciationCalculation depreciationCalculationMonthly =
        DepreciationCalculation.createDepreciationCalculationObject(
            calculateOnAmount, depreciationPercentage, months);
    this.monthlyDepreciationAmount = depreciationCalculationMonthly.calculate().round(2);
    this.totalDepreciation = monthlyDepreciationAmount;
  }

  public BigDecimalValue getDepreciationPercentage() {
    return depreciationPercentage;
  }

  public BigIntegerValue getMonths() {
    return months;
  }

  public BigDecimalValue getMonthlyDepreciationAmount() {
    return monthlyDepreciationAmount;
  }

  public BigDecimalValue getCalculateOnAmount() {
    return calculateOnAmount;
  }

  public BigDecimalValue getTotalDepreciation() {
    return totalDepreciation;
  }
}
