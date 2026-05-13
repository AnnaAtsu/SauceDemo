package test;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorsTest extends BaseTest {

    @Test
    void checkLocators() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        driver.findElement(By.tagName("select"));
        driver.findElement(By.linkText("Twitter"));
        driver.findElement(By.partialLinkText("Faceb"));
        driver.findElement(By.xpath("//div[@class='inventory_item_name ']"));
        driver.findElement(By.xpath("//a[contains(@href, 'linkedin')]"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Onesie']"));
        driver.findElement(By.xpath("//div[contains(text(), 'This classic Sauce Labs t-shirt is perfect to wear')]"));
        driver.findElement(By.xpath("//span[@class='title' and contains(text(), 'Products')]"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/parent::a"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/ancestor::div[@class='inventory_item']"));
        driver.findElement(By.xpath("//div[@class='inventory_item']/descendant::div[@class='inventory_item_price']"));
        driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']/following::div[@class='inventory_item_price']"));
        driver.findElement(By.xpath("//div[@class='inventory_item_price']/preceding::div"));
        driver.findElement(By.cssSelector(".inventory_item_name"));
        driver.findElement(By.cssSelector("div.inventory_item_name"));
        driver.findElement(By.cssSelector(".inventory_item .inventory_item_name"));
        driver.findElement(By.cssSelector("#shopping_cart_container"));
        driver.findElement(By.cssSelector("ul"));
        driver.findElement(By.cssSelector("div.footer_copy"));
        driver.findElement(By.cssSelector("[data-test='product-sort-container']"));
        driver.findElement(By.cssSelector("[class|='bm']"));
        driver.findElement(By.cssSelector("[class~='inventory_item']"));
        driver.findElement(By.cssSelector("button[id^='add-to-cart']"));
        driver.findElement(By.cssSelector("img[src$='.jpg']"));
        driver.findElement(By.cssSelector("a[href*='saucelabs']"));
    }
}
