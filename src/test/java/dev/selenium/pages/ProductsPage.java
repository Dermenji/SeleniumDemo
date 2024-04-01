package dev.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement item;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement shoppingCartBadge;


    public ProductsPage (WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return pageTitle.getText();
    }

    public void addProduct() {
        item.click();
    }

    public void openShoppingCart() {
        shoppingCartBadge.click();
    }

    public HeaderComponent header () {
        return new HeaderComponent(driver);
    }

}
