package ru.kpfu.itis.darzam.chess.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static ru.kpfu.itis.darzam.chess.response_value.InvalidResponseValue.UNREACHABLE_POSITION;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Integer invalidArgument(IllegalArgumentException ex){
        return UNREACHABLE_POSITION;
    }
}
