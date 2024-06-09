package com.bookelse.model.values;

public class IntegerValue extends Value<Integer> {

  public IntegerValue(Integer value) {
    super(value);
  }

  public static IntegerValue valueOf(String intStr) {
    return new IntegerValue(Integer.valueOf(intStr));
  }

  @Override
  public Value<Integer> addValue(Value<Integer> addend) {
    return new IntegerValue(value + addend.getValue());
  }

  @Override
  public Value<Integer> subtractValue(Value<Integer> subtrahend) {
    return new IntegerValue(value - subtrahend.getValue());
  }

  @Override
  public Value<Integer> multiplyValue(Value<Integer> multiplier) {
    return new IntegerValue(value * multiplier.getValue());
  }

  @Override
  public Value<Integer> divideValue(Value<Integer> divisor) {
    return new IntegerValue(value / divisor.getValue());
  }
}
