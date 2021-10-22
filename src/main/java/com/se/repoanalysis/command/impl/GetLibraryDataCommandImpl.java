package com.se.repoanalysis.command.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.se.repoanalysis.api.NodeRegistryApiClient;
import com.se.repoanalysis.api.NpmJsApiClient;
import com.se.repoanalysis.command.GetLibraryDataCommand;
import com.se.repoanalysis.dao.ErrorLibraryRepository;
import com.se.repoanalysis.dao.LibraryRepository;
import com.se.repoanalysis.dto.DownloadDataDto;
import com.se.repoanalysis.dto.LibraryDataDto;
import com.se.repoanalysis.model.ErrorLibrary;
import com.se.repoanalysis.model.LibraryData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @author Karan Mehta
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class GetLibraryDataCommandImpl implements GetLibraryDataCommand {
  private final NodeRegistryApiClient nodeRegistryApiClient;
  private final NpmJsApiClient npmJsApiClient;
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
  private final LibraryRepository libraryRepository;
  private final ErrorLibraryRepository errorLibraryRepository;
  private static final String SEPARATOR = ":";
  private static final ZonedDateTime currentDate =
      LocalDate.now().atStartOfDay(ZoneId.systemDefault());

  @Override
  public Mono<LibraryData> execute(String libraryName) {
    return Mono.zip(nodeRegistryApiClient.findLibrary(libraryName),
            npmJsApiClient.getDownloads(getInterval(), libraryName))
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

  private LibraryData convertLibraryDataResponse(Tuple2<LibraryDataDto, DownloadDataDto> objects) {
    LibraryData libraryData = objectMapper.convertValue(objects.getT1(), LibraryData.class);
    if (libraryData != null) {
      libraryData.setDownloads(objects.getT2().getDownloads());
    }
    return libraryData;
  }

  private String getInterval() {
    Date end = Date.from(currentDate.toInstant());
    Date start = Date.from(currentDate.minusYears(1).toInstant());
    String startDate = simpleDateFormat.format(start);
    String endDate = simpleDateFormat.format(end);
    return startDate + SEPARATOR + endDate;
  }
}
