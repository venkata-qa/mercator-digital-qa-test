package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckOutPage extends BasePage {

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id="shopping_cart_container")
    WebElement shoppingCartBtn;
    @FindBy(id="checkout")
    WebElement checkoutBtn;
    @FindBy(id="first-name")
    WebElement firstNameTxtBox;
    @FindBy(id="last-name")
    WebElement lastNameTxtBox;
    @FindBy(id="postal-code")
    WebElement postalCodeTxtBox;
    @FindBy(id="continue")
    WebElement continueBtn;
    @FindBy(id= "finish")
    WebElement finishBtn;
    @FindBy(className ="complete-header")
    public WebElement successMessage;
    public void ProcessToCheckOut(){
        click(shoppingCartBtn);
        click(checkoutBtn);
    }
    public void FillYourInformation(String first, String last, String zip)
    {
        enterText(firstNameTxtBox,first);
        enterText(lastNameTxtBox,last);
        enterText(postalCodeTxtBox,zip);
        click(continueBtn);
    }
    public void confirmOrder()
    {
        click(finishBtn);
    }

}
