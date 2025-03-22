package ru.testtasks.crudapi.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Описание ошибки")
public class BaseError implements Serializable {

  protected String title;
  protected String type;
  private String error;
  private String details;

}

