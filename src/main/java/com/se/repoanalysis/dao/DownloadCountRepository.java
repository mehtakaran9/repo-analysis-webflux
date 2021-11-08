package com.se.repoanalysis.dao;

import com.se.repoanalysis.model.DownloadData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Karan Mehta
 */
@Repository
public interface DownloadCountRepository extends ReactiveMongoRepository<DownloadData, String> {
}
