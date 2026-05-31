package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductsPage extends BasePage {

    WebDriverWait wait;

    private final By TITLE = By.cssSelector("[data-test = title]");
    private final By CART = By.cssSelector("[data-test = shopping-cart-link]");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Add to cart']";
    private final String REMOVE_FROM_CART_PATTERN =
            "//*[text()='%s']//ancestor::div[@class='inventory_item']//button[text()='Remove']";
    private final String BUTTON_PATTERN =
            "//div[text()='%s']//ancestor::div[@class='inventory_item']//button";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public ProductsPage open() {
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    @Override
    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }
    @Step("Получить заголовок страницы")
    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Добавление в корзину товара с именем : {product}")
    public ProductsPage addToCart(String product) {
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
        return this;
    }

    @Step("Удаление в корзину товара с именем : {product}")
    public void removeFromCart(String product) {
        driver.findElement(By.xpath(String.format(REMOVE_FROM_CART_PATTERN, product))).click();
    }

    @Step("Получить название кнопки для товара: {productName}")
    public String getButtonTextForProduct(String productName) {
        String xpath = String.format(BUTTON_PATTERN, productName);
        return driver.findElement(By.xpath(xpath)).getText();
    }

    @Step("Нажатие кнопки корзины")
    public CartPage clickCart() {
        driver.findElement(CART).click();
        return new CartPage(driver);
    }

    @Step("Посчитать количество товаров")
    public int getProductsCount() {
        List<WebElement> products = driver.findElements(By.className("inventory_item"));
        return products.size();
    }

    @Step("Получить все товары")
    public List<WebElement> getAllProducts() {
        return driver.findElements(By.className("inventory_item"));
    }
}
