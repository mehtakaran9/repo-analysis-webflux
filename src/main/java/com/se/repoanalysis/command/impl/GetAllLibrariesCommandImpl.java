package com.se.repoanalysis.command.impl;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import com.se.repoanalysis.command.GetAllLibrariesCommand;
import com.se.repoanalysis.model.Response;
import com.se.repoanalysis.web.LibraryController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static com.se.repoanalysis.web.BaseController.SYNC;

/**
 * @author Karan Mehta
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class GetAllLibrariesCommandImpl implements GetAllLibrariesCommand {
  public static final String ACCEPTED = "ACCEPTED";
  private final SchedulerHelper schedulerHelper;
  private final CommandExecutor commandExecutor;

  @SuppressWarnings("unchecked")
  @Override
  public Mono<Response<String>> execute(LibraryController.FileCommandRequest request) {
    return Flux.fromStream(new BufferedReader(new InputStreamReader(Objects.requireNonNull(this.getClass()
            .getClassLoader()
            .getResourceAsStream(request.getFile())), StandardCharsets.UTF_8)).lines())
        .flatMap(libraryName -> commandExecutor.execute(request.getAClass(),
                libraryName) //change the command class based on what you want to execute
            .subscribeOn(schedulerHelper.of(SYNC)))
        .then(Mono.fromCallable(() -> new Response<>(ACCEPTED)));
  }

}
