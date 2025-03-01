package io.quantumqa.pages;

import io.quantumqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {
    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement zipCodeField;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(id = "finish")
    WebElement finishButton;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void enterShippingInformation(String firstName, String lastName, String zipCode) {
        enterText(firstNameField, firstName);
        enterText(lastNameField, lastName);
        enterText(zipCodeField, zipCode);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public void clickFinish() {
        click(finishButton);
    }
}