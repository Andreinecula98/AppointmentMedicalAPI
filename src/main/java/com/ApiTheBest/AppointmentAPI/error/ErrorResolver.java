package com.ApiTheBest.AppointmentAPI.error;

import com.ApiTheBest.AppointmentAPI.model.errorModel.ErrorResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ErrorResolver {


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorResponse handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception,
                                                                      HttpServletResponse servletResponse) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorDescription("Method not allowed");
        servletResponse.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //getFieldErrors returneaza o lista de tip FieldError
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception,
                                                               HttpServletResponse servletResponse) {
        ErrorResponse response = new ErrorResponse();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            response.setErrorDescription("Invalid " + fieldError.getRejectedValue() +
                    " value for field " + fieldError.getField());
        });
        servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ErrorResponse handleInvalidFormatException(InvalidFormatException exception,
                                                      HttpServletResponse servletResponse) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorDescription("Formatul pentru data este invalid. Reintroduceti data folosing formatul yyyy-MM-dd");
        servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return response;
    }
}

