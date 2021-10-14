package com.se.repoanalysis.dao;

import com.se.repoanalysis.model.ErrorLibrary;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karan Mehta
 */
@Repository
public interface ErrorLibraryRepository extends ReactiveMongoRepository<ErrorLibrary, String> {
}
