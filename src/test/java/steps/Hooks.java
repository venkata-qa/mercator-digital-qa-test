package steps;

import browser_manager.DriverManager;
import context.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ConfigurationReader;
import utils.EnvironmentInfo;

import java.time.Duration;

public class Hooks {

    private static final Logger logger = LogManager.getLogger(Hooks.class);

    public static WebDriver driver;
    public static TestContext testContext;

    @Before
    public static void setup() {
        EnvironmentInfo.generateEnvironmentProperties();
        try {
            driver = DriverManager.get();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
            driver.get(ConfigurationReader.get("url"));
            testContext = new TestContext(driver);
            logger.info("Driver started and navigated to URL: " + ConfigurationReader.get("url"));
        } catch (Exception e) {
            logger.error("Error occurred while starting the driver", e);
            throw e;
        }
    }

    @After
    public void tearDown(Scenario scenario) throws InterruptedException {
        if(scenario.isFailed()){
            final byte[]screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png","screenshot");
        }
        Thread.sleep(4000);
        DriverManager.closeDriver();
    }

    public static TestContext getTestContext() {
        return testContext;
    }
}
