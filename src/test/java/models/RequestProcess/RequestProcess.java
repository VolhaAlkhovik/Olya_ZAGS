package models.RequestProcess;

import lombok.Data;

@Data
public class RequestProcess {

  private Integer applicantid;
  private Integer applicationid;
  private Integer citizenid;
  private String dateofapplication;
  private String kindofapplication;
  private String statusofapplication;
  private Integer staffid;
  private String channel;
  private String image;
}
