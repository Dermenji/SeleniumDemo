package dev.selenium.tests;

import dev.selenium.base.MainTest;
import dev.selenium.pages.CartPage;
import dev.selenium.pages.LoginPage;
import dev.selenium.pages.ProductsPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductsPageTest extends MainTest {
    private ProductsPage productsPage;

    @BeforeMethod
    public void loginBefore() {
        LoginPage loginPage = new LoginPage();
        loginPage.loginAs("standard_user", "secret_sauce");
    }

    @Test
    public void canOpenCart() {
        productsPage = new ProductsPage();
        productsPage.header().openCartByIcon();

        CartPage cartPage = new CartPage();

    }


}
