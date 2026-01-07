package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;
import specs.RequestSpec;
import specs.ResponseSpec;

@UtilityClass
public class RequestManager {

    public static <T> T getRequest(RequestSpecification requestSpecification,
                            ResponseSpecification responseSpecification,
                            String path,
                            Class <T> clazz){
        return
                RestAssured.given()
                        .spec(requestSpecification)
                        .basePath(path)
                        .when()
                        .get()
                        .then()
                        .spec(responseSpecification)
                        .extract().as(clazz);
    }

    public static <T> T getRequest(RequestSpecification requestSpecification,
                                   ResponseSpecification responseSpecification,
                                   String path,
                                   String paramName,
                                   Object id,
                                   Class <T> clazz){
        return
                RestAssured.given()
                        .spec(requestSpecification)
                        .basePath(path)
                        .pathParam(paramName, id)
                        .when()
                        .get()
                        .then()
                        .spec(responseSpecification)
                        .extract().as(clazz);
    }

    public static <T> T postRequest(RequestSpecification requestSpecification,
                                           ResponseSpecification responseSpecification,
                                           String path,
                                           Object request,
                                    Class <T> tclass) {
        return
                RestAssured.given()
                        .spec(requestSpecification)
                        .basePath(path)
                        .body(request)
                        .when()
                        .post()
                        .then()
                        .spec(responseSpecification)
                        .extract().as(tclass);
    }
}
