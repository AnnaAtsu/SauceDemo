package test;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class CheckOutTest extends BaseTest{

    @Test
    public void checkCheckoutWithEmptyUsername() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        checkpoutPage.clickShoppingCartIcon();
        checkpoutPage.clickCheckoutButton();
        checkpoutPage.checkoutUser("", "pass", "300045");

        softAssert.assertEquals(checkpoutPage.getErrorMessage(), "Error: First Name is required",
                "Нет имени");
        softAssert.assertAll();
    }

    @Test
    public void checkCheckoutWithEmptyPassword() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        checkpoutPage.clickShoppingCartIcon();
        checkpoutPage.clickCheckoutButton();
        checkpoutPage.checkoutUser("user1", "", "300045");

        softAssert.assertEquals(checkpoutPage.getErrorMessage(), "Error: Last Name is required",
                "Нет фамилии");
        softAssert.assertAll();
    }

    @Test
    public void checkCheckoutWithEmptyPostal() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        checkpoutPage.clickShoppingCartIcon();
        checkpoutPage.clickCheckoutButton();
        checkpoutPage.checkoutUser("user1", "pass", "");

        softAssert.assertEquals(checkpoutPage.getErrorMessage(), "Error: Postal Code is required",
                "Нет почтового индекса");
        softAssert.assertAll();
    }

    @Test
    public void checkCheckoutWithPositiveCreds() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.addToCart("Sauce Labs Backpack");
        checkpoutPage.clickShoppingCartIcon();
        checkpoutPage.clickCheckoutButton();
        checkpoutPage.checkoutUser("user1", "pass", "30045");

        softAssert.assertEquals(checkpoutPage.getTitle(), "Checkout: Overview",
                "Неверная cтраница");
        softAssert.assertAll();
    }
}
