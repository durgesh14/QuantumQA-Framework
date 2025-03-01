package io.quantumqa.pages;

import io.quantumqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(css = ".shopping_cart_link span")
    List<WebElement> cartItem;

    @FindBy(xpath = "//div[text()='%s']/ancestor::div[@class='cart_item']//button")
    WebElement removeButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckout() {
        click(checkoutButton);
    }

    public boolean isProductInCart(String productName) {
        return isElementDisplayed(By.xpath("//div[text()='" + productName + "']"));
    }

    public void removeProductFromCart(String productName) {
        click(By.xpath(String.format("//div[text()='%s']/ancestor::div[@class='inventory_item_description']//button", productName)));
    }

    public boolean isCartEmpty() {
        return cartItem.isEmpty();
    }
}