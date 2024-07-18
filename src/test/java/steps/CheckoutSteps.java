package steps;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static steps.Hooks.driver;
import static steps.Hooks.getTestContext;

public class CheckoutSteps {
    private final LoginPage loginPage;
    private final ShoppingPage shoppingPage;
    private final CheckOutPage checkOutPage;
    private final LogoutPage logoutPage;

    public CheckoutSteps() {
        PageObjectManager pageObjectManager = getTestContext().getPageObjectManager();
        loginPage = pageObjectManager.getLoginPage();
        shoppingPage = pageObjectManager.getShoppingPage();
        checkOutPage = pageObjectManager.getCheckOutPage();
        logoutPage = pageObjectManager.getLogoutPage();
    }

    @When("the user logs in with valid credentials {string}, {string}")
    public void userLogin(String username, String password) {
       loginPage.UserLogin(username,password);
    }
    @And("user adds items to the cart")
    public void addItemsToCart() {
        shoppingPage.AddToCart();
    }
    @And("user checks out the order {string}, {string}, {string}")
    public void checkOutTheOrder(String firstName, String lastName, String zip) {
        checkOutPage.ProcessToCheckOut();
        checkOutPage.FillYourInformation(firstName,lastName,zip);
        checkOutPage.confirmOrder();
        Assert.assertTrue(checkOutPage.successMessage.getText().contains("Thank you for your order!"));
    }
    @And("user logs out of the application")
    public void logout() {
        logoutPage.logOut();
    }
    @Then("the user should be on the login page")
    public void verifyLogin() {
        String currentURL = driver.getCurrentUrl();
        String expectedURL = "https://www.saucedemo.com/";
        assertEquals(expectedURL, currentURL);
    }
    @And("user add high priced item to the cart")
    public void addHighestItemToCart() {
        shoppingPage.addHighestItemToCart();
    }
}
