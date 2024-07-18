package context;

import org.openqa.selenium.WebDriver;
import pages.PageObjectManager;

public class TestContext {

    private WebDriver driver;
    private PageObjectManager pageObjectManager;

    public TestContext(WebDriver driver) {
        this.driver = driver;
        this.pageObjectManager = new PageObjectManager(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
}
