package io.quantumqa.pages;

import io.quantumqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class InputsPage extends BasePage {

    @FindBy(linkText = "Inputs")
    WebElement inputsLink;

    @FindBy(xpath = "//input[@type='number']")
    WebElement numberField;

    public InputsPage(WebDriver driver) {
        super(driver);
    }

    public void clickInputsLink() {
        click(inputsLink);
    }

    public void interactWithRandomValues() throws InterruptedException {
        waitForElementToBeVisible(numberField);
        for (int i = 0; i < 10; i++) {
            Random rand = new Random();
            int values = rand.nextInt(90) + 10;
            String value = String.valueOf(values);
            numberField.sendKeys(value);
            Thread.sleep(1000);
            numberField.clear();
        }
    }

    public void interactWithIncrementArrow() throws InterruptedException {
        waitForElementToBeVisible(numberField);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            numberField.sendKeys(Keys.ARROW_UP);
        }
    }

    public void interactWithDecrementArrow() throws InterruptedException {
       waitForElementToBeVisible(numberField);
        for (int i = 0; i < 10; i++) {
            Thread.sleep(1000);
            numberField.sendKeys(Keys.ARROW_DOWN);
        }
    }

    public boolean isInputsFieldDisplayed() {
        return isElementDisplayed(numberField);
    }
}