package utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentInfo {

    public static void generateEnvironmentProperties() {
        Properties properties = new Properties();

        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String browser = ConfigurationReader.get("browser");
        String env = ConfigurationReader.get("env");
        String url = ConfigurationReader.get("url");

        properties.setProperty("OS.Name", osName);
        properties.setProperty("OS.Version", osVersion);
        properties.setProperty("Browser", browser);
        properties.setProperty("Environment", env);
        properties.setProperty("URL", url);


        File resultsDir = new File("target/allure-results");
        if (!resultsDir.exists()) {
            resultsDir.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream("target/allure-results/environment.properties")) {
            properties.store(fos, "Environment Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
