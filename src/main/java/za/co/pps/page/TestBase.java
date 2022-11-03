package za.co.pps.page;

import com.aventstack.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import za.co.pps.utility.ExcelReader;
import za.co.pps.utility.Utilities;

import java.util.logging.Logger;

import static za.co.pps.utility.Utilities.createOutputFolder;

public class TestBase {

    public static final Logger log4j = Logger.getLogger(TestBase.class.getName());

    public static WebDriver driver;

    private ExcelReader excelReader;

    public static ExtentReports extent;

    public TestBase() {
        driver = null;
    }

    @BeforeClass
    public void before() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        log4j.info("Logging chrome browser");
        Utilities.folderName = createOutputFolder();
    }

    @AfterClass
    public void after() {
        if (driver != null) {
            driver.quit();
            log4j.info("================ Closing the browser session, test complete ========");
        }
    }

    protected String getSiteURL() {
        String URL = System.getProperty("testURL");
        if (URL == null)
            return "http://automationpractice.com/index.php";
        else {
            return URL;
        }
    }

    public String[][] getData(String excelName, String sheetName) {
        String path = System.getProperty("user.dir") + "/src/main/resources/TestData/" + excelName;
        log4j.info("Opening URL path for excel" + System.getProperty("user.dir") + "/src/main/resources/TestData/" + excelName);
        excelReader = new ExcelReader(path);
        String[][] data = excelReader.getDataFromExcelSheet(sheetName);
        return data;
    }

    protected WebDriver getWebDriver() {
        return driver;
    }
}
