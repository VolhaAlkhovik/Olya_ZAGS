package tests;

import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import endpoints.Endpoints;
import models.RequestProcess.RequestProcessBody;
import models.RequestProcess.RequestProcessResponse;
import models.SendUser.SendUserRequestBody;
import models.SendUser.SendUserResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

public class RequestProcess extends BaseTest {

  private static int createdApplId;
  private static int createdStaffId;

  /* @Test
    public void sendAdminRequest(){
    SendAdminRequestBody request = new SendAdminRequestBody();
      request.setDateofbirth("2025-01-01");
      request.setPersonalFirstName("Ivan");
      request.setPersonalLastName("Ivanov");
      request.setPersonalMiddleName("Petrovich");
      request.setPersonalNumberOfPassport("HG231456");
      request.setPersonalPhoneNumber("80251425358");

    SendAdminResponse responseAdminRequest =
            RequestManager.postRequest(
                    RequestSpec.requestSpecification(),
                    ResponseSpec.responseSpecification(),
                    Endpoints.SENDADMINREQUEST,
                    request,
                    SendAdminResponse.class);

    assertNotNull(responseAdminRequest, "Ответ не должен быть null");
    createdStaffId = responseAdminRequest.getData().getStaffid();
  }
  */
  @Test
  public void requestprocess() {

    SendUserRequestBody request = SendUserRequestBody.builder()
            .mode("wedding")
            .personalLastName(faker.name().lastName())
            .personalFirstName(faker.name().firstName())
            .personalMiddleName(faker.name().nameWithMiddle())
            .personalPhoneNumber(faker.phoneNumber().phoneNumber())
            .personalNumberOfPassport("GH234345")
            .personalAddress(faker.address().city())
            .dateOfMarriage("2026-06-06")
            .anotherPersonFirstName(faker.name().firstName())
            .anotherPersonLastName(faker.name().lastName())
            .anotherPersonMiddleName(faker.name().nameWithMiddle())
            .anotherPersonPassport("KJ343453")
            .birthOfAnotherPerson("2006-08-06")
            .citizenFirstName(faker.name().firstName())
            .citizenMiddleName(faker.name().nameWithMiddle())
            .citizenBirthDate("2008-05-06")
            .citizenLastName(faker.name().lastName())
            .citizenGender(faker.demographic().sex())
            .newLastName(faker.name().lastName())
            .citizenNumberOfPassport("JF48394")
            .citizenAddress(faker.address().city())
            .build();

    SendUserResponse responseCreatedUser =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SENDUSERREQUEST,
            request,
            SendUserResponse.class);

    assertNotNull(responseCreatedUser, "Ответ не должены быть null");
    createdApplId = responseCreatedUser.getData().getApplicationid();

    RequestProcessBody requestProcess = new RequestProcessBody();
    requestProcess.setApplId(createdApplId);

    RequestProcessResponse response =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.REQUESTPROCESS,
            requestProcess,
            RequestProcessResponse.class);

    assertNotNull(response, "Ответ не должены быть null");
    assertEquals(
        createdApplId, response.getData().getApplicationid(), "ApplicationId не совпадает");
  }
}
