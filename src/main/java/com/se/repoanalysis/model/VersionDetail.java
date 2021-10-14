package com.se.repoanalysis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Karan Mehta
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VersionDetail {
  private String version;
  private String deprecated;
  private Map<String, Object> dependencies;
  private Map<String, Object> devDependencies;
}
