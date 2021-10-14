package com.se.repoanalysis.api;

import com.blibli.oss.backend.apiclient.annotation.ApiClient;
import com.se.repoanalysis.api.fallback.NodeRegistryApiClientFallback;
import com.se.repoanalysis.dto.LibraryDataDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@Component
@ApiClient(name = "npmClient", fallback = NodeRegistryApiClientFallback.class)
public interface NodeRegistryApiClient {

  @RequestMapping(value = "/{libraryName}", method = RequestMethod.GET,
      produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
  Mono<LibraryDataDto> findLibrary(@PathVariable("libraryName") String libraryName);

}
