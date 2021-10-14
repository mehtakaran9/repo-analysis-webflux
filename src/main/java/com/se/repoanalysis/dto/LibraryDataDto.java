package com.se.repoanalysis.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.se.repoanalysis.model.VersionDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karan Mehta
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibraryDataDto {
  private String name;
  private String description;
  private Map<String, VersionDetail> versions = new HashMap<>();
  private Map<Object, Object> time = new HashMap<>();

  @Override
  public String toString() {
    return "LibraryDataDto{" + "name='" + name + '\'' + ", description='" + description + '\''
        + ", versions=" + versions + ", time=" + time + '}';
  }
}
