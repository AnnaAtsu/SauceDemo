package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CompletePurchaseTest extends BaseTest{

    @Test
    public void buyProductTest() {

        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        checkpoutPage.clickShoppingCartIcon();
        checkpoutPage.clickCheckoutButton();
        checkpoutPage.checkoutUser("user1", "pass", "30045");
        checkpoutPage.clickFinishButton();

        softAssert.assertEquals(checkpoutPage.getSuccessfulMessage(), "Thank you for your order!",
                "No message or wrong message");
        softAssert.assertAll();
    }
}
