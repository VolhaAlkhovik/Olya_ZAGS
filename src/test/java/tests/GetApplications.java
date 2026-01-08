package tests;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import endpoints.Endpoints;
import models.Application.GetApplicationResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

public class GetApplications extends BaseTest {

  @Test
  public void getApplications() {
    GetApplicationResponse response =
        RequestManager.getRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.GETAPPLICATIONS,
            GetApplicationResponse.class);

    assertNotNull(response, "Ответ не должены быть null");
    assertNotNull(response.getRequestId(), "RequestId is null");
    assertNotNull(response.getData(), "Список не должен быть null");
    assertFalse(response.getData().isEmpty(), "Список не должен быть пустой");
    assertTrue(response.getTotal() > 0, "Должно быть больше 0");
  }
}
