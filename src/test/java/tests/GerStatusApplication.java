package tests;

import base.BaseTest;
import endpoints.Endpoints;
import models.StatusAppl.GetStatusApplResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class GerStatusApplication extends BaseTest {

    @Test
    public void getStatusApplication(){

        GetStatusApplResponse response = RequestManager.getRequest(
                RequestSpec.requestSpecification(),
        ResponseSpec.responseSpecification(),
        Endpoints.GETSTATUSAPPLICATION,
        "id",
        57534,
        GetStatusApplResponse.class);

        assertNotNull(response, "Ответ не должены быть null");
    }
}
