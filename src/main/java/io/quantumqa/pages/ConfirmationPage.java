package io.quantumqa.pages;

import io.quantumqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ConfirmationPage extends BasePage {
    @FindBy(className = "complete-header")
    WebElement confirmationMessage;

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public String getConfirmationMessage() {
        return getText(confirmationMessage);
    }
}