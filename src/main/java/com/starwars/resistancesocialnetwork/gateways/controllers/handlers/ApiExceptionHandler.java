package com.starwars.resistancesocialnetwork.gateways.controllers.handlers;

import com.starwars.resistancesocialnetwork.exceptions.HeadquarterNotFoundException;
import com.starwars.resistancesocialnetwork.exceptions.RebelNotFoundException;
import com.starwars.resistancesocialnetwork.gateways.controllers.response.ErrorResponse;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        val errorMessages = exception.getFieldErrors().stream().map(this::mapToErrorMessage).collect(Collectors.toList());
        val errorResponse = ErrorResponse.builder().errorsMessage(errorMessages).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleHeadquarterNotFoundException(HeadquarterNotFoundException exception){
        ErrorResponse errorResponse = ErrorResponse.builder().errorsMessage(List.of(exception.getMessage())).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleRebelNotFoundException(RebelNotFoundException exception){
        ErrorResponse errorResponse = ErrorResponse.builder().errorsMessage(List.of(exception.getMessage())).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    private String mapToErrorMessage(FieldError fieldError){
        return fieldError.getField() + ":" + fieldError.getDefaultMessage();
    }

}
