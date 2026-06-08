package test;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ProductsInvocationTest extends BaseTest{

    String productName = "Sauce Labs Bolt T-Shirt";

    @Test
    public void checkAllItemsOnPage() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack")
                .clickCart();
        int actualProductsCount = productsPage.getProductsCount();

        softAssert.assertEquals(actualProductsCount, 6,
                "Ожидалось 6 товаров на странице, но найдено: " + actualProductsCount);
        softAssert.assertAll();
    }

}
