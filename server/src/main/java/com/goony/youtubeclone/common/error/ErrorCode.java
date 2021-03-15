package com.goony.youtubeclone.common.error;

public enum ErrorCode {
  //공통 에러 코드
  INVALID_INPUT_VALUE(400, "C001", "Invalid Input Value"),
  METHOD_NOT_ALLOWED(405,"C002", "Method Not Allowed"),
  ENTITY_NOT_FOUND(400, "CO03", "Entity Not Found"),
  INTERNAL_SERVER_ERROR(500, "C004", "Internal Server Error"),
  INVALID_TYPE_VALUE(400, "C005", "Invalid Type Value"),
  HANDLE_ACCESS_DENIED(403, "C006", "Access is  Denied");

  private final String code;
  private final String message;
  private int status;

  ErrorCode(int status, String code, String message) {
    this.code = code;
    this.message = message;
    this.status = status;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public int getStatus() {
    return status;
  }
}
