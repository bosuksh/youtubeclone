package com.goony.youtubeclone.common.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
  //공통 에러 코드
  INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
  METHOD_NOT_ALLOWED(405,"C002", "Method Not Allowed"),
  ENTITY_NOT_FOUND(400, "CO03", "Entity Not Found"),
  INTERNAL_SERVER_ERROR(500, "C004", "Internal Server Error"),
  INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
  HANDLE_ACCESS_DENIED(403, "C006", "Access is  Denied");

  private int status;
  private final String code;
  private final String message;

}
