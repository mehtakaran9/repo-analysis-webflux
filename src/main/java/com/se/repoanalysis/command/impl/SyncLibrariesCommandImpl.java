package com.se.repoanalysis.command.impl;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import com.se.repoanalysis.command.GetLibraryDataCommand;
import com.se.repoanalysis.command.SyncLibrariesCommand;
import com.se.repoanalysis.dao.LibraryRepository;
import com.se.repoanalysis.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author Karan Mehta
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SyncLibrariesCommandImpl implements SyncLibrariesCommand {
  public static final String ACCEPTED = "ACCEPTED";
  private final SchedulerHelper schedulerHelper;
  private final CommandExecutor commandExecutor;
  private final LibraryRepository libraryRepository;

  @Override
  public Mono<Response<Boolean>> execute(String request) {
    return Flux.fromStream(new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass()
            .getClassLoader()
            .getResourceAsStream(request)), StandardCharsets.UTF_8)).lines())
        .flatMap(this::doesLibraryExist)
        .flatMap(libraryName -> {
          if (!StringUtils.isEmpty(libraryName)) {
            log.warn("Library not present in the database yet: {}", libraryName);
            return commandExecutor.execute(GetLibraryDataCommand.class, libraryName);
          } else {
            return Mono.just(new Response<>(true));
          }
        })
        .then(Mono.fromCallable(() -> new Response<>(true)));
  }

  private Mono<String> doesLibraryExist(String line) {
    return libraryRepository.existsByName(line).map(aBoolean -> {
      if (!BooleanUtils.isTrue(aBoolean)) {
        return line;
      } else {
        return StringUtils.EMPTY;
      }
    });
  }
}
