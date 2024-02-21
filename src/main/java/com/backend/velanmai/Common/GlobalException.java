package com.backend.velanmai.Common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler
    public ResponseEntity handlingException(Exception e)
    {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setError("something went wrong");
        apiResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(apiResponse);
    }
    @ExceptionHandler
    public ResponseEntity handlingbadException(BadRequestException e)
    {
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        apiResponse.setError(e.getErrors());
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }
}
