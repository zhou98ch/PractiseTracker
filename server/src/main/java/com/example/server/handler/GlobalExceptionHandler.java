package com.example.server.handler;


import com.example.result.Result;
import exception.BaseException;

import constant.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Global Exception Handler
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * catch business exception
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("Exception Info：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * handle SQL exception
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        if(message.contains("Unique index or primary key violation")){
            String[] split =  message.split(" ");
            String email = split[16];
            String msg = "The user with email of " + email + MessageConstant.AlREADY_EXISTS;
            return Result.error(msg);
        }else{
            return Result.error(MessageConstant.UNKNOWN_ERROR);
        }
    }

    //TODO Handle other SQL exceptions
}
