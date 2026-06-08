package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class AddToCartInvocationTest extends BaseTest{

    @Test
    public void checkCartWithChainOfInvocation(String productName) {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open()
                .login("standard_user", "secret_sauce")
                .addToCart("Sauce Labs Backpack")
                .clickCart();

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
