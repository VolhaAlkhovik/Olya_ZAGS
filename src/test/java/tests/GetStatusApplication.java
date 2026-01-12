package tests;

import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import endpoints.Endpoints;
import models.SendUser.SendUserRequestBody;
import models.SendUser.SendUserResponse;
import models.StatusAppl.GetStatusApplResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

public class GetStatusApplication extends BaseTest {

  private static int createdApplId;

  @Test
  public void sendUserRequest() {
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
  }

  @Test
  public void getStatusApplication() {

    GetStatusApplResponse response =
        RequestManager.getRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.GETSTATUSAPPLICATION,
            "id",
            createdApplId,
            GetStatusApplResponse.class);

    assertNotNull(response, "Ответ не должены быть null");
  }
}
