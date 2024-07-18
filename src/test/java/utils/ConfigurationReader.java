package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationReader {
    private static final Logger logger = LogManager.getLogger(ConfigurationReader.class);
    private static Properties properties = new Properties();

    static {
        try (InputStream input = ConfigurationReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException e) {
            logger.error("Failed to load config properties.", e);
            throw new ExceptionInInitializerError(e);
        }
    }

    public static String get(String keyName) {
        return properties.getProperty(keyName);
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
