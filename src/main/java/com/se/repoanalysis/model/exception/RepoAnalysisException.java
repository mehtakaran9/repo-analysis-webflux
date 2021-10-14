package com.se.repoanalysis.model.exception;

import com.se.repoanalysis.model.enums.ErrorResponse;
import lombok.Getter;

/**
 * @author Karan Mehta
 */
@Getter
public class RepoAnalysisException extends RuntimeException {
  private static final long serialVersionUID = -3984528860014118112L;

  private final String errorCode;
  private final String errorMessage;

  public RepoAnalysisException() {
    super(ErrorResponse.UNSPECIFIED.getMessage());
    this.errorCode = ErrorResponse.UNSPECIFIED.getCode();
    this.errorMessage = ErrorResponse.UNSPECIFIED.getMessage();
  }

  public RepoAnalysisException(ErrorResponse errorResponse) {
    super(errorResponse.getMessage());
    this.errorCode = errorResponse.getCode();
    this.errorMessage = errorResponse.getMessage();
  }

  public RepoAnalysisException(String errorCode, String errorMessage) {
    super(errorMessage);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  @Override
  public String toString() {
    return String.format("RepoAnalysisException [errorCode=%s, errorMessage=%s, toString()=%s]",
        this.errorCode,
        this.errorMessage,
        super.toString());
  }
}
