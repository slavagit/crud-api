package ru.testtasks.crudapi.configuration.tests.controllers;

import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import ru.testtasks.crudapi.AbstractCommonContext;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

class TransferTest extends AbstractCommonContext {

  @Test
  void multiThreadTransfer() {
    var userFirst = userService.getUser(
        UUID.fromString("610e2138-30e7-40ec-b7ef-4812498f5f04")
    );
    var userSecond = userService.getUser(
        UUID.fromString("2f2e8f85-16d7-48de-ba8e-386e9ea1835b")
    );

    var amountTransferToSecond = 1000;
    var amountTransferToFirst = 3000;

    CompletableFuture.runAsync(() -> {
          var total = IntStream.generate(() -> amountTransferToSecond / 10)
              .filter(el -> {
                try {
                  transferService.transfer(userFirst, userSecond, BigDecimal.valueOf(el));
                  System.out.println("Переведено " + el);
                  return true;
                } catch (Exception ex) {
                  System.out.println("Ошибка перевода " + ex.getMessage());
                  return false;
                }
              })
              .limit(amountTransferToSecond / 100).sum();
          System.out.println("Всего переведено второму" + total);
        }
    );

    CompletableFuture.runAsync(() -> {
          var total = IntStream.generate(() -> amountTransferToFirst / 10)
              .filter(el -> {
                try {
                  transferService.transfer(userSecond, userFirst, BigDecimal.valueOf(el));
                  System.out.println("Переведено " + el);
                  return true;
                } catch (Exception ex) {
                  System.out.println("Ошибка перевода " + ex.getMessage());
                  return false;
                }
              })
              .limit(amountTransferToFirst / 300).sum();
          System.out.println("Всего переведено первому" + total);
        }
    );

    Awaitility.await()
        .during(Duration.ofSeconds(1))
        .atMost(Duration.ofSeconds(10))
        .until(() -> {
          var balanceFirst = userService.getUser(userFirst.getId())
              .getAccount()
              .getBalance();
          var balanceSecond = userService.getUser(userSecond.getId())
              .getAccount()
              .getBalance();
          return balanceFirst.subtract(balanceSecond)
              .compareTo(BigDecimal.valueOf(amountTransferToFirst)) == 0;
        });
    // Восстанавливаем начальные значения
    transferService.transfer(userFirst, userSecond, BigDecimal.valueOf(amountTransferToFirst));
    transferService.transfer(userSecond, userFirst, BigDecimal.valueOf(amountTransferToSecond));

  }
}
