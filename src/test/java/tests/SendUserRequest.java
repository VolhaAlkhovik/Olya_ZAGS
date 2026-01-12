package tests;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import endpoints.Endpoints;
import models.SendUser.SendUserRequestBody;
import models.SendUser.SendUserResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

public class SendUserRequest extends BaseTest {

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

    SendUserResponse response =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SENDUSERREQUEST,
            request,
            SendUserResponse.class);

    assertNotNull(response, "Ответ не должен быть null");
  }
}
