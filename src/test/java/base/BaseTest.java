package base;

import utils.ConfigProperties;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;



public class BaseTest {

    @BeforeAll
    public static void setUp(){
        baseURI = ConfigProperties.getBaseUri();
        authentication = RestAssured.basic(ConfigProperties.getUsername(), ConfigProperties.getPass());
    }

}
