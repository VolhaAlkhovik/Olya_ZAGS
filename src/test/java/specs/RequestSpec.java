package specs;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RequestSpec {

    public RequestSpecification requestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri("https://regoffice.senla.eu")
                .setAuth(RestAssured.basic("user", "senlatest"))
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }
}
