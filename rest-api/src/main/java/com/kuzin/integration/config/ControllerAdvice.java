package com.kuzin.integration.config;

import com.kuzin.integration.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ErrorDto handleIllegalArgumentException(IllegalArgumentException e) {
        return new ErrorDto(HttpStatus.BAD_REQUEST.name(), "Bad request message");
    }
}
