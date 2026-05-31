package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

public class CheckoutOverviewInvocationTest extends BaseTest{
    private String productName = "Sauce Labs Bolt T-Shirt";
    private double productPrice = 15.99;

    @Test
    public void testCheckoutOverviewWithOneProduct() {
        SoftAssert softAssert = new SoftAssert();
        productsPage = loginPage
                .open()
                .login("standard_user", "secret_sauce");
        productsPage.addToCart(productName);
        productsPage.clickCart();
        checkpoutPage.clickShoppingCartIcon();
        checkpoutPage.clickCheckoutButton();
        checkpoutPage.checkoutUser("user1", "pass", "30045");
        List<String> productNames = checkoutOverviewPage.getProductNames();

        softAssert.assertEquals(productNames.size(), 1,
                "Должен отображаться 1 товар");
        softAssert.assertEquals(productNames.get(0), productName,
                "Название товара неверное!");

        List<Double> productPrices = checkoutOverviewPage.getProductPrices();
        double actualItemTotal = checkoutOverviewPage.getItemTotal();
        double expectedItemTotal = checkoutOverviewPage.calculateExpectedItemTotal(productPrices);

        softAssert.assertEquals(actualItemTotal, expectedItemTotal,
                "Item total не соответствует сумме!!!");
        softAssert.assertEquals(actualItemTotal, productPrice,
                "Item total должен быть раdен цене!");
        softAssert.assertAll();
    }
}
