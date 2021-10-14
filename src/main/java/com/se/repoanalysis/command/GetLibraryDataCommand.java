package com.se.repoanalysis.command;

import com.blibli.oss.backend.command.Command;
import com.se.repoanalysis.model.LibraryData;

/**
 * @author Karan Mehta
 */
public interface GetLibraryDataCommand extends Command<String, LibraryData> {
}
