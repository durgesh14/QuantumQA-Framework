package io.quantumqa.pages;
import io.quantumqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "user-name")
    WebElement usernameField;

    @FindBy(id = "password")
    WebElement passwordField;

    @FindBy(id = "login-button")
    WebElement loginButton;


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        enterText(usernameField, username);
        enterText(passwordField, password);
        click(loginButton);
    }
}