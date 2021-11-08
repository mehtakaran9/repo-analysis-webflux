package com.se.repoanalysis.web;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import com.se.repoanalysis.command.GetAllLibrariesCommand;
import com.se.repoanalysis.command.GetDownloadCountsCommand;
import com.se.repoanalysis.command.GetLibraryDataCommand;
import com.se.repoanalysis.command.SyncLibrariesCommand;
import com.se.repoanalysis.dto.response.AnalysisResponse;
import com.se.repoanalysis.model.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@RestController("/sync")
public class LibraryController extends BaseController {

  @Data
  @AllArgsConstructor
  public static class FileCommandRequest<T> {
    private Class<T> aClass;
    private String file;
  }

  public LibraryController(SchedulerHelper schedulerHelper, CommandExecutor commandExecutor) {
    super(schedulerHelper, commandExecutor);
  }

  @GetMapping("/all")
  public Mono<Response<String>> sync() {
    return commandExecutor.execute(GetAllLibrariesCommand.class,
            new FileCommandRequest<>(GetLibraryDataCommand.class, "libraries.csv"))
        .subscribeOn(schedulerHelper.of(WEB));
  }

  @GetMapping("/download-counts")
  public Mono<Response<String>> downloadCounts() {
    return commandExecutor.execute(GetAllLibrariesCommand.class,
            new FileCommandRequest(GetDownloadCountsCommand.class, "packages_new.csv"))
        .subscribeOn(schedulerHelper.of(WEB));
  }

  @GetMapping("/resync")
  public Mono<Response<Boolean>> resync() {
    return commandExecutor.execute(SyncLibrariesCommand.class, "libraries.csv")
        .subscribeOn(schedulerHelper.of(WEB));
  }

  @GetMapping("/report")
  public AnalysisResponse getAnalysisResponse() {
    return null;
  }
}
