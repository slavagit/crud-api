package ru.testtasks.crudapi.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.testtasks.crudapi.utils.ExceptionUtils;

@ControllerAdvice
@RequiredArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

  private static final MultiValueMap<String, String> HEADERS_MAP = new LinkedMultiValueMap<>();

  static {
    HEADERS_MAP.add("Content-Type", "application/problem+json");
  }


  @ExceptionHandler(Throwable.class)
  public ResponseEntity<BaseError> handleDefaultException(Throwable ex) {

    var errorBody = BaseError.builder()
        .title("ОШИБКА")
        .type(ex.getClass().getSimpleName())
        .error(ex.getMessage())
        .details(ExceptionUtils.getMessageChain(ex))
        .build();

    logger.info(ex.getClass().getSimpleName() + " : " + ex.getMessage(), ex);

    return new ResponseEntity<>(errorBody, HEADERS_MAP, annotatedOrDefault(ex.getClass()));
  }


  private HttpStatus annotatedOrDefault(Class<? extends Throwable> clazz) {
    var responseStatus = clazz.getAnnotation(ResponseStatus.class);
    if (responseStatus != null) {
      return responseStatus.value();
    }
    return HttpStatus.INTERNAL_SERVER_ERROR;
  }
}
