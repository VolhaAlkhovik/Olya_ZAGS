package tests;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import endpoints.Endpoints;
import io.restassured.response.Response;
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
                    .personalMiddleName(faker.name().firstName())
                    .personalNumberOfPassport("GF323842")
                    .personalPhoneNumber("2365988")
                    .build();

    SendAdminResponse response =

        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SEND_ADMIN_REQUEST,
            request,
            SendAdminResponse.class);


    assertNotNull(response, "Ответ не должен быть null");
    assertNotNull(response.getRequestId(), "RequestID не должен быть null");
    assertNotNull(response.getData(), "Блок Data не должен быть null");
    assertTrue(response.getData().getStaffid() > 0, "staffid должен быть > 0");
  }
}
