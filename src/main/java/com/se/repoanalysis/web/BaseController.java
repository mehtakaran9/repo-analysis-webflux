package com.se.repoanalysis.web;

import com.blibli.oss.backend.command.executor.CommandExecutor;
import com.blibli.oss.backend.reactor.scheduler.SchedulerHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

/**
 * @author Karan Mehta
 */
@Controller
@RequiredArgsConstructor
public class BaseController {
  public static final String SYNC = "SYNC";
  public static final String WEB = "WEB";
  protected final SchedulerHelper schedulerHelper;
  protected final CommandExecutor commandExecutor;
}
