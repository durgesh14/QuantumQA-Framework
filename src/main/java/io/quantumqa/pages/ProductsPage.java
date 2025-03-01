package io.quantumqa.pages;

import io.quantumqa.core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(xpath = "//div[text()='%s']/ancestor::div[@class='inventory_item']//button")
    WebElement itemAddToCartButton;

    @FindBy(className = "product_sort_container")
    WebElement filterDropdown;

    @FindBy(className = "inventory_item_price")
    List<WebElement> productPrices;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

    @FindBy(css = ".btn_primary")
    List<WebElement> firstProduct;

    @FindBy(css = ".inventory_item_name")
    List<WebElement> firstProductName;

    @FindBy(xpath = "//div[text()='%s']/ancestor::div[@class='inventory_item']//button")
    List<WebElement> addToCartButton;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;


    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addItemToCart(String itemName) {
        By itemLocator = By.xpath(String.format("//div[text()='%s']/ancestor::div[@class='inventory_item']//button", itemName));
        click(itemLocator);
    }

    public void goToCart() {
        click(cartIcon);
    }

    public void selectFilterOption(String filterOption) {
        selectByVisibleText(filterDropdown, filterOption);
    }

    public List<Double> getProductPrices() {
        List<Double> prices = new ArrayList<>();
        List<WebElement> priceElements = productPrices;
        for (WebElement element : priceElements) {
            String priceText = element.getText().replace("$", "");
            prices.add(Double.parseDouble(priceText));
        }
        return prices;
    }

    public void addCheapestProductToCart() {
        List<Double> prices = getProductPrices();
        double minPrice = prices.stream().min(Double::compare).orElse(0.0);
        int index = prices.indexOf(minPrice);
        List<WebElement> addToCartButtons = addToCartButton;
        addToCartButtons.get(index).click();
    }

    public String getCheapestProductName() {
        List<Double> prices = getProductPrices();
        double minPrice = prices.stream().min(Double::compare).orElse(0.0);
        int index = prices.indexOf(minPrice);
        List<WebElement> nameElements = productNames;
        return nameElements.get(index).getText();
    }

    public void logout() {
        click(menuButton);
        click(logoutLink);
    }

    public void addFirstProductToCart() {
        firstProduct.get(0).click();
    }

    public String getFirstProductName() {
        return firstProductName.get(0).getText().trim();
    }
}