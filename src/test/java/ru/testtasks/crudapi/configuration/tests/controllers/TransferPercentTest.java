package ru.testtasks.crudapi.configuration.tests.controllers;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.IntStream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.testtasks.crudapi.AbstractCommonContext;
import ru.testtasks.crudapi.utils.Constants;
import ru.testtasks.crudapi.utils.PercentUtils;

class TransferPercentTest extends AbstractCommonContext {

  @Test
  void interestTransfer() {
    var limit = 10;
    var userFirst = userService.getUser(
        UUID.fromString("610e2138-30e7-40ec-b7ef-4812498f5f04")
    );

    IntStream.generate(() -> 1)
        .limit(limit)
        .forEach(el -> transferService.transferInterest(userFirst));

    var currentBalance = userService.getUser(userFirst.getId()).getAccount().getBalance();
    var interest = userService.getUser(userFirst.getId()).getContraAccount().getBalance().abs();
    var startBalance = currentBalance.subtract(interest);
    var percent = PercentUtils.toPercentageAt(startBalance, currentBalance);
    Assertions.assertThat(
        percent.compareTo(
            Constants.VALUE_INTEREST_PERCENT.multiply(BigDecimal.valueOf(limit)))
    ).isZero();


  }
}
