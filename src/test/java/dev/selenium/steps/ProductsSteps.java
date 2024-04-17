package dev.selenium.steps;

import dev.selenium.pages.ProductsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.AssertJUnit.assertEquals;

public class ProductsSteps {

    private ProductsPage productsPage = new ProductsPage();

    @When("the user is redirected to products page")
    public void theUserIsRedirectedToProductsPage() {
        assertEquals(productsPage.getPageTitle(), "Products");
    }

    @Then("the user can open the cart page by clicking on the cart icon")
    public void theUserCanOpenTheCartPageByClickingOnTheCartIcon() {
        productsPage.header().openCartByIcon();
    }
}
