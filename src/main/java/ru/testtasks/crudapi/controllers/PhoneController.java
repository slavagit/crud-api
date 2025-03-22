package ru.testtasks.crudapi.controllers;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.testtasks.crudapi.services.PhoneService;
import ru.testtasks.crudapi.services.UserService;

@RestController
@RequiredArgsConstructor
@Validated
public class PhoneController implements PhoneControllerApi {

  private final PhoneService phoneService;
  private final UserService userService;

  @Override
  public ResponseEntity<UUID> createPhone(String phone) {
    return new ResponseEntity<>(
        phoneService.createPhone(userService.getCurrentUser(), phone),
        HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<Void> updatePhone(UUID phoneId, String phone) {
    phoneService.updatePhone(userService.getCurrentUser(), phoneId, phone);
    return new ResponseEntity<>(
        HttpStatus.OK
    );
  }

  @Override
  public ResponseEntity<Void> deletePhone(UUID phoneId) {
    phoneService.deletePhone(userService.getCurrentUser(), phoneId);
    return new ResponseEntity<>(
        HttpStatus.OK
    );
  }

}
