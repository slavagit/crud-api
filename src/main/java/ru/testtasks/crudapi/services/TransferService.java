package ru.testtasks.crudapi.services;

import static ru.testtasks.crudapi.utils.Constants.VALUE_INTEREST_PERCENT;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testtasks.crudapi.exception.GeneralException;
import ru.testtasks.crudapi.model.entity.AbstractAccount;
import ru.testtasks.crudapi.model.entity.AccountEnt;
import ru.testtasks.crudapi.model.entity.UserEnt;
import ru.testtasks.crudapi.repository.AccountRepository;
import ru.testtasks.crudapi.repository.ContrAccountRepository;
import ru.testtasks.crudapi.services.validation.Validation;
import ru.testtasks.crudapi.utils.PercentUtils;

@Service
@RequiredArgsConstructor
public class TransferService {


  private final AccountRepository accountRepository;
  private final ContrAccountRepository contrAccountRepository;
  private final Validation<AbstractAccount> validationAccount;


  @Transactional
  public void transfer(UserEnt sourceUser, UserEnt targetUser, BigDecimal amount) {
    Pair<AccountEnt, AccountEnt> pairAccounts = Pair.of(
        accountRepository.getUserAccountWithBlock(sourceUser.getId())
            .orElseThrow(() -> new GeneralException("Счет заблокирован или не найден")),
        accountRepository.getUserAccountWithBlock(targetUser.getId())
            .orElseThrow(() -> new GeneralException("Счет заблокирован или не найден"))
    );
    pairAccounts.getFirst().setBalance(
        pairAccounts.getFirst().getBalance().subtract(amount)
    );
    pairAccounts.getSecond().setBalance(
        pairAccounts.getSecond().getBalance().add(amount)
    );
    validationAccount.validate(pairAccounts.getFirst(), pairAccounts.getSecond());
  }


  @Transactional
  public void transferInterest(UserEnt sourceUser) {
    Pair<AbstractAccount, AbstractAccount> pairAccounts = Pair.of(
        contrAccountRepository.getUserAccountWithBlock(sourceUser.getId())
            .orElseThrow(() -> new GeneralException("Счет заблокирован или не найден")),
        accountRepository.getUserAccountWithBlock(sourceUser.getId())
            .orElseThrow(() -> new GeneralException("Счет заблокирован или не найден"))
    );

    var amount = PercentUtils.percentOf(
        VALUE_INTEREST_PERCENT,
        pairAccounts.getSecond().getBalance()
            .subtract(pairAccounts.getFirst().getUser().getContraAccount().getBalance().abs()));

    pairAccounts.getFirst().setBalance(
        pairAccounts.getFirst().getBalance().subtract(amount)
    );
    pairAccounts.getSecond().setBalance(
        pairAccounts.getSecond().getBalance().add(amount)
    );
    validationAccount.validate(pairAccounts.getSecond());
  }

}
