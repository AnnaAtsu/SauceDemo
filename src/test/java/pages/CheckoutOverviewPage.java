package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CheckoutOverviewPage extends BasePage{

    private final By CART_ITEMS = By.className("cart_item");
    private final By PRODUCT_NAME = By.className("inventory_item_name");
    private final By PRODUCT_PRICE = By.className("inventory_item_price");
    private final By ITEM_TOTAL = By.className("summary_subtotal_label");

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getProductNames() {
        List<WebElement> cartItems = driver.findElements(CART_ITEMS);
        List<String> productNames = new ArrayList<>();

        for (WebElement item : cartItems) {
            String name = item.findElement(PRODUCT_NAME).getText();
            productNames.add(name);
        }

        return productNames;
    }

    public List<Double> getProductPrices() {
        List<WebElement> cartItems = driver.findElements(CART_ITEMS);
        List<Double> productPrices = new ArrayList<>();
        for (WebElement item : cartItems) {
            String priceText = item.findElement(PRODUCT_PRICE).getText();
            double price = Double.parseDouble(priceText.replace("$", ""));
            productPrices.add(price);
        }

        return productPrices;
    }

    public double getItemTotal() {
        String itemTotalText = driver.findElement(ITEM_TOTAL).getText();
        String numberPart = itemTotalText.replace("Item total: $", "");
        return Double.parseDouble(numberPart);
    }

    public int getProductsCount() {
        return driver.findElements(CART_ITEMS).size();
    }

    public double calculateExpectedItemTotal(List<Double> prices) {
        double sum = 0;
        for (double price : prices) {
            sum += price;
        }
        return Math.round(sum * 100.0) / 100.0;
    }
}
