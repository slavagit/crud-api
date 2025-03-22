package ru.testtasks.crudapi.utils;


import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtils {

  public static String getMessageChain(Throwable ex) {
    return org.apache.commons.lang3.exception.ExceptionUtils
        .getThrowableList(ex).stream()
        .map(e -> e.getClass().getSimpleName() + ": " + e.getMessage()
        )
        .collect(Collectors.joining(" -> "));
  }

}
