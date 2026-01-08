package base;

import static io.restassured.RestAssured.*;

import org.junit.jupiter.api.BeforeAll;
import utils.ConfigProperties;

public class BaseTest {

  @BeforeAll
  public static void setUp() {
    baseURI = ConfigProperties.getBaseUri();
    authentication = basic(ConfigProperties.getUsername(), ConfigProperties.getPass());
  }
}
