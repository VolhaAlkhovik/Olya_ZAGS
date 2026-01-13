package tests;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import base.BaseTest;
import endpoints.Endpoints;
import models.Application.GetApplicationResponse;
import org.assertj.core.api.SoftAssertions;
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
            Endpoints.GET_APPLICATIONS,
            GetApplicationResponse.class);

    SoftAssertions soft = new SoftAssertions();
    soft.assertThat(response).as("Response data").isNotNull();
    soft.assertThat(response.getTotal()).as("Total count").isGreaterThanOrEqualTo(response.getData().size());
    soft.assertThat(response.getData()).isNotEmpty();
    soft.assertThat(response.getRequestId()).isNotNull();

    var first = response.getData().get(0);
    soft.assertThat(first.getApplicantid()).as("ApplicationId").isGreaterThan(0);
    soft.assertThat(first.getCitizenid()).as("CitizenId").isGreaterThan(0);
    soft.assertThat(first.getApplicantid()).as("ApplicantId").isGreaterThan(0);
    soft.assertThat(first.getKindofapplication()).as("Kind of application").isNotBlank();
    soft.assertThat(first.getStatusofapplication()).as("Status").isIn("under consideration", "approved", "rejected");
    soft.assertThat(first.getDateofapplication()).as("Date format").matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z");
    soft.assertAll();


  }
}
