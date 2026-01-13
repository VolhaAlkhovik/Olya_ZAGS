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
import org.assertj.core.api.SoftAssertions;


public class SendUserRequest extends BaseTest {

  @Test
  public void sendUserRequest() {

      SendUserRequestBody body = SendUserRequestBodyFactory.weddingRequest();

        SendUserResponse response =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SEND_USER_REQUEST,
            body,
            SendUserResponse.class);

        SoftAssertions soft = new SoftAssertions();
        soft.assertThat(response).as("Response object").isNotNull();
        soft.assertThat(response.getData()).as("Data block").isNotNull();
        soft.assertThat(response.getData().getApplicantid()).isGreaterThan(0);
        soft.assertThat(response.getData().getCitizenid()).isGreaterThan(0);
        soft.assertThat(response.getData().getApplicationid()).isGreaterThan(0);
        soft.assertThat(response.getData().getMerrigecertificateid()).isGreaterThan(0);
        soft.assertAll();
  }
}
