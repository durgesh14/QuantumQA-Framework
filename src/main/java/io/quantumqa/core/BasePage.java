package io.quantumqa.core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    // Common method to wait for element visibility
    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void selectFilterOption(String filterOption, WebElement filterDropdown) {
        selectByVisibleText(filterDropdown, filterOption);
    }

    protected void selectByVisibleText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    // Common method to click an element
    protected void click(WebElement element) {
        waitForElementToBeVisible(element);
        element.click();
    }

    // Common method to click an element
    protected void click(By by) {
        WebElement element = driver.findElement(by);
        waitForElementToBeVisible(element);
        element.click();
    }

    // Common method to enter text
    protected void enterText(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    // Common method to get text
    protected String getText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }

    // Common method to check if element is displayed
    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(By by) {
        try {
            WebElement element = driver.findElement(by);
            waitForElementToBeVisible(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}