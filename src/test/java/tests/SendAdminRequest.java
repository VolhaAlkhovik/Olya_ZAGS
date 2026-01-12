package tests;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import endpoints.Endpoints;
import models.SendAdmin.SendAdminRequestBody;
import models.SendAdmin.SendAdminResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

public class SendAdminRequest extends BaseTest {

  @Test
  public void sendAdminRequest() {

    SendAdminRequestBody request = SendAdminRequestBody.builder()
                    .dateofbirth("2025-01-01")
                    .personalFirstName(faker.name().firstName())
                    .personalLastName(faker.name().lastName())
                    .personalMiddleName(faker.name().nameWithMiddle())
                    .personalNumberOfPassport("HG231456")
                    .personalPhoneNumber(faker.phoneNumber().phoneNumber())
                    .build();

    SendAdminResponse response =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SENDADMINREQUEST,
            request,
            SendAdminResponse.class);

    assertNotNull(response, "Ответ не должен быть null");
    assertNotNull(response.getRequestId(), "RequestID не должен быть null");
    assertNotNull(response.getData(), "Блок Data не должен быть null");
    assertTrue(response.getData().getStaffid() > 0, "staffid должен быть > 0");
  }
}
