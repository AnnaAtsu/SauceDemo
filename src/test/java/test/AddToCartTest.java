package test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AddToCartTest extends BaseTest {

    String expectedNameItem = "Sauce Labs Backpack";
    String expectedPriceItem = "$29.99";

    @Test
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
}
