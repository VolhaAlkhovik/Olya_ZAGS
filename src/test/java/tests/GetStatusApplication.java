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
    SendUserRequestBody request = new SendUserRequestBody();
    request.setMode("wedding");
    request.setPersonalLastName("Ivanov");
    request.setPersonalFirstName("Ivan");
    request.setPersonalMiddleName("Sergeevich");
    request.setPersonalPhoneNumber("6925456");
    request.setPersonalNumberOfPassport("GH234345");
    request.setPersonalAddress("Chicago");
    request.setDateOfMarriage("2026-06-06");
    request.setAnotherPersonFirstName("Irina");
    request.setAnotherPersonLastName("Petrova");
    request.setAnotherPersonMiddleName("Ivanovna");
    request.setAnotherPersonPassport("KJ343453");
    request.setBirthOfAnotherPerson("2006-08-06");
    request.setCitizenFirstName("Volodya");
    request.setCitizenMiddleName("Petrovich");
    request.setCitizenBirthDate("2008-05-06");
    request.setCitizenLastName("Petrov");
    request.setCitizenGender("male");
    request.setNewLastName("testLastName");
    request.setCitizenNumberOfPassport("JF48394");
    request.setCitizenAddress("Belgium");

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
