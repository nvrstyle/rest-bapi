package ru.lubich.bapi.controller.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lubich.bapi.exception.InnerException;
import ru.lubich.bapi.view.ErrorView;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class ExceptionResponse {

    @ExceptionHandler({InnerException.class, NullPointerException.class, HttpMessageNotReadableException.class, HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorView exceptionHandler(RuntimeException e) {
        String message;
        if (e instanceof NullPointerException) {
            message = "В запросе Был передан некорректный JSON";
        }
        else {
            message = e.getMessage();
        }
        ErrorView errorView = new ErrorView(message);
        return errorView;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView unexpectedExceptionHandler(MethodArgumentNotValidException e) {
        InnerException innerException = new InnerException("Не задан обязательный параметр", e);
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }
        ErrorView errorView = new ErrorView(innerException.getMessage() + ": " + errors);
        return errorView;
    }
}
