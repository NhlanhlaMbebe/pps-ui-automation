package za.co.pps.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Cart {

    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[text()='Add to cart']")
    private WebElement addToCart;

    @FindBy(how = How.XPATH, using = "//*[contains(text(),'Proceed to checkout')]")
    private WebElement processtoCheckoutButton;


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
}
