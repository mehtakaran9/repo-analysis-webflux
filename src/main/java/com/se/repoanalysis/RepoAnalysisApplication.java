package com.se.repoanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
@ConfigurationPropertiesScan("com.se.repoanalysis")
@EnableMongoAuditing
public class RepoAnalysisApplication {

  public static void main(String[] args) {
    SpringApplication.run(RepoAnalysisApplication.class, args);
  }

}
