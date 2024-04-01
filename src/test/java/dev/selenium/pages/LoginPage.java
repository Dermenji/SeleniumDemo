package dev.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message-container")
    private WebElement errorMessageContainer;




    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void setPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }


    public String getErrorMessage() {
        return errorMessageContainer.getText();
    }

    public void loginAs(String userName, String password) {
        setUsername(userName);
        setPassword(password);
        clickLoginButton();
    }
}
