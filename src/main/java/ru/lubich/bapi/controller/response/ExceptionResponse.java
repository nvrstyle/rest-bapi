package ru.lubich.bapi.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.view.ErrorView;


@RestControllerAdvice
public class ExceptionResponse {

    @ExceptionHandler({InnerException.class, HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorView exceptionHandler(RuntimeException e) {
        ErrorView errorView = new ErrorView(e.getLocalizedMessage());
        return errorView;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView unexpectedExceptionHandler(Exception e) {
        ErrorView errorView = new ErrorView(e.getLocalizedMessage());
        return errorView;
    }
}
