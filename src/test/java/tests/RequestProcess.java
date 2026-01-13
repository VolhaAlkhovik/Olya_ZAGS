package tests;

import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import builder.SendUserRequestBodyFactory;
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

  @Test
  public void requestprocess() {

    SendUserRequestBody body = SendUserRequestBodyFactory.weddingRequest();

    SendUserResponse responseCreatedUser =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SEND_USER_REQUEST,
            body,
            SendUserResponse.class);

    assertNotNull(responseCreatedUser, "Ответ не должены быть null");
    assertNotNull(responseCreatedUser.getData(),"Data не должно быть null");
    assertTrue(responseCreatedUser.getData().getApplicationid() > 0, "ApplicationId должен быть > 0");
    createdApplId = responseCreatedUser.getData().getApplicationid();

    RequestProcessBody requestProcess = new RequestProcessBody();
    requestProcess.setApplId(createdApplId);

    RequestProcessResponse response =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.REQUEST_PROCESS,
            requestProcess,
            RequestProcessResponse.class);

    assertNotNull(response, "Ответ не должены быть null");
    assertEquals(
        createdApplId, response.getData().getApplicationid(), "ApplicationId не совпадает");
  }
}
