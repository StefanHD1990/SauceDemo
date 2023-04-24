package testovi;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.*;

public class CheckoutTest extends BaseTest {

    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void Before() {
        driver = openWebDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
    }

    @Test
    public void CheckAllItemsPriceWithFixedItemsValues() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.VALID_PASSWORD);
        inventoryPage.AddBikeLight();
        inventoryPage.AddShirt();
        inventoryPage.AddOnesie();
        inventoryPage.OpenCart();
        inventoryPage.checkout();
        Assert.assertEquals(driver.getCurrentUrl(), StringParam.CHECKOUT_STEP_ONE_URL);
        checkoutPage.EnterFirstName("Stefan");
        checkoutPage.EnterLastName("Vujic");
        checkoutPage.EnterZipCode("11090");
        checkoutPage.ClickOnContinue();
        Assert.assertEquals(driver.getCurrentUrl(), StringParam.CHECKOUT_STEP_TWO_URL);
        Assert.assertEquals("Item total: $33.97", checkoutPage.GetItemTotalString());
        Assert.assertEquals("Total: $36.69", checkoutPage.GetTotalString());
    }


    @Test
    public void CheckAllItemsPrice() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.VALID_PASSWORD);
        inventoryPage.AddBikeLight();
        inventoryPage.AddShirt();
        inventoryPage.AddOnesie();
        inventoryPage.OpenCart();
        inventoryPage.checkout();
        checkoutPage.EnterFirstName("Stefan");
        checkoutPage.EnterLastName("Vujic");
        checkoutPage.EnterZipCode("11090");
        checkoutPage.ClickOnContinue();
        Assert.assertEquals(checkoutPage.GetSumPriceOfAllProducts(), checkoutPage.GetItemTotal());
        Assert.assertEquals(checkoutPage.SumTaxAndTotal(), checkoutPage.GetTotal());

    }

    @Test
    public void EndShoppingProcess() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.VALID_PASSWORD);
        inventoryPage.AddBikeLight();
        inventoryPage.OpenCart();
        inventoryPage.checkout();
        checkoutPage.EnterFirstName("Stefan");
        checkoutPage.EnterLastName("Vujic");
        checkoutPage.EnterZipCode("11090");
        checkoutPage.ClickOnContinue();
        checkoutPage.ClickOnFinish();
        Assert.assertEquals(driver.getCurrentUrl(), StringParam.CHECKOUT_COMPLETE_URL);
        Assert.assertEquals(checkoutPage.GetCompleteHeader(), "Thank you for your order!");
        Assert.assertEquals(checkoutPage.GetCompleteText(), "Your order has been dispatched, and will arrive just as fast as the pony can get there!");
        Assert.assertEquals(checkoutPage.GetBackToProductsText(), "Back Home");
    }


    @AfterMethod
    public void After() {
        driver.quit();
    }
}



