package ru.testtasks.crudapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping({"/users/phone"})
@Tag(name = "Телефон пользователя")
@Validated
public interface PhoneControllerApi {

  @PostMapping
  @ResponseBody
  @Operation(summary = "Добавление номера")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"
      )
  })
  ResponseEntity<UUID> createPhone(
      @RequestParam
      @Pattern(regexp = "^(7|8)\\d{10}$",message = "Не соовтетствует формату")
      @Size(max = 13, message = "Ограничение по размеру")
      @NotNull String phone
  );

  @PutMapping(path = "/{phone_id}")
  @ResponseBody
  @Operation(summary = "Изменение номера")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"
      )
  })
  ResponseEntity<Void> updatePhone(
      @PathVariable("phone_id") UUID phoneId,
      @RequestParam
      @Pattern(regexp = "^(7|8)\\d{10}$",message = "Не соовтетствует формату")
      @Size(max = 13, message = "Ограничение по размеру")
      @NotNull String phone
  );

  @DeleteMapping(path = "/{phone_id}")
  @ResponseBody
  @Operation(summary = "Удаление номера")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"
      )
  })
  ResponseEntity<Void> deletePhone(
      @PathVariable("phone_id") UUID phoneId
  );

}
