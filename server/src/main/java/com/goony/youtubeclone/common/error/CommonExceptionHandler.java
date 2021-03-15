package com.goony.youtubeclone.common.error;

import com.goony.youtubeclone.common.error.exception.BusinessException;
import com.goony.youtubeclone.common.error.exception.UserDuplicatedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {

  @ExceptionHandler(value = UserDuplicatedException.class)
  protected ResponseEntity<ErrorResponse> handleUserDuplicatedException(UserDuplicatedException ex) {
    log.error("Email is Duplicated");
    log.error(ex.getLocalizedMessage());
    ErrorResponse response = ErrorResponse.of(ErrorCode.USER_DUPLICATED);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  protected ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    log.error("Argument Binding Error");
    log.error(ex.getLocalizedMessage());
    ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }
}
