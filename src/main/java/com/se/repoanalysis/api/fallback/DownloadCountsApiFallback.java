package com.se.repoanalysis.api.fallback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@Component
@Slf4j
public class DownloadCountsApiFallback {
  public Mono<Object> getDownloads(String from,
      String until,
      String packageName,
      Throwable throwable) {
    log.error("Could not get the download counts for the npm stat {}", packageName);
    return Mono.empty();
  }
}
