package browser_manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import utils.ConfigurationReader;

public class DriverManager {

    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    private DriverManager() {}

    private static WebDriver driver;

    public static WebDriver get() {
        if (driver == null) {
            String browser = ConfigurationReader.get("browser");
            logger.info("Browser selected: " + browser);
            try {
                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver();
                        logger.info("Chrome browser started");
                        break;
                    case "chrome-headless":
                        WebDriverManager.chromedriver().setup();
                        driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                        logger.info("Chrome headless browser started");
                        break;
                    case "firefox":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver();
                        logger.info("Firefox browser started");
                        break;
                    case "firefox-headless":
                        WebDriverManager.firefoxdriver().setup();
                        driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
                        logger.info("Firefox headless browser started");
                        break;
                    case "ie":
                        if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                            throw new WebDriverException("Your OS doesn't support Internet Explorer");
                        WebDriverManager.iedriver().setup();
                        driver = new InternetExplorerDriver();
                        logger.info("Internet Explorer browser started");
                        break;
                    case "edge":
                        if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                            throw new WebDriverException("Your OS doesn't support Edge");
                        WebDriverManager.edgedriver().setup();
                        driver = new EdgeDriver();
                        logger.info("Edge browser started");
                        break;
                    case "safari":
                        if (!System.getProperty("os.name").toLowerCase().contains("mac"))
                            throw new WebDriverException("Your OS doesn't support Safari");
                        WebDriverManager.getInstance(SafariDriver.class).setup();
                        driver = new SafariDriver();
                        logger.info("Safari browser started");
                        break;
                    default:
                        throw new WebDriverException("No browser specified or browser not supported: " + browser);
                }
            } catch (WebDriverException e) {
                logger.error("Error occurred while starting the browser: " + browser, e);
                throw e;
            }
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed");
            driver = null;
        }
    }
}
