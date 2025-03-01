package io.quantumqa.stepdefinitions;

import io.quantumqa.core.DriverFactory;
import io.quantumqa.pages.InputsPage;
import io.quantumqa.reporting.ReportLogger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InputsStepDefinitions {

    private WebDriver driver;
    private InputsPage inputsPage;

    @Given("I am on the-internet herokuapp homepage")
    public void navigateToHomepage() {
        driver = DriverFactory.getDriver();
        driver.get("https://the-internet.herokuapp.com/");
        ReportLogger.logInfo("Navigated to the-internet herokuapp homepage");
    }

    @When("I click on the Inputs link")
    public void clickInputsLink() {
        inputsPage = new InputsPage(driver);
        inputsPage.clickInputsLink();
        ReportLogger.logInfo("Clicked on Inputs link");
    }

    @When("I interact with the number field using random values")
    public void interactWithRandomValues() throws InterruptedException {
        inputsPage.interactWithRandomValues();
        ReportLogger.logInfo("Interacted with number field using random values");
    }

    @When("I interact with the number field using increment arrow")
    public void interactWithIncrementArrow() throws InterruptedException {
        inputsPage.interactWithIncrementArrow();
        ReportLogger.logInfo("Interacted with number field using increment arrow");
    }

    @When("I interact with the number field using decrement arrow")
    public void interactWithDecrementArrow() throws InterruptedException {
        inputsPage.interactWithDecrementArrow();
        ReportLogger.logInfo("Interacted with number field using decrement arrow");
    }

    @Then("the inputs field should behave as expected")
    public void verifyInputsFieldBehavior() {
        Assert.assertTrue(inputsPage.isInputsFieldDisplayed(), "Inputs field did not behave as expected");
        ReportLogger.logPass("Inputs field behaved as expected");
    }
}