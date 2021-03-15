package com.goony.youtubeclone.common.error;

import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ErrorResponse {
  private String code;
  private String message;
  private int status;
  private List<FieldError> fieldErrors;


  private ErrorResponse(ErrorCode errorCode) {
    code = errorCode.getCode();
    message = errorCode.getMessage();
    status = errorCode.getStatus();
    fieldErrors = new ArrayList<>();
  }

  private ErrorResponse(ErrorCode errorCode, List<FieldError> fieldErrors) {
    code = errorCode.getCode();
    message = errorCode.getMessage();
    status = errorCode.getStatus();
    this.fieldErrors = fieldErrors;
  }

  public static ErrorResponse of(ErrorCode errorCode) {
    return new ErrorResponse(errorCode);
  }

  public static ErrorResponse of(ErrorCode errorCode, List<FieldError> errors) {
    return new ErrorResponse(errorCode, errors);
  }

  public static ErrorResponse of(ErrorCode errorCode, BindingResult errors) {
    return new ErrorResponse(errorCode, errors.getFieldErrors());
  }

}
