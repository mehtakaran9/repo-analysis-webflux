package com.se.repoanalysis.api.fallback;

import com.se.repoanalysis.dto.DownloadDataDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@Component
@Slf4j
public class NpmJsApiClientFallback {
  public Mono<DownloadDataDto> getDownloads(String interval,
      String packageName,
      Throwable throwable) {
    log.error("Could not get the download for the package {} due to {}",
        packageName,
        ExceptionUtils.getStackTrace(throwable));
    return Mono.empty();
  }
}
