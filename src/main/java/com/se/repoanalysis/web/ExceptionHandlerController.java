package com.se.repoanalysis.web;

import com.se.repoanalysis.model.Response;
import com.se.repoanalysis.model.exception.RepoAnalysisException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Karan Mehta
 */
@RestControllerAdvice
@Slf4j
@SuppressWarnings("rawtypes")
public class ExceptionHandlerController {
  @ExceptionHandler({RepoAnalysisException.class})
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  Response handleApplicationException(RepoAnalysisException applicationException) {
    log.error(RepoAnalysisException.class.getName(),
        ExceptionUtils.getStackTrace(applicationException));
    return new Response(applicationException.getErrorCode(),
        applicationException.getErrorMessage());
  }


}
