package com.ArtSeeReal.pro.hendler;

import com.ArtSeeReal.pro.enums.error.ErrorCode;
import com.ArtSeeReal.pro.enums.module.ErrorResponse;
import com.ArtSeeReal.pro.error.CustomException;
import java.util.NoSuchElementException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.TEMPORARY_SERVER_ERROR);
        errorResponse.setDetail(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleNoSuchElementException(Exception e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.RESOURCE_NOT_FOUND);
        errorResponse.setDetail(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NotImplementedException.class)
    public ResponseEntity<ErrorResponse> handleNotImplementedException(NotImplementedException e) {
        ErrorResponse errorResponse = ErrorResponse.of(ErrorCode.NOT_IMPLEMENTED_EXCEPTION);
        errorResponse.setDetail(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    //요 밑으로 쭉쭉 추가적인 ExceptionHandler들을 추가해서 처리합니다

    /* Custom Error Handler */
    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse response = ErrorResponse.of(e.getErrorCode());
        response.setDetail(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}