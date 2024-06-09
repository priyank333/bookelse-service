package com.bookelse.model.values;

import java.math.BigInteger;

public class BigIntegerValue extends Value<BigInteger> {
  public BigIntegerValue(BigInteger value) {
    super(value);
  }

  public static BigIntegerValue valueOf(BigDecimalValue bigDecimalValue) {
    if (bigDecimalValue != null && bigDecimalValue.getValue() != null) {
      return BigIntegerValue.createBigIntegerValue(bigDecimalValue.value.toString());
    }
    return null;
  }

  public static BigIntegerValue createBigIntegerValue(String intStr) {
    return new BigIntegerValue(new BigInteger(intStr));
  }

  public static BigIntegerValue valueOf(String strValue) {
    if (strValue != null && (!strValue.trim().isEmpty())) {
      return BigIntegerValue.createBigIntegerValue(strValue);
    }
    return null;
  }

  @Override
  public Value<BigInteger> addValue(Value<BigInteger> addend) {
    return new BigIntegerValue(this.value = this.value.add(addend.getValue()));
  }

  @Override
  public Value<BigInteger> subtractValue(Value<BigInteger> subtrahend) {
    return new BigIntegerValue(this.value.subtract(subtrahend.getValue()));
  }

  @Override
  public Value<BigInteger> multiplyValue(Value<BigInteger> multiplier) {
    return new BigIntegerValue(this.value.multiply(multiplier.getValue()));
  }

  @Override
  public Value<BigInteger> divideValue(Value<BigInteger> divisor) {
    return new BigIntegerValue(this.value.divide(divisor.getValue()));
  }
}
