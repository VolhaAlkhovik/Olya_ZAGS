package models.Application;

import java.util.List;
import lombok.Getter;

@Getter
public class GetApplicationResponse {

  private Integer total;
  private List<ApplicationData> data;
  private String requestId;
}
