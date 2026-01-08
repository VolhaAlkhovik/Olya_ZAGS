package models.StatusAppl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplStatusData {

  private String dateofapplication;
  private String kindofapplication;
  private String statusofapplication;
}
