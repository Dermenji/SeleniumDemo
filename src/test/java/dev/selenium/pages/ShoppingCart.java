package dev.selenium.pages;

import dev.selenium.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCart extends BasePage{

    @FindBy(css = ".cart_button")
    private List<WebElement> listOfRemoveButtons;

    public boolean isItemAdded() {
        if(listOfRemoveButtons.size() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public void removeItem(){
        listOfRemoveButtons.get(0).click();
    }
}
