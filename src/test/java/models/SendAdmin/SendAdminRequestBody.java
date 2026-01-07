package models.SendAdmin;

import lombok.Data;

@Data
public class SendAdminRequestBody {

    private String dateofbirth;
    private String personalFirstName;
    private String personalLastName;
    private String personalMiddleName;
    private String personalNumberOfPassport;
    private String personalPhoneNumber;

}
