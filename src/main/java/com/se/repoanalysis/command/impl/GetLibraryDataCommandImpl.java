package com.se.repoanalysis.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.repoanalysis.api.NodeRegistryApiClient;
import com.se.repoanalysis.command.GetLibraryDataCommand;
import com.se.repoanalysis.dao.ErrorLibraryRepository;
import com.se.repoanalysis.dao.LibraryRepository;
import com.se.repoanalysis.dto.LibraryDataDto;
import com.se.repoanalysis.model.ErrorLibrary;
import com.se.repoanalysis.model.LibraryData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class GetLibraryDataCommandImpl implements GetLibraryDataCommand {
  private final NodeRegistryApiClient nodeRegistryApiClient;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final LibraryRepository libraryRepository;
  private final ErrorLibraryRepository errorLibraryRepository;

  @Override
  public Mono<LibraryData> execute(String libraryName) {
    return nodeRegistryApiClient.findLibrary(libraryName)
        .map(this::convertLibraryDataResponse)
        .filter(libraryData -> !ObjectUtils.isEmpty(libraryData))
        .flatMap(libraryRepository::save)
        .doOnError(err -> {
          log.error("Could not get data for library {} due to exception {}",
              libraryName,
              ExceptionUtils.getStackTrace(err));
          errorLibraryRepository.save(new ErrorLibrary(libraryName));
        });
  }

  private LibraryData convertLibraryDataResponse(LibraryDataDto libraryDataDto) {
    return objectMapper.convertValue(libraryDataDto, LibraryData.class);
  }
}
