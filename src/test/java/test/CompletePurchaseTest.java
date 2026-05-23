package test;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CompletePurchaseTest extends BaseTest {

    @Test(description = "End to end тест для совершения покупки",
            testName = "Критический путь",
            groups = "smoke")
    @Owner("Anna")
    @Epic("Sauce Demo 4")
    @Feature("End to end test")
    @Story("Buy products")
    @Severity(SeverityLevel.BLOCKER)
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
