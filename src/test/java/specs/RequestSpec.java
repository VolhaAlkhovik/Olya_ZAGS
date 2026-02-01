package specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import utils.ConfigProperties;

@UtilityClass
public class RequestSpec {

  public static RequestSpecification requestSpecification() {
    return new RequestSpecBuilder()
        .setBaseUri(ConfigProperties.getBaseUri())
        .setAuth(RestAssured.basic(ConfigProperties.getUsername(), ConfigProperties.getPass()))
        .setContentType(ContentType.JSON)
        .log(LogDetail.ALL)
        .build();
  }
}
