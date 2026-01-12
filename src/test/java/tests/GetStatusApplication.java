package tests;

import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import builder.SendUserRequestBodyFactory;
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
    SendUserRequestBody body  = SendUserRequestBodyFactory.weddingRequest();

    SendUserResponse responseCreatedUser =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SENDUSERREQUEST,
            body,
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
