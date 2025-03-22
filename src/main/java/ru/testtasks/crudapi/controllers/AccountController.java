package ru.testtasks.crudapi.controllers;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.testtasks.crudapi.services.TransferService;
import ru.testtasks.crudapi.services.UserService;

@RestController
@RequiredArgsConstructor
@Validated
public class AccountController implements AccountControllerApi {

  private final TransferService transferService;
  private final UserService userService;

  @Override
  public ResponseEntity<Void> transfer(UUID userId, BigDecimal amount) {
    transferService.transfer(
        userService.getCurrentUser(), userService.getUser(userId), amount);
    return new ResponseEntity<>(
        HttpStatus.OK
    );
  }
}
