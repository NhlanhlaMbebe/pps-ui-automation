package za.co.pps.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import za.co.pps.page.Cart;
import za.co.pps.page.LendingPage;
import za.co.pps.page.TestBase;
import za.co.pps.utility.ExtentManger;

import java.util.concurrent.TimeUnit;

public class ShoppingTest extends TestBase {

    private LendingPage lendingPage;
    private Cart cart;

    @Override
    @BeforeClass
    public void before() throws Exception {
        super.before();
        getWebDriver().get(getSiteURL());
        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get(getSiteURL());
    }

    @Test
    public void testShopping() throws InterruptedException {
        lendingPage = new LendingPage(driver);
        lendingPage.scrollToItem("");
        lendingPage.clickOnItem();
        cart = new Cart(driver);
        cart.clickOncartButton();
        cart.proceedToCheckout();
        cart.deleteItem();
        cart.verifyItemIsRemovedFromCart();
        cart.searchItem("T shirt") ;
        cart.searchButton();
        cart.scrolltoBottomOfThePage();
        cart.validateStoreInformation();

        Thread.sleep(5000L);
    }

    @Override
    @AfterClass(alwaysRun = true)
    public void after() {
        super.after();
        ExtentManger.getInstance().flush();
    }
}
