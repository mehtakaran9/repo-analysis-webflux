package com.se.repoanalysis.api.fallback;

import com.se.repoanalysis.dto.LibraryDataDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@Component
@Slf4j
public class NodeRegistryApiClientFallback {

  public Mono<LibraryDataDto> findLibrary(String libraryName, Throwable throwable) {
    log.error("Could not find library data for library {} due to exception {}",
        libraryName,
        ExceptionUtils.getStackTrace(throwable));
    return Mono.empty();
  }
}
