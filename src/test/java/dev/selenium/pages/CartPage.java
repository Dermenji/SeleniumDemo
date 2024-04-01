package dev.selenium.pages;

import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public HeaderComponent header () {
        return new HeaderComponent(driver);
    }
}
