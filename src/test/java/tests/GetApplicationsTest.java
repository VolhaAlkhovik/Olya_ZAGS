package tests;

import base.BaseTest;
import endpoints.Endpoints;
import models.Application.ApplicationData;
import models.Application.GetApplicationResponse;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class GetApplicationsTest extends BaseTest {

  @Test
  public void getApplications() {
    GetApplicationResponse response =
        RequestManager.getRequest(
            RequestSpec.requestSpecification(),
            ResponseSpec.responseSpecification(),
            Endpoints.GET_APPLICATIONS,
            GetApplicationResponse.class);

    assertAll("Response checks",
            () -> assertNotNull(response, "Response data should not be null"),
            () -> assertNotNull(response.getData(), "Response data list should not be null"),
            () -> assertFalse(response.getData().isEmpty(), "Response data should not be empty"),
            () -> assertNotNull(response.getRequestId(), "RequestId should not be null"),
            () -> assertTrue(response.getTotal() >= response.getData().size(), "Total count should be >= size of data list"),


            () -> response.getData().stream().forEach(applicationData -> assertAll("ApplicationData checks",
            () -> assertTrue(applicationData.getApplicantid() > 0),
            () -> assertTrue(applicationData.getCitizenid() > 0),
            () -> assertTrue(applicationData.getApplicantid() > 0),
            () -> assertNotNull(applicationData.getKindofapplication()),
            () -> assertFalse(applicationData.getKindofapplication().isBlank()),
            () -> assertTrue(List.of("under consideration", "approved", "rejected").contains(applicationData.getStatusofapplication())),
            () -> assertTrue(applicationData.getDateofapplication().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}\\.\\d{3}Z"))
    )
    ));
  }
}
