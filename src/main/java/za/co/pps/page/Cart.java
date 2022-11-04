package za.co.pps.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import za.co.pps.utility.Utilities;

import static org.testng.AssertJUnit.assertEquals;

public class Cart {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[text()='Add to cart']")
    private WebElement addToCart;

    @FindBy(how = How.XPATH, using = "//*[@title='Delete']")
    private WebElement deleteButton;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Proceed to checkout')]")
    private WebElement processtoCheckoutButton;

    @FindBy(how = How.XPATH, using = "//*[@class='alert alert-warning']")
    private WebElement warningText;

    @FindBy(how = How.ID_OR_NAME, using = "search_query_top")
    private WebElement searchItemText;

    @FindBy(how = How.ID_OR_NAME, using = "submit_search")
    private WebElement searchButton;

    @FindBy(how = How.XPATH, using = "//*[@class='icon-map-marker']/parent::li")
    private WebElement locationText;

    @FindBy(how = How.XPATH, using = "//*[@class='icon-phone']/parent::li")
    private WebElement phone;

    @FindBy(how = How.XPATH, using = "//*[@class='icon-envelope-alt']/parent::li")
    private WebElement email;

    public Cart(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void clickOncartButton() {
        addToCart.click();
    }

    public void proceedToCheckout() {
        processtoCheckoutButton.click();
    }

    public void deleteItem() {
        deleteButton.click();
    }

    public void verifyItemIsRemovedFromCart() throws InterruptedException {
        Utilities.fluentWait(By.xpath("//*[@class='alert alert-warning']"), driver);
        Thread.sleep(3000L);
        assertEquals(warningText.getText().trim(), "Your shopping cart is empty.");
    }

    public void searchItem(String text) {
        searchItemText.clear();
        searchItemText.sendKeys(text);
    }

    public void searchButton() {
        searchButton.click();
    }

    public void scrolltoBottomOfThePage() {
        ((JavascriptExecutor) driver)
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    public void validateStoreInformation() {
        assertEquals(locationText.getText().trim(), "Selenium Framework, Research Triangle Park, North Carolina, USA");
        assertEquals(phone.getText().trim(), "Call us now: (347) 466-7432");
        assertEquals(email.getText().trim(), "Email: support@seleniumframework.com");

    }
}
