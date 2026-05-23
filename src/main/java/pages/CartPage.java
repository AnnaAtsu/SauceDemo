package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private final By CART_ITEMS = By.className("cart_item");
    private final By PRODUCT_NAME = By.className("inventory_item_name");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получить список товаров в корзине")
    public List<String> getProductNamesInCart() {
        List<WebElement> cartItems = driver.findElements(CART_ITEMS);
        List<String> productNames = new ArrayList<>();

        for (WebElement item : cartItems) {
            String name = item.findElement(PRODUCT_NAME).getText();
            productNames.add(name);
        }
        return productNames;
    }
}
