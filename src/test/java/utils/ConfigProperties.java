package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

  private static final Properties PROPERTIES = new Properties();

  static {
    try (InputStream input =
        ConfigProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
      PROPERTIES.load(input);
    } catch (Exception e) {
      throw new RuntimeException("Cannot load app", e);
    }
  }

  public static String getBaseUri() {
    return PROPERTIES.getProperty("base.uri");
  }

  public static String getUsername() {
    return PROPERTIES.getProperty("auth.username");
  }

  public static String getPass() {
    return PROPERTIES.getProperty("auth.pass");
  }

  public static String getDBUrl() {
    return PROPERTIES.getProperty("DBurl");
  }

  public static String getDBUser() {
    return PROPERTIES.getProperty("DBuser");
  }

  public static String getDBPassword() {
    return PROPERTIES.getProperty("DBpass");
  }
}
