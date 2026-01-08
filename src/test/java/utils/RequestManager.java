package utils;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestManager {

  public static <T> T getRequest(
      final RequestSpecification requestSpecification,
      final ResponseSpecification responseSpecification,
      final String path,
      final Class<T> clazz) {
    return RestAssured.given()
        .spec(requestSpecification)
        .basePath(path)
        .when()
        .get()
        .then()
        .spec(responseSpecification)
        .extract()
        .as(clazz);
  }

  public static <T> T getRequest(
      final RequestSpecification requestSpecification,
      final ResponseSpecification responseSpecification,
      final String path,
      final String paramName,
      final Integer id,
      final Class<T> clazz) {
    return RestAssured.given()
        .spec(requestSpecification)
        .basePath(path)
        .pathParam(paramName, id)
        .when()
        .get()
        .then()
        .spec(responseSpecification)
        .extract()
        .as(clazz);
  }

  public static <T> T postRequest(
      final RequestSpecification requestSpecification,
      final ResponseSpecification responseSpecification,
      final String path,
      final Object request,
      final Class<T> tclass) {
    return RestAssured.given()
        .spec(requestSpecification)
        .basePath(path)
        .body(request)
        .when()
        .post()
        .then()
        .spec(responseSpecification)
        .extract()
        .as(tclass);
  }
}
