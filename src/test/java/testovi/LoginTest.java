package testovi;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesObject.LoginPage;
import pagesObject.StringParam;

public class LoginTest extends BaseTest {
    ChromeDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void SetUp() {
        driver = openWebDriver();
        loginPage = new LoginPage(driver);
    }


    @Test
    public void noLoginOnPage() {
        loginPage.Login("", "");
        Assert.assertEquals(driver.getCurrentUrl(), StringParam.LOGIN_PAGE_URL);
    }

    @Test
    public void loginWithNoValidUsername() {
        loginPage.Login(StringParam.NO_VALID_USERNAME, StringParam.VALID_PASSWORD);
        Assert.assertEquals(loginPage.getTextMessage(), StringParam.LOGIN_ERROR_MESSAGE);
    }


    @Test
    public void loginWithNoValidPassword() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.NO_VALID_PASSWORD);
        Assert.assertEquals(loginPage.getTextMessage(), StringParam.LOGIN_ERROR_MESSAGE);
    }

    @Test
    public void loginWithValidData() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.VALID_PASSWORD);
        Assert.assertEquals(driver.getCurrentUrl(), StringParam.INVENTORY_PAGE_URL);
    }

    @Test
    public void logout() {
        loginPage.Login(StringParam.VALID_USERNAME, StringParam.VALID_PASSWORD);
        loginPage.OpenMenu();
        loginPage.Logout();
        Assert.assertEquals(driver.getCurrentUrl(), StringParam.LOGIN_PAGE_URL);
    }


    @AfterMethod
    public void After() {
        driver.quit();
    }
}
