package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class ConfigReader {

    private static Properties config     = new Properties();
    private static Properties testData   = new Properties();

    static {
        try {
            config.load(new FileInputStream(
                "src/test/resources/config/config.properties"));
            testData.load(new FileInputStream(
                "src/test/resources/config/testdata.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file: " + e.getMessage());
        }
    }

   
    public static String get(String key) {
        String value = config.getProperty(key);
        if (value == null) {
            value = testData.getProperty(key);
        }
        if (value == null) {
            throw new RuntimeException("Key '" + key + "' not found in properties files.");
        }
        return value.trim();
    }

   
    public static String getData(String key) {
        String value = testData.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Test data key '" + key + "' not found.");
        }
        return value.trim();
    }
}
