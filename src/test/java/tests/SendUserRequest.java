package tests;

import base.BaseTest;
import endpoints.Endpoints;
import models.SendUser.SendUserRequestBody;
import models.SendUser.SendUserResponse;
import org.junit.jupiter.api.Test;
import specs.RequestSpec;
import specs.ResponseSpec;
import utils.RequestManager;

import static io.restassured.RestAssured.*;

public class SendUserRequest extends BaseTest {

    @Test
    public void sendUserRequest(){

        SendUserRequestBody request = new SendUserRequestBody();
        request.setMode("wedding");
        request.setPersonalLastName("Ivanov");
        request.setPersonalMiddleName("Sergeevich");
        request.setPersonalPhoneNumber("6925456");
        request.setPersonalNumberOfPassport("GH234345");
        request.setAnotherPersonFirstName("Irina");
        request.setAnotherPersonLastName("Petrova");
        request.setAnotherPersonMiddleName("Ivanovna");
        request.setAnotherPersonPassport("GH234345");
        request.setBirth_of_anotoherPerson("2006-08-06");
        request.setCitizenFirstName("Volodya");
        request.setCitizenMiddleName("Petrovich");
        request.setCitizenBirthDate("2008-05-06");
        request.setCitizenLastName("Petrov");
        request.setCitizenGender("male");
        request.setNewLastName("testLastName");
        request.setCitizenNumberOfPassport("2009-06-06");

        SendUserResponse response = RequestManager.postRequest(
                RequestSpec.requestSpecification(),
                ResponseSpec.responseSpecification(),
                Endpoints.SENDUSERREQUEST,
                request,
                SendUserResponse.class);
    }
}
