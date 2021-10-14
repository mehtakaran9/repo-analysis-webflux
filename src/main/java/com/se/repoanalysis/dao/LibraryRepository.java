package com.se.repoanalysis.dao;

import com.se.repoanalysis.model.LibraryData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

/**
 * @author Karan Mehta
 */
@Repository
public interface LibraryRepository extends ReactiveMongoRepository<LibraryData, String> {
  Mono<Boolean> existsByName(String name);
}
