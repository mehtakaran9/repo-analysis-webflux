package com.se.repoanalysis.api;

import com.blibli.oss.backend.apiclient.annotation.ApiClient;
import com.se.repoanalysis.api.fallback.DownloadCountsApiFallback;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@Component
@ApiClient(name = "downloadCountsApi", fallback = DownloadCountsApiFallback.class)
public interface DownloadCountsApiClient {
  @RequestMapping(method = RequestMethod.GET, value = "/api/download-counts")
  Mono<Object> getDownloads(@RequestParam(value = "from") String from,
      @RequestParam(value = "until") String until,
      @RequestParam(value = "package") String packageName);
}
