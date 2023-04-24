package pagesObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage{
    @FindBy(id = "remove-sauce-labs-bike-light")
    WebElement removeBikeLightButton;
    @FindBy(id = "remove-sauce-labs-bolt-t-shirt")
    WebElement removeShirtButton;
    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    public CartPage(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void RemoveBikeLight() {
        removeBikeLightButton.click();
    }

    public void RemoveShirt() {
        removeShirtButton.click();
    }

    public void continueShopping() {
        continueShoppingButton.click();
    }

}
