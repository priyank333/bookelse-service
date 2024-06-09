package com.bookelse.model.calculation;

import com.bookelse.model.values.BigDecimalValue;
import com.bookelse.model.values.BigIntegerValue;

public class DepreciationCalculation implements Calculation {

  private final BigDecimalValue amount;
  private final BigDecimalValue depreciationPercentage;
  private final BigIntegerValue months;

  private DepreciationCalculation(
      BigDecimalValue amount, BigDecimalValue depreciationPercentage, BigIntegerValue months) {
    this.amount = amount;
    this.depreciationPercentage = depreciationPercentage;
    this.months = months;
  }

  public static DepreciationCalculation createDepreciationCalculationObject(
      BigDecimalValue amount, BigDecimalValue depreciationPercentage, BigIntegerValue months) {
    return new DepreciationCalculation(amount, depreciationPercentage, months);
  }

  @Override
  public BigDecimalValue calculate() {
    return amount.percentage(
        depreciationPercentage.divideValue(BigDecimalValue.valueOf(months.getValue().toString())));
  }
}
