package pages;

import org.openqa.selenium.WebDriver;
import pages.CheckOutPage;
import pages.LoginPage;
import pages.LogoutPage;
import pages.ShoppingPage;

public class PageObjectManager {
    private WebDriver driver;

    private LoginPage loginPage;
    private ShoppingPage shoppingPage;
    private CheckOutPage checkOutPage;
    private LogoutPage logoutPage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage(driver);
        }
        return loginPage;
    }

    public ShoppingPage getShoppingPage() {
        if (shoppingPage == null) {
            shoppingPage = new ShoppingPage(driver);
        }
        return shoppingPage;
    }

    public CheckOutPage getCheckOutPage() {
        if (checkOutPage == null) {
            checkOutPage = new CheckOutPage(driver);
        }
        return checkOutPage;
    }

    public LogoutPage getLogoutPage() {
        if (logoutPage == null) {
            logoutPage = new LogoutPage(driver);
        }
        return logoutPage;
    }
}