package ru.testtasks.crudapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import ru.testtasks.crudapi.controllers.dto.GenericPage;
import ru.testtasks.crudapi.controllers.dto.UserDto;
import ru.testtasks.crudapi.controllers.dto.UserFilterDto;
import ru.testtasks.crudapi.services.UserService;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController implements UserControllerApi {

    private final UserService userService;

    @Override
    public GenericPage<UserDto> getUsers(UserFilterDto filter, Pageable pageable) {
        return userService.getUsersByFilter(filter, pageable);
    }
}
