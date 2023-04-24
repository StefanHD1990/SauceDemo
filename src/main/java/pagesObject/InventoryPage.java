package pagesObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage extends BasePage {

    @FindBy(id = "add-to-cart-sauce-labs-onesie")
    WebElement addOnesieButton;
    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement addBikeLightButton;
    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement addShirtButton;
    @FindBy(id = "shopping_cart_container")
    WebElement cart;
    @FindBy(className = "inventory_item_price")
    WebElement lowPrice;
    @FindBy(className = "product_sort_container")
    WebElement sortButton;
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    public InventoryPage(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void SortItemsByLowestPrice() {
        Select dropdown = new Select(sortButton);
        dropdown.selectByVisibleText("Price (low to high)");
    }

    public String getFirstItemPrice() {
        return lowPrice.getText();

    }

    public void AddOnesie() {
        addOnesieButton.click();
    }

    public void AddBikeLight() {
        addBikeLightButton.click();
    }

    public void AddShirt() {
        addShirtButton.click();
    }

    public String Cart() {
        return cart.getText();
    }

    public void OpenCart() {
        cart.click();
    }

    public void checkout() {
        checkoutButton.click();
    }

}
