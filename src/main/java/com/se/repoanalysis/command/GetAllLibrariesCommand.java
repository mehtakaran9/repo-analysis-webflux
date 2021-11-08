package com.se.repoanalysis.command;

import com.blibli.oss.backend.command.Command;
import com.se.repoanalysis.model.Response;
import com.se.repoanalysis.web.LibraryController;

/**
 * @author Karan Mehta
 */
public interface GetAllLibrariesCommand
    extends Command<LibraryController.FileCommandRequest, Response<String>> {
}
