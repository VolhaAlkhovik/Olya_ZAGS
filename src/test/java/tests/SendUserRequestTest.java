package tests;

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
import static org.junit.jupiter.api.Assertions.*;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static utils.JdbcConnection.connectionToDB;


public class SendUserRequestTest extends BaseTest {

  @Test
  public void sendUserRequest() throws SQLException {

      SendUserRequestBody body = SendUserRequestBodyFactory.weddingRequest();

        SendUserResponse response =
        RequestManager.postRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.SEND_USER_REQUEST,
            body,
            SendUserResponse.class);

        assertAll(
                () -> assertNotNull(response, "Response object"),
                () -> assertNotNull(response,"Data block"),
                () -> assertTrue(response.getData().getApplicantid() > 0),
                () -> assertTrue(response.getData().getCitizenid() > 0),
                () -> assertTrue(response.getData().getApplicationid() > 0),
                () -> assertTrue(response.getData().getMerrigecertificateid() > 0)
        );

        long applicationId = response.getData().getApplicationid();
        long citizenid = response.getData().getCitizenid();
        long merrigecertificateid = response.getData().getMerrigecertificateid();

        String sqlQuery = "SELECT a.applicationid, m.citizenid, m.merrigecertificateid, m.dateofmerrige, m.surnameofspouse, m.nameofspouse" +
                " FROM reg_office.merrigecertificates m " +
                "JOIN reg_office.applications a " +
                "ON a.citizenid = m.citizenid " +
                "WHERE a.applicationid = ?";

        try(PreparedStatement pstmt = connectionToDB().prepareStatement(sqlQuery)) {
          pstmt.setLong(1, applicationId);
          ResultSet rs = pstmt.executeQuery();
          assertTrue(rs.next(), "Запись applicationid не найдена в БД");
          assertEquals(citizenid, rs.getLong("citizenid"));
          assertEquals(merrigecertificateid, rs.getLong("merrigecertificateid"));

          assertAll(
                  () -> assertEquals(body.getDateOfMarriage(), rs.getString("dateofmerrige")),
                  () -> assertEquals(body.getAnotherPersonLastName(), rs.getString("surnameofspouse")),
                  () -> assertEquals(body.getAnotherPersonFirstName(), rs.getString("nameofspouse"))
          );

        }
  }
}
