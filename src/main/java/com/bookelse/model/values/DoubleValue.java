package com.bookelse.model.values;

public class DoubleValue extends DecimalValue<Double> {
  private DoubleValue(Double value) {
    super(value);
  }

  public static DoubleValue valueOf(String doubleStr) {
    if (doubleStr != null && (!doubleStr.trim().isEmpty())) {
      return new DoubleValue(Double.valueOf(doubleStr));
    }
    return null;
  }

  @Override
  public DoubleValue percentage(Value<Double> percent) {
    return multiplyValue(percent).divideValue(valueOf("100"));
  }

  @Override
  public DoubleValue addValue(Value<Double> addend) {
    return new DoubleValue(value + addend.value);
  }

  @Override
  public DoubleValue subtractValue(Value<Double> subtrahend) {
    return new DoubleValue(value - subtrahend.value);
  }

  @Override
  public DoubleValue multiplyValue(Value<Double> multiplier) {
    return new DoubleValue(value * multiplier.value);
  }

  @Override
  public DoubleValue divideValue(Value<Double> divisor) {
    return new DoubleValue(value / divisor.value);
  }

  public DoubleValue round(int scale) {
    double scalePow = Math.pow(10, scale);
    double rounded = Math.round(value * scalePow) / scalePow;
    return new DoubleValue(rounded);
  }
}
