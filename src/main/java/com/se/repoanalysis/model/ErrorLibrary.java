package com.se.repoanalysis.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Karan Mehta
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "error_libraries")
public class ErrorLibrary implements Serializable {
  public static final String MARK_FOR_DELETE = "markForDelete";
  public static final String UPDATED_DATE = "updatedDate";
  public static final String CREATED_DATE = "createdDate";
  public static final String VERSION = "version";
  public static final String CREATED_BY = "createdBy";
  public static final String UPDATED_BY = "updatedBy";
  public static final long serialVersionUID = 925357614558851189L;

  @Id
  private String id;

  @Version
  @Field(value = VERSION)
  private Long version;

  @CreatedDate
  @Field(value = CREATED_DATE)
  private Date createdDate;

  @LastModifiedDate
  @Field(value = UPDATED_DATE)
  private Date updatedDate;

  private String name;

  public ErrorLibrary(String name) {
    this.name = name;
  }
}
