package models.SendAdmin;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SendAdminRequestBody {

  private String dateofbirth;
  private String personalFirstName;
  private String personalLastName;
  private String personalMiddleName;
  private String personalNumberOfPassport;
  private String personalPhoneNumber;
}
