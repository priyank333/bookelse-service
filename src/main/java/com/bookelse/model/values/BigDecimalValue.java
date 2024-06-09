package com.bookelse.model.values;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class BigDecimalValue extends DecimalValue<BigDecimal> {

  private BigDecimalValue(BigDecimal value) {
    super(value);
  }

  private BigDecimalValue(String strValue) {
    super(new BigDecimal(strValue));
  }

  public static BigDecimalValue valueOf(BigIntegerValue bigIntegerValue) {
    if (bigIntegerValue != null && bigIntegerValue.getValue() != null) {
      return valueOf(bigIntegerValue.value.toString());
    }
    return null;
  }

  public static BigDecimalValue valueOf(String strValue) {
    if (strValue != null && (!strValue.trim().isEmpty())) {
      return new BigDecimalValue(strValue);
    }
    return null;
  }

  public static BigDecimalValue valueOf(DoubleValue doubleValue) {
    Objects.requireNonNull(doubleValue);
    return valueOf(doubleValue.value.toString());
  }

  @Override
  public BigDecimalValue percentage(Value<BigDecimal> percent) {
    return valueOf(
        String.valueOf(
            percent
                .value
                .divide(BigDecimal.valueOf(100), CALC_MATH_CONTEXT)
                .multiply(new BigDecimal(value.toString()))));
  }

  @Override
  public BigDecimalValue addValue(Value<BigDecimal> addend) {
    return new BigDecimalValue(this.value = this.value.add(addend.getValue(), CALC_MATH_CONTEXT));
  }

  @Override
  public BigDecimalValue subtractValue(Value<BigDecimal> subtrahend) {
    return new BigDecimalValue(this.value.subtract(subtrahend.getValue(), CALC_MATH_CONTEXT));
  }

  @Override
  public BigDecimalValue multiplyValue(Value<BigDecimal> multiplier) {
    return new BigDecimalValue(this.value.multiply(multiplier.getValue(), CALC_MATH_CONTEXT));
  }

  @Override
  public BigDecimalValue divideValue(Value<BigDecimal> divisor) {
    return new BigDecimalValue(this.value.divide(divisor.getValue(), CALC_MATH_CONTEXT));
  }

  public BigDecimalValue getRoundedNewValue(int scale) {
    return new BigDecimalValue(this.getValue().setScale(scale, RoundingMode.HALF_UP));
  }

  public BigDecimalValue round(int scale) {
    if (this.value != null) {
      this.value = this.getValue().setScale(scale, RoundingMode.HALF_UP);
    }
    return this;
  }

  @Override
  public String toString() {
    return "BigDecimalValue{" + "value=" + value + '}';
  }
}
