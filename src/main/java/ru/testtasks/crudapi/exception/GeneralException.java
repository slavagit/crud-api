package ru.testtasks.crudapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class GeneralException extends RuntimeException {
    public GeneralException(Throwable throwable) {
        super(throwable);
    }

    public GeneralException(String message) {
        super(message);
    }

}
