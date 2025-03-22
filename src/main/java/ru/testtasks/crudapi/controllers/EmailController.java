package ru.testtasks.crudapi.controllers;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.testtasks.crudapi.services.EmailService;
import ru.testtasks.crudapi.services.UserService;

@RestController
@RequiredArgsConstructor
@Validated
public class EmailController implements EmailControllerApi {

  private final EmailService emailService;
  private final UserService userService;

  @Override
  public ResponseEntity<UUID> createEmail(String email) {
    return new ResponseEntity<>(
        emailService.createEmail(userService.getCurrentUser(), email),
        HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<Void> updateEmail(UUID emailId, String email) {
    emailService.updateEmail(userService.getCurrentUser(), emailId, email);
    return new ResponseEntity<>(
        HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<Void> deleteEmail(UUID emailId) {
    emailService.deleteEmail(userService.getCurrentUser(),emailId);
    return new ResponseEntity<>(
        HttpStatus.OK
    );
  }

}
