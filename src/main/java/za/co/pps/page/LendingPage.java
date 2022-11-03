package za.co.pps.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LendingPage {

    private WebDriver driver;

    @FindBys({@FindBy(how = How.XPATH, using = "//a[contains(text(),'Printed Dress')]")})
    private List<WebElement> items;

    public LendingPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void scrollToItem(String item) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//a[contains(text(),'Printed Dress')]"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    public void clickOnItem() {
        items.get(1).click();
    }
}
