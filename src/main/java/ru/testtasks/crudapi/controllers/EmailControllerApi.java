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

@RequestMapping({"/users/email"})
@Tag(name = "Email пользователя")
@Validated
public interface EmailControllerApi {

  @PostMapping
  @ResponseBody
  @Operation(summary = "Создание почтового адреса")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"
      )
  })
  ResponseEntity<UUID> createEmail(
      @RequestParam
      @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Не соовтетствует формату")
      @Size(max = 200, message = "Ограничение по размеру")
      @NotNull String email
  );

  @PutMapping(path = "/{email_id}")
  @ResponseBody
  @Operation(summary = "Изменение почтового адреса")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"
      )
  })
  ResponseEntity<Void> updateEmail(
      @PathVariable("email_id") UUID emailId,
      @RequestParam
      @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",message = "Не соовтетствует формату")
      @Size(max = 200, message = "Ограничение по размеру")
      @NotNull String email
  );

  @DeleteMapping(path = "/{email_id}")
  @ResponseBody
  @Operation(summary = "Удаление почтового адреса")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"
      )
  })
  ResponseEntity<Void> deleteEmail(
      @PathVariable("email_id") UUID emailId
  );

}
