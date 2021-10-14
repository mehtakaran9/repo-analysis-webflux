package com.se.repoanalysis.command;

import com.blibli.oss.backend.command.Command;
import com.se.repoanalysis.model.Response;

/**
 * @author Karan Mehta
 */
public interface SyncLibrariesCommand extends Command<String, Response<Boolean>> {
}
