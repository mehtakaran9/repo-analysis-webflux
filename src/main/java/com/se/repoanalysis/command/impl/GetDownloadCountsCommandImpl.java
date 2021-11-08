package com.se.repoanalysis.command.impl;

import com.se.repoanalysis.api.DownloadCountsApiClient;
import com.se.repoanalysis.command.GetDownloadCountsCommand;
import com.se.repoanalysis.dao.DownloadCountRepository;
import com.se.repoanalysis.model.DownloadData;
import com.se.repoanalysis.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetDownloadCountsCommandImpl implements GetDownloadCountsCommand {
  private final DownloadCountsApiClient downloadCountsApiClient;
  private static final String from = "2020-01-01";
  private static final String until = "2021-11-07";
  private final DownloadCountRepository downloadCountRepository;

  @Override
  public Mono<Response<String>> execute(String libraryName) {
    return downloadCountsApiClient.getDownloads(from, until, libraryName)
        .switchIfEmpty(Mono.defer(() -> Mono.just(new Object())))
        .filter(ObjectUtils::isEmpty)
        .flatMap(response -> downloadCountRepository.save(new DownloadData(response, libraryName)))
        .thenReturn(new Response<>(Boolean.TRUE.toString()));
  }
}
