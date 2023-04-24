package testovi;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.InventoryPage;
import pagesObject.LoginPage;
import pagesObject.StringParam;

public class SortTest extends BaseTest {

    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;

    @BeforeMethod
    public void Before() {
        driver = openWebDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
    }

    @Test
    public void SortByLowPrice() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.VALID_PASSWORD);
        inventoryPage.SortItemsByLowestPrice();
        Assert.assertEquals(inventoryPage.getFirstItemPrice(), "$7.99");
    }

    @AfterMethod
    public void After() {
        driver.quit();
    }
}
