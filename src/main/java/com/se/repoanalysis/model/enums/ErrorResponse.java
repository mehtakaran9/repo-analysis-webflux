package com.se.repoanalysis.model.enums;

/**
 * @author Karan Mehta
 */
public enum ErrorResponse {
  UNSPECIFIED("UNSPECIFIED", "Please try again after sometime"),
  INVALID_REQUEST("INVALID_REQUEST", "Request is invalid"),
  EXTERNAL_ERROR("EXTERNAL_ERROR", "Error occured while getting the data from npm"),
  FAIL_RESPONSE("RESPONSE_FAILED", "Failed while getting response");

  String message;
  String code;

  ErrorResponse(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
