package dev.selenium;

import dev.selenium.pages.LoginPage;
import dev.selenium.pages.ProductsPage;
import dev.selenium.pages.ShoppingCart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

import static org.testng.AssertJUnit.*;

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

        productsPage.addProduct();
        productsPage.openShoppingCart();

        ShoppingCart cart = new ShoppingCart(driver);
        assertTrue(cart.isItemAdded());

        cart.removeItem();
        assertFalse(cart.isItemAdded());


    }

    @Test
    public void testNotValidLogin() {
        SoftAssert soft = new SoftAssert();
        loginPage = new LoginPage(driver);
        loginPage.setUsername("");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        soft.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Username is required");

        loginPage.setUsername("standard_user");
        loginPage.setPassword("");
        loginPage.clickLoginButton();

        soft.assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required");
        soft.assertAll();



    }

}
