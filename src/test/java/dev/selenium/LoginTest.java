package dev.selenium;

import dev.selenium.pages.LoginPage;
import dev.selenium.pages.ProductsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.AssertJUnit.assertEquals;

public class LoginTest extends MainDriver{
    LoginPage loginPage;
    ProductsPage productsPage;

    @Test
    public void testSuccessfulLogin() {
        loginPage = new LoginPage(driver);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");

        loginPage.clickLoginButton();
        productsPage = new ProductsPage(driver);

        assertEquals(productsPage.getPageTitle() , "Products");
    }

    @Test
    public void testNotValidLogin() {
        loginPage = new LoginPage(driver);
        loginPage.setUsername("");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        productsPage = new ProductsPage(driver);



    }

}
