package pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger(BasePage.class);

    private static final int WAIT_TIMEOUT_SECONDS = 10;
    private static final int POLLING_INTERVAL_MILLIS = 500;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
        this.wait.pollingEvery(Duration.ofMillis(POLLING_INTERVAL_MILLIS));
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            logger.info("Clicked on element: " + element);
        } catch (Exception e) {
            logger.error("Unable to click on element: " + element, e);
            throw e;
        }
    }

    protected void enterText(WebElement element, String value) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            element.clear();
            element.sendKeys(value);
            logger.info("Entered text '" + value + "' into element: " + element);
        } catch (Exception e) {
            logger.error("Unable to enter text '" + value + "' into element: " + element, e);
            throw e;
        }
    }

    protected String getText(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            String text = element.getText();
            logger.info("Got text '" + text + "' from element: " + element);
            return text;
        } catch (Exception e) {
            logger.error("Unable to get text from element: " + element, e);
            throw e;
        }
    }
}