package com.hdi.studentportal.Advice;


import com.hdi.studentportal.Response.Error.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<?> noSuchElementException(NoSuchElementException noSuchElementException) {
    List<String> details = new ArrayList<>();
    details.add(noSuchElementException.getMessage());
    ErrorResponse errorResponse = new ErrorResponse("Not Found!", details);
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> methodArgumentNotValidException(
      MethodArgumentNotValidException methodArgumentNotValidException) {
    List<String> details = new ArrayList<>();
    methodArgumentNotValidException.getFieldErrors().forEach(fieldError ->
        details.add(fieldError.getDefaultMessage())
    );
    ErrorResponse errorResponse = new ErrorResponse("Field Error!", details);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<?> runtimeException(RuntimeException runtimeException) {
    List<String> details = new ArrayList<>();
    details.add(runtimeException.getMessage());
    ErrorResponse errorResponse = new ErrorResponse("Bad Request!", details);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}

