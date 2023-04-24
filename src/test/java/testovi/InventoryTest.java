package testovi;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.*;

public class InventoryTest extends BaseTest {

    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;

    @BeforeMethod
    public void Before() {
        driver = openWebDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
    }

    @Test
    public void Add3CheapestProductsToCart() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.VALID_PASSWORD);
        inventoryPage.SortItemsByLowestPrice();
        inventoryPage.AddOnesie();
        inventoryPage.AddBikeLight();
        inventoryPage.AddShirt();
        Assert.assertEquals(inventoryPage.Cart(), "3");
    }

    @Test
    public void Add2ProductsAndRemoveIt() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.VALID_PASSWORD);
        inventoryPage.AddBikeLight();
        inventoryPage.AddShirt();
        inventoryPage.OpenCart();
        Assert.assertEquals(driver.getCurrentUrl(), StringParam.CART_URL);
        cartPage.RemoveBikeLight();
        cartPage.RemoveShirt();
        cartPage.continueShopping();
        Assert.assertEquals(inventoryPage.Cart(), "" );
    }

    @AfterMethod
    public void After() {
        driver.quit();
    }
}


