package com.se.repoanalysis.api;

import com.blibli.oss.backend.apiclient.annotation.ApiClient;
import com.se.repoanalysis.api.fallback.NpmJsApiClientFallback;
import com.se.repoanalysis.dto.DownloadDataDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@Component
@ApiClient(name = "npmJsApi", fallback = NpmJsApiClientFallback.class)
public interface NpmJsApiClient {
  @RequestMapping(method = RequestMethod.GET, value = "/downloads/point/{interval}/{packageName}")
  Mono<DownloadDataDto> getDownloads(@PathVariable(value = "interval") String interval,
      @PathVariable(value = "package") String packageName);
}
