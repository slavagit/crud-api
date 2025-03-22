package ru.testtasks.crudapi.services.validation.imp;

import static ru.testtasks.crudapi.utils.Constants.VALUE_INTEREST_LIMIT_PERCENT;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.stereotype.Component;
import ru.testtasks.crudapi.model.entity.AbstractAccount;
import ru.testtasks.crudapi.services.validation.Validation;
import ru.testtasks.crudapi.utils.PercentUtils;

@Component
public class ValidationAccount implements Validation<AbstractAccount> {

  @Override
  public ValidationInfo validate(AbstractAccount accountEnt) {
    var result = new ArrayList<String>();
    if (accountEnt.getBalance().compareTo(BigDecimal.ZERO) < 0) {
      result.add("Баланс меньше 0");
    }

    var interestBalance = accountEnt.getUser().getContraAccount().getBalance().abs();
    if (interestBalance.compareTo(BigDecimal.ZERO) != 0) {
      var percentageOfInterest =
          PercentUtils.toPercentageOf(
              interestBalance,
              accountEnt.getBalance()
                  .subtract(interestBalance)
          );
      if (percentageOfInterest.compareTo(VALUE_INTEREST_LIMIT_PERCENT) > 0) {
        result.add("Достигнуто ограничение по начислению процентов");
      }
    }

    return ValidationInfo.builder()
        .status(result.isEmpty())
        .messageList(result)
        .build();

  }
}
