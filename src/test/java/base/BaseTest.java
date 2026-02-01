package base;

import static io.restassured.RestAssured.*;

import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import utils.ConfigProperties;

public class BaseTest {

  private final Faker faker = new Faker();

  protected final Faker getFaker() {
    return faker;
  }

  @BeforeAll
  public static void setUp() {
    baseURI = ConfigProperties.getBaseUri();
    authentication = basic(ConfigProperties.getUsername(), ConfigProperties.getPass());
  }
}
