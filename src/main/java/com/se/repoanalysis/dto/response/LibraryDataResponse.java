package com.se.repoanalysis.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.se.repoanalysis.model.VersionDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author Karan Mehta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class LibraryDataResponse {
  private String name;
  private String description;
  private Map<String, VersionDetail> versions;
  private Map<Object, Object> time;
  private Long downloads;
}
