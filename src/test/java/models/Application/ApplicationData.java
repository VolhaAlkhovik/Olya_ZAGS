package models.Application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationData {

  private Integer applicationid;
  private Integer citizenid;
  private Integer applicantid;
  private Integer staffid;
  private String dateofapplication;
  private String kindofapplication;
  private String statusofapplication;
  private String chanel;
  private String image;
}
