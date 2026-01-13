package tests;

import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import builder.SendUserRequestBodyFactory;
import endpoints.Endpoints;
import models.SendUser.SendUserRequestBody;
import models.SendUser.SendUserResponse;
import models.StatusAppl.GetStatusApplResponse;
import org.assertj.core.api.SoftAssertions;
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
            Endpoints.SEND_USER_REQUEST,
            body,
            SendUserResponse.class);

    assertNotNull(responseCreatedUser, "Ответ не должены быть null");
    assertNotNull(responseCreatedUser.getData(),"Data не должно быть null");
    assertTrue(responseCreatedUser.getData().getApplicationid() > 0, "ApplicationId должен быть > 0");
    createdApplId = responseCreatedUser.getData().getApplicationid();
  }

  public void getStatusApplication() {

    GetStatusApplResponse response =
        RequestManager.getRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.GET_STATUS_APPLICATION,
            "id",
            createdApplId,
            GetStatusApplResponse.class);

    SoftAssertions soft = new SoftAssertions();
    soft.assertThat(response).isNotNull();
    soft.assertThat(response.getData()).isNotNull();
    soft.assertThat(response.getData().getKindofapplication()).isEqualTo("Получение свидетельства о браке");
    soft.assertThat(response.getData().getStatusofapplication()).isIn("under consideration", "approved", "rejected");
    soft.assertThat(response.getData().getDateofapplication()).matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z");
    soft.assertAll();
  }
}
