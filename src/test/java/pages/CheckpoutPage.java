package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckpoutPage extends BasePage {

    private final By CHECKOUT_BUTTON = By.id("checkout");
    private final By USERNAME_CHECKOUT_FIELD = By.id("first-name");
    private final By PASSWORD_CHECKOUT_FIELD = By.id("last-name");
    private final By POSTAL_CHECKOUT_FIELD = By.id("postal-code");
    private final By CONTINUE_BUTTON = By.name("continue");
    private final By SHOPPING_CART_ICON = By.xpath("//a[@class='shopping_cart_link']");
    private final By ERROR_MESSAGE = By.className("error-message-container");
    private final By TITLE = By.cssSelector("[data-test = title]");
    private final By FINISH_BUTTON = By.id("finish");
    private final By FINAL_SUCCESSFULL_MESSAGE = By.cssSelector("[data-test =complete-header]");

    public CheckpoutPage(WebDriver driver) {
        super(driver);
    }

    public void clickCheckoutButton() {
        driver.findElement(CHECKOUT_BUTTON).click();
    }

    public void clickFinishButton() {
        driver.findElement(FINISH_BUTTON).click();
    }

    public void checkoutUser(String user, String pass, String postal) {
        driver.findElement(USERNAME_CHECKOUT_FIELD).sendKeys(user);
        driver.findElement(PASSWORD_CHECKOUT_FIELD).sendKeys(pass);
        driver.findElement(POSTAL_CHECKOUT_FIELD).sendKeys(postal);
        driver.findElement(CONTINUE_BUTTON).click();
   }
   public void clickShoppingCartIcon() {
        driver.findElement(SHOPPING_CART_ICON).click();
   }

    public String getErrorMessage() {
        return driver.findElement(ERROR_MESSAGE).getText();
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public String getSuccessfulMessage() {
        return driver.findElement(FINAL_SUCCESSFULL_MESSAGE).getText();
    }
}
