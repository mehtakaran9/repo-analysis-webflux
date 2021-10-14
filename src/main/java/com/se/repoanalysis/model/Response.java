package com.se.repoanalysis.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.se.repoanalysis.model.enums.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Karan Mehta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude
public class Response<T> {
  public static final String SUCCESS_RESULT = "true";
  public static final String FAILURE_RESULT = "false";
  private static final long serialVersionUID = -4654551389913357345L;

  private String result;
  private T resultData;
  private String errorCode;
  private String errorDesc;

  public Response(T resultData) {
    this.resultData = resultData;
    this.result = SUCCESS_RESULT;
  }

  public Response(T resultData, ErrorResponse errorResponse) {
    this.resultData = resultData;
    this.errorDesc = errorResponse.getMessage();
    this.errorCode = errorResponse.getCode();
    this.result = FAILURE_RESULT;
  }

  public Response(String errorCode, String errorDesc) {
    this.errorCode = errorCode;
    this.errorDesc = errorDesc;
    this.result = FAILURE_RESULT;
  }

  public Response(T resultData, String errorCode, String errorDesc) {
    this.resultData = resultData;
    this.errorCode = errorCode;
    this.errorDesc = errorDesc;
    this.result = FAILURE_RESULT;
  }

  public Response(ErrorResponse errorResponse) {
    this.errorDesc = errorResponse.getMessage();
    this.errorCode = errorResponse.getCode();
    this.result = FAILURE_RESULT;
  }

  public void setErrorCode(ErrorResponse errorResponse) {
    this.errorCode = errorResponse.getCode();
  }

  public void setErrorDesc(ErrorResponse errorResponse) {
    this.errorDesc = errorResponse.getMessage();
  }

  public void setError(String errorCode, String errorDesc) {
    this.errorCode = errorCode;
    this.errorDesc = errorDesc;
    this.result = FAILURE_RESULT;
  }
}
