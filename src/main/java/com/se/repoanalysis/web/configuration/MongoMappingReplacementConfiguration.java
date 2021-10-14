package com.se.repoanalysis.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

/**
 * @author Karan Mehta
 */
@Configuration
public class MongoMappingReplacementConfiguration extends AbstractReactiveMongoConfiguration {

  @Value("${spring.data.mongodb.database}")
  private String databaseName;

  @Autowired
  void setMapKeyDotReplacement(MappingMongoConverter mappingMongoConverter) {
    mappingMongoConverter.setMapKeyDotReplacement("_");
  }

  @Override
  protected String getDatabaseName() {
    return databaseName;
  }
}
