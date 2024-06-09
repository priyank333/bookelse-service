package com.bookelse.model.values;

public abstract class DecimalValue<T extends Number> extends Value<T> {
  public DecimalValue(T value) {
    super(value);
  }

  public abstract Value<T> percentage(Value<T> percent);
}
