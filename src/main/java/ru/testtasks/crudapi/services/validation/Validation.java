package ru.testtasks.crudapi.services.validation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import ru.testtasks.crudapi.exception.GeneralException;

public interface Validation<T> {

  ValidationInfo validate(T object);

  default void validate(T... objects) throws GeneralException {

    var result = Arrays.stream(objects)
        .map(this::validate)
        .flatMap(el -> el.messageList.stream())
        .collect(Collectors.collectingAndThen(
            Collectors.toList(),
            list -> ValidationInfo.builder().status(list.isEmpty()).messageList(list).build()
        ));
    if (!result.status) {
      throw new GeneralException(result.messageList.toString());
    }
  }

  @AllArgsConstructor
  @Builder
  class ValidationInfo {

    private Boolean status;
    private List<String> messageList;

  }

}
