package utils;

import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {

    private static final Properties properties = new Properties();

    static{
        try (InputStream input = ConfigProperties.class
                .getClassLoader().getResourceAsStream("config.properties")) {
        properties.load(input);
        }
        catch (Exception e){
            throw new RuntimeException("Cannot load app", e);
        }
    }

    public static String getBaseUri(){
        return properties.getProperty("base.uri");
    }

    public static String getUsername(){
        return properties.getProperty("auth.username");
    }

    public static String getPass(){
        return properties.getProperty("auth.pass");
    }
}
