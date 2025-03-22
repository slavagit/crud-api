package ru.testtasks.crudapi.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.testtasks.crudapi.controllers.dto.GenericPage;
import ru.testtasks.crudapi.controllers.dto.UserDto;
import ru.testtasks.crudapi.controllers.dto.UserFilterDto;

import javax.validation.Valid;

@RequestMapping({"/users"})
@Tag(name = "Список пользователей")
@Validated
public interface UserControllerApi {

    @PostMapping
    @ResponseBody
    @Operation(summary = "Получить пользователей по фильтру")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запрос выполнен успешно"),
            @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера"
            )
    })
    GenericPage<UserDto> getUsers(@Valid @RequestBody UserFilterDto filter,
                                  @PageableDefault(sort = "name", direction = Sort.Direction.DESC) Pageable pageable);

}
