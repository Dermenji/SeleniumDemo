package dev.selenium.tests;

import com.opencsv.exceptions.CsvException;
import dev.selenium.base.MainTest;
import dev.selenium.pages.LoginPage;
import dev.selenium.pages.ProductsPage;
import dev.selenium.pages.ShoppingCart;
import dev.selenium.utils.CsvReader;
import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.testng.AssertJUnit.*;

public class LoginTest extends MainTest {
    LoginPage loginPage;
    ProductsPage productsPage;

    @DataProvider(name = "login-data")
    public static Object[][] dataProviderHardcodedData() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "login-data-file")
    public static Object[][] dataProviderFromCsvFile() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/login-data.csv");
    }

    @Test(dataProvider = "login-data-file")
    public void testSuccessfulLogin(String username, String password) {
        loginPage = new LoginPage();
        loginPage.setUsername(username);
        loginPage.setPassword(password);

        loginPage.clickLoginButton();
    }

    @DataProvider(name = "negative-login-data-file")
    public static Object[][] negativeDataProviderFromCsvFile() throws IOException, CsvException {
        return CsvReader.readFile("src/test/resources/login-fail-data.csv");
    }

    @Epic("Authentication")
    @Feature("Login")
    @Story("Successful Login")
    @Severity(SeverityLevel.CRITICAL)
    @Test(dataProvider = "negative-login-data-file")
    public void testNotValidLogin(String username, String password, String message) {
        loginPage = new LoginPage();
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        assertEquals(loginPage.getErrorMessage(), message);
    }

}
