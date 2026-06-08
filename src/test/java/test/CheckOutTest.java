package test;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


public class CheckOutTest extends BaseTest {

    @Test(description = "Проверка чекаута с пустым именем",
            testName = "Негативные креды",
            groups = "regress")
    @Owner("Anna")
    @Epic("Sauce Demo 3")
    @Feature("Checkout Page")
    @Story("Checkout With Empty Username")
    @Severity(SeverityLevel.CRITICAL)
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

    @Test(description = "Проверка чекаута с пустым паролем",
            testName = "Негативные креды",
            groups = "regress")
    @Owner("Anna")
    @Epic("Sauce Demo 3")
    @Feature("Checkout Page")
    @Story("Checkout With Empty Password")
    @Severity(SeverityLevel.CRITICAL)
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

    @Test(description = "Проверка чекаута с пустым почтовым кодом",
            testName = "Негативные креды",
            groups = "regress")
    @Owner("Anna")
    @Epic("Sauce Demo 3")
    @Feature("Checkout Page")
    @Story("Checkout With Empty Postal")
    @Severity(SeverityLevel.CRITICAL)
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

    @Test(description = "Проверка чекаута с валидными данными",
            testName = "Позитивные креды",
            groups = "smoke")
    @Owner("Anna")
    @Epic("Sauce Demo 3")
    @Feature("Checkout Page")
    @Story("Checkout With positive creds")
    @Severity(SeverityLevel.BLOCKER)
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
