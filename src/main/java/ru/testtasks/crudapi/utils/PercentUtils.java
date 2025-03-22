package ru.testtasks.crudapi.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PercentUtils {

  private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

  public static BigDecimal toPercentageOf(BigDecimal value, BigDecimal total) {
    return value.divide(total, 4, RoundingMode.HALF_UP).multiply(ONE_HUNDRED);
  }

  public static BigDecimal toPercentageAt(BigDecimal value, BigDecimal total) {
    return total.multiply(ONE_HUNDRED).divide(value, 4, RoundingMode.HALF_UP).subtract(ONE_HUNDRED);

  }

  public static BigDecimal percentOf(BigDecimal percentage, BigDecimal total) {
    return percentage.multiply(total).divide(ONE_HUNDRED, 2, RoundingMode.HALF_UP);
  }

}
