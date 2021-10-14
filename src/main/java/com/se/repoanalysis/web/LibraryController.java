package com.se.repoanalysis.web;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import com.se.repoanalysis.command.GetAllLibrariesCommand;
import com.se.repoanalysis.command.SyncLibrariesCommand;
import com.se.repoanalysis.dto.response.AnalysisResponse;
import com.se.repoanalysis.model.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@RestController("/sync")
public class LibraryController extends BaseController {

  public LibraryController(SchedulerHelper schedulerHelper, CommandExecutor commandExecutor) {
    super(schedulerHelper, commandExecutor);
  }

  @GetMapping("/all")
  public Mono<Response<String>> sync() {
    return commandExecutor.execute(GetAllLibrariesCommand.class, "libraries.csv")
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
