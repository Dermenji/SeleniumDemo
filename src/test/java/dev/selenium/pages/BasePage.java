package dev.selenium.pages;

import dev.selenium.driver.DriverFactory;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public WebDriver driver;
    private WebDriverWait wait;

    public BasePage () {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeStale (WebElement element) {
        wait.until(ExpectedConditions.stalenessOf(element));
    }

    public void clickWithJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    public void scrollIntoViewWithJS(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void clickAndWaitElement( WebElement element) {
        try {
            element.click();
        } catch (StaleElementReferenceException e) {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        }
    }

    public void hoverElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }







}
