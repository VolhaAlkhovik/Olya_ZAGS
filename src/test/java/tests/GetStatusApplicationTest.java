package tests;

import static org.junit.jupiter.api.Assertions.*;
import static utils.JdbcConnection.connectionToDB;

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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GetStatusApplicationTest extends BaseTest {

  private static int createdApplId;

  @Test
  public void sendUserRequest() {
    SendUserRequestBody body = SendUserRequestBodyFactory.weddingRequest();

    SendUserResponse responseCreatedUser =
            RequestManager.postRequest(
                    RequestSpec.requestSpecification(),
                    ResponseSpec.responseSpecification(),
                    Endpoints.SEND_USER_REQUEST,
                    body,
                    SendUserResponse.class);

    assertNotNull(responseCreatedUser, "Ответ не должены быть null");
    assertNotNull(responseCreatedUser.getData(), "Data не должно быть null");
    assertTrue(responseCreatedUser.getData().getApplicationid() > 0, "ApplicationId должен быть > 0");
    createdApplId = responseCreatedUser.getData().getApplicationid();

  }

  @Test

  public void getStatusApplication() throws SQLException {

    GetStatusApplResponse response =
            RequestManager.getRequest(
                    RequestSpec.requestSpecification(),
                    ResponseSpec.responseSpecification(),
                    Endpoints.GET_STATUS_APPLICATION,
                    "id",
                    createdApplId,
                    GetStatusApplResponse.class);

    assertAll (
            () -> assertNotNull(response),
            () -> assertNotNull(response.getData()),
            () -> assertEquals(response.getData().getKindofapplication(), "Получение свидетельства о браке"),
            () -> assertTrue(List.of("under consideration", "approved", "rejected").contains(response.getData().getStatusofapplication())),
            () -> assertTrue(response.getData().getDateofapplication().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"))
            );

    long applicationId = createdApplId;

    String selectQuery = "SELECT applicationid, statusofapplication FROM reg_office.applications WHERE applicationid = ?";

    try (PreparedStatement pstmt = connectionToDB().prepareStatement(selectQuery)) {
      pstmt.setLong(1, applicationId);
      ResultSet rs = pstmt.executeQuery();

      assertTrue(rs.next(), "запись с application_id не найдена в БД");
      assertEquals(applicationId, rs.getLong("applicationid"));
      assertEquals("under consideration", rs.getString("statusofapplication"));
    }
  }
}
