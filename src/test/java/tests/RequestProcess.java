package tests;

import static org.junit.jupiter.api.Assertions.*;

import endpoints.Endpoints;
import models.RequestProcess.RequestProcessBody;
import models.RequestProcess.RequestProcessResponse;
import models.SendUser.SendUserRequestBody;
import models.SendUser.SendUserResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

public class RequestProcess {

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
