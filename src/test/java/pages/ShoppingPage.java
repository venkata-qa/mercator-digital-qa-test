package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static steps.Hooks.driver;

public class ShoppingPage extends BasePage
{
    public ShoppingPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id="add-to-cart-sauce-labs-backpack")
    WebElement backpackAddToCartBtn;
    @FindBy(id="add-to-cart-sauce-labs-fleece-jacket")
    WebElement jacketAddToCartBtn;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> prices;

    @FindBy(className = "btn_inventory")
    private List<WebElement> addToCartButtons;

    public void AddToCart(){
        click(backpackAddToCartBtn);
        click(jacketAddToCartBtn);
    }

    public void addHighestItemToCart() {
        double highestPrice = 0.0;
        int highestPriceIndex = -1;

        for (int i = 0; i < prices.size(); i++) {
            String priceText = prices.get(i).getText().replace("$", "");
            double price = Double.parseDouble(priceText);

            if (price > highestPrice) {
                highestPrice = price;
                highestPriceIndex = i;
            }
        }

        if (highestPriceIndex != -1) {
            addToCartButtons.get(highestPriceIndex).click();
        }
    }
}
