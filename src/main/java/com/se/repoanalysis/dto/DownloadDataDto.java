package com.se.repoanalysis.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Karan Mehta
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadDataDto {
  private Long downloads;
  private String start;
  private String end;
  @JsonProperty(value = "package")
  private String packageName;

  @Override
  public String toString() {
    return "DownloadDataDto{" + "downloads=" + downloads + ", start='" + start + '\'' + ", end='"
        + end + '\'' + ", packageName='" + packageName + '\'' + '}';
  }
}
