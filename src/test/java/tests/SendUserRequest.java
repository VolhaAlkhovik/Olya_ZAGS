package tests;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import builder.SendUserRequestBodyFactory;
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

      SendUserRequestBody body = SendUserRequestBodyFactory.weddingRequest();

        SendUserResponse response =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SENDUSERREQUEST,
            body,
            SendUserResponse.class);

    assertNotNull(response, "Ответ не должен быть null");
  }
}
