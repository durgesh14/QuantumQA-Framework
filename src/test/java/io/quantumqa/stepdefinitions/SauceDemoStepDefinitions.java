package io.quantumqa.stepdefinitions;


import io.quantumqa.core.DriverFactory;
import io.quantumqa.pages.*;
import io.quantumqa.reporting.ReportLogger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

public class SauceDemoStepDefinitions {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private ConfirmationPage confirmationPage;

    @Given("I am logged in to SauceDemo with username {string} and password {string}")
    public void loginToSauceDemo(String username, String password) {
        driver = DriverFactory.getDriver();
        driver.get("https://www.saucedemo.com/");
        loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        ReportLogger.logInfo("Logged in to SauceDemo with username: " + username);
    }

    @When("I add the following items to the cart:")
    public void addItemsToCart(List<String> items) {
        productsPage = new ProductsPage(driver);
        for (String item : items) {
            productsPage.addItemToCart(item);
            ReportLogger.logInfo("Added item to cart: " + item);
        }
    }

    @When("I proceed to checkout")
    public void proceedToCheckout() {
        productsPage.goToCart();
        cartPage = new CartPage(driver);
        cartPage.clickCheckout();
        ReportLogger.logInfo("Proceeded to checkout");
    }

    @When("I enter shipping information:")
    public void enterShippingInformation(List<Map<String, String>> shippingInfo) {
        checkoutPage = new CheckoutPage(driver);
        Map<String, String> info = shippingInfo.get(0); // Get the first row
        checkoutPage.enterShippingInformation(
                info.get("firstName"),
                info.get("lastName"),
                info.get("zipCode")
        );
        checkoutPage.clickContinue();
        ReportLogger.logInfo("Entered shipping information");
    }

    @When("I complete the purchase")
    public void completePurchase() {
        checkoutPage.clickFinish();
        confirmationPage = new ConfirmationPage(driver);
        ReportLogger.logInfo("Completed the purchase");
    }

    @Then("I should see the order confirmation message {string}")
    public void verifyOrderConfirmation(String expectedMessage) {
        String actualMessage = confirmationPage.getConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "Order confirmation message mismatch");
        ReportLogger.logPass("Verified order confirmation message: " + expectedMessage);
    }

    @When("I filter products by price {string}")
    public void filterProductsByPrice(String filterOption) {
        productsPage = new ProductsPage(driver);
        productsPage.selectFilterOption(filterOption);
        ReportLogger.logInfo("Filtered products by: " + filterOption);
    }

    @Then("the products should be sorted by price in ascending order")
    public void verifyProductsAreSorted() {
        List<Double> prices = productsPage.getProductPrices();
        for (int i = 0; i < prices.size() - 1; i++) {
            Assert.assertTrue(prices.get(i) <= prices.get(i + 1), "Products are not sorted in ascending order");
        }
        ReportLogger.logPass("Verified products are sorted by price in ascending order");
    }

    @When("I add the cheapest product to the cart")
    public void addCheapestProductToCart() {
        productsPage.addCheapestProductToCart();
        ReportLogger.logInfo("Added the cheapest product to the cart");
    }

    @When("I add the first product to the cart")
    public void addFirstProductToCart() {
        productsPage.addFirstProductToCart();
        ReportLogger.logInfo("Added the First product to the cart");
    }

    @When("I verify the cart contains the cheapest product")
    public void verifyCartContainsCheapestProduct() {
        cartPage = new CartPage(driver);
        String cheapestProductName = productsPage.getCheapestProductName();
        Assert.assertTrue(cartPage.isProductInCart(cheapestProductName), "Cart does not contain the cheapest product");
        ReportLogger.logPass("Verified cart contains the cheapest product: " + cheapestProductName);
    }

    @When("I remove the cheapest product from the cart")
    public void removeCheapestProductFromCart() {
        cartPage.removeProductFromCart(productsPage.getCheapestProductName());
        ReportLogger.logInfo("Removed the cheapest product from the cart");
    }

    @When("I remove the first product from the cart")
    public void removeFirstProductFromCart() {
        cartPage.removeProductFromCart(productsPage.getFirstProductName());
        ReportLogger.logInfo("Removed the cheapest product from the cart");
    }

    @Then("the cart should be empty")
    public void verifyCartIsEmpty() {
        Assert.assertFalse(cartPage.isCartEmpty(), "Cart is not empty"); //fail
        ReportLogger.logPass("Verified the cart is empty");
    }

    @Then("I logout from the application")
    public void logoutFromApplication() {
        productsPage.logout();
        ReportLogger.logInfo("Logged out from the application");
    }
}