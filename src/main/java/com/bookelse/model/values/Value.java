package com.bookelse.model.values;

import java.math.MathContext;
import java.math.RoundingMode;

public abstract class Value<T extends Number> {
  protected final MathContext CALC_MATH_CONTEXT;
  protected T value;
  protected RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;
  protected int precision = 60;

  public Value(T value) {
    CALC_MATH_CONTEXT = new MathContext(precision, ROUNDING_MODE);
    this.value = value;
  }

  public abstract Value<T> addValue(Value<T> addend);

  public abstract Value<T> subtractValue(Value<T> subtrahend);

  public abstract Value<T> multiplyValue(Value<T> multiplier);

  public abstract Value<T> divideValue(Value<T> divisor);

  public T getValue() {
    return value;
  }
}
