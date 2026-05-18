package test;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class AddToCartTest extends BaseTest {

    String expectedNameItem = "Sauce Labs Backpack";
    String expectedPriceItem = "$29.99";

    @Test(description = "Проверка добавления товара в корзину",
            testName = "Проверка товара в корзине",
            groups = "smoke")
    void checkCartItem() {
        SoftAssert softAssert = new SoftAssert();
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        driver.findElement(By.xpath("//button[text()='Add to cart']")).click();
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        String itemName = driver.findElement(By.cssSelector("div.inventory_item_name")).getText();
        String itemPrice = driver.findElement(By.xpath("//div[@class='inventory_item_price']")).getText();

        softAssert.assertEquals(expectedNameItem, itemName);
        softAssert.assertEquals(expectedPriceItem, itemPrice);
        softAssert.assertAll();
    }

    @DataProvider(name = "cartProducts")
    public Object[][] getCartProducts() {
        return new Object[][]{
                {"Sauce Labs Backpack"},
                {"Sauce Labs Bolt T-Shirt"},
                {"Sauce Labs Fleece Jacket"}
        };
    }

    @Test(dataProvider = "cartProducts",
            description = "Проверка добавления товара в корзину с дата провайдером",
            testName = "Проверка товара в корзине + дата провайдер",
            groups = "smoke")
    public void checkCart(String productName) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user","secret_sauce");
        List<String> expectedProducts = new ArrayList<>();
        String[] productsToAdd = {"Sauce Labs Backpack", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket"};

        for (String product : productsToAdd) {
            productsPage.addToCart(product);
            expectedProducts.add(product);
        }
        productsPage.clickCart();
        List<String> actualProducts = cartPage.getProductNamesInCart();

        softAssert.assertTrue(actualProducts.contains(productName),
                "Товар не найден в корзине!");
        if (productName.equals(productsToAdd[productsToAdd.length - 1])) {
            softAssert.assertEquals(actualProducts.size(), expectedProducts.size(),
                    "Количество товаров в корзине не совпадает!!!");
            softAssert.assertTrue(actualProducts.containsAll(expectedProducts),
                    "Не все товары добавлены в корзину. Должно быть: " + expectedProducts);
        }
        softAssert.assertAll();
    }
}
