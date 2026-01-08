package models.StatusAppl;

import lombok.Data;

@Data
public class GetStatusApplResponse {

  private ApplStatusData data;
  private String requestId;
}
