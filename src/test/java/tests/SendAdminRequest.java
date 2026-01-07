package tests;

import base.BaseTest;
import endpoints.Endpoints;
import models.SendAdmin.SendAdminRequestBody;
import models.SendAdmin.SendAdminResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;


public class SendAdminRequest extends BaseTest {

    @Test
    public void sendAdminRequest(){

        SendAdminRequestBody request = new SendAdminRequestBody();
        request.setDateofbirth("2025-01-01");
        request.setPersonalFirstName("Ivan");
        request.setPersonalLastName("Ivanov");
        request.setPersonalMiddleName("Petrovich");
        request.setPersonalNumberOfPassport("HG231456");
        request.setPersonalPhoneNumber("80251425358");

        SendAdminResponse response = RequestManager.postRequest(
                RequestSpec.requestSpecification(),
                ResponseSpec.responseSpecification(),
                Endpoints.SENDADMINREQUEST,
                request,
                SendAdminResponse.class);

        assertNotNull(response, "Ответ не должен быть null");
        assertNotNull(response.getRequestId(), "RequestID не должен быть null");
        assertNotNull(response.getData(), "Блок Data не должен быть null");
        assertTrue(response.getData().getStaffid() > 0, "staffid должен быть > 0" );

    }

}
