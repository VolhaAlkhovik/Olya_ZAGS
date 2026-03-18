package tests;

import static org.junit.jupiter.api.Assertions.*;
import static utils.JdbcConnection.connectionToDB;

import base.BaseTest;
import endpoints.Endpoints;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.SendAdmin.SendAdminRequestBody;
import models.SendAdmin.SendAdminResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

public class SendAdminRequestTest extends BaseTest {

  @Test
  public void sendAdminRequest() throws SQLException {

    SendAdminRequestBody request =
        SendAdminRequestBody.builder()
            .dateofbirth("2025-01-01")
            .personalFirstName(getFaker().name().firstName())
            .personalLastName(getFaker().name().lastName())
            .personalMiddleName(getFaker().name().firstName())
            .personalNumberOfPassport("GF323842")
            .personalPhoneNumber("2365988")
            .build();

    SendAdminResponse response =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SEND_ADMIN_REQUEST,
            request,
            SendAdminResponse.class);

    assertNotNull(response, "Ответ не должен быть null");
    assertNotNull(response.getRequestId(), "RequestID не должен быть null");
    assertNotNull(response.getData(), "Блок Data не должен быть null");
    assertTrue(response.getData().getStaffid() > 0, "staffid должен быть > 0");

    long staffId = response.getData().getStaffid();

    String sqlQuery = "SELECT * FROM reg_office.staff WHERE staffid = ?";

    try (PreparedStatement pstmt = connectionToDB().prepareStatement(sqlQuery)) {
      pstmt.setLong(1, staffId);
      ResultSet rs = pstmt.executeQuery();

      assertTrue(rs.next(), "Запись со staffid не найдена в БД");
      assertEquals(staffId, rs.getLong("staffid"));

      assertAll(
          "Проверка данных staffid",
          () -> assertEquals(request.getPersonalFirstName(), rs.getString("name")),
          () -> assertEquals(request.getPersonalLastName(), rs.getString("surname")),
          () -> assertEquals(request.getPersonalMiddleName(), rs.getString("middlename")),
          () -> assertEquals(request.getDateofbirth(), rs.getString("dateofbirth")),
          () -> assertEquals(request.getPersonalPhoneNumber(), rs.getString("phonenumber")),
          () ->
              assertEquals(request.getPersonalNumberOfPassport(), rs.getString("passportnumber")));
    }
  }
}
