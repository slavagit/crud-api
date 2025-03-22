package ru.testtasks.crudapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import java.util.UUID;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping({"/users/account"})
@Tag(name = "Счета пользователей")
@Validated
public interface AccountControllerApi {

  @PostMapping
  @ResponseBody
  @Operation(summary = "Перевод по счету")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
      @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"
      )
  })
  ResponseEntity<Void> transfer(
      @Parameter(description = "Идентификатор пользователя получателя")
      @RequestParam
      @NotNull UUID userId,
      @Parameter(description = "Сумма перевода")
      @RequestParam
      @NotNull @Min(1) BigDecimal amount
  );


}
