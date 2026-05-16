package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.List;


public class ProductsTest extends BaseTest{

    String productName = "Sauce Labs Bolt T-Shirt";

    @Test
    public void checkAllItemsOnPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        int actualProductsCount = productsPage.getProductsCount();

        softAssert.assertEquals(actualProductsCount, 6,
                "Ожидалось 6 товаров на странице, но найдено: " + actualProductsCount);
        softAssert.assertAll();
    }

    @Test
    public void checkAddButtonCount() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));

        softAssert.assertEquals(addToCartButtons.size(), 6,
                "Количество кнопок 'Add to cart' неверно!");
        softAssert.assertAll();
    }

    @Test
    public void checkAllAddButtonsChangeToRemove() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));
        for (WebElement button : addToCartButtons) {
            button.click();
        }
        List<WebElement> removeButtons = driver.findElements(By.xpath("//button[text()='Remove']"));

        softAssert.assertEquals(removeButtons.size(), 6,
                "Кнопки должны стать 'Remove'!");
        softAssert.assertAll();
    }

    @Test
    public void checkAddButtonChangeToRemove() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        String initialAddButtonText = productsPage.getButtonTextForProduct(productName);

        softAssert.assertEquals(initialAddButtonText, "Add to cart",
                "Не 'Add to cart'");

        productsPage.addToCart(productName);
        String buttonTextAfterAdd = productsPage.getButtonTextForProduct(productName);

        softAssert.assertEquals(buttonTextAfterAdd, "Remove",
                "Кнопка должна стать 'Remove'");

        productsPage.removeFromCart(productName);
        String buttonTextAfterRemove = productsPage.getButtonTextForProduct(productName);

        softAssert.assertEquals(buttonTextAfterRemove, "Add to cart",
                "После удаления из корзины кнопка должна снова стать 'Add to cart'");
        softAssert.assertAll();
    }
}
