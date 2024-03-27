package dev.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;


public class SeleniumDemo extends MainDriver {


    @Test
    public void testOPenBrowser() throws InterruptedException {
        // Act
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.sendKeys("locked_out_user");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");

        Thread.sleep(1000);
        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();

        WebElement errorContainer = driver.findElement(By.className("error-message-container"));
        assertTrue(errorContainer.isDisplayed());

        Assert.assertEquals(errorContainer.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }


}
