package ru.testtasks.crudapi.controllers.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDate;

@Data
@ToString
@FieldNameConstants
@Builder
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserFilterDto {

    String phone;
    String name;
    String email;
    LocalDate dateOfBirth;
}
