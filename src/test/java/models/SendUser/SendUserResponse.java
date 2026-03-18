package models.SendUser;

import lombok.Data;

@Data
public class SendUserResponse {

  private SendUser data;
  private String requestId;
}
