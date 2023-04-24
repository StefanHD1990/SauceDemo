package pagesObject;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends BasePage {

    @FindBy(id = "first-name")
    WebElement firstName;
    @FindBy(id = "last-name")
    WebElement lastName;
    @FindBy(id = "postal-code")
    WebElement zipCode;
    @FindBy(id = "continue")
    WebElement continueButton;
    @FindBys({@FindBy(className = "inventory_item_price")})
    List<WebElement> items;
    @FindBy(className = "summary_subtotal_label")
    WebElement itemTotalValue;
    @FindBy(className = "summary_total_label")
    WebElement totalValue;
    @FindBy(className = "summary_tax_label")
    WebElement tax;
    @FindBy(id = "finish")
    WebElement finishButton;
    @FindBy(className = "complete-header")
    WebElement completeHeader;
    @FindBy(className = "complete-text")
    WebElement completeText;
    @FindBy(id = "back-to-products")
    WebElement backToProductsButton;

    public CheckoutPage(ChromeDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void EnterFirstName(String name) {
        firstName.sendKeys(name);
    }

    public void EnterLastName(String last) {
        lastName.sendKeys(last);
    }

    public void EnterZipCode(String zip) {
        zipCode.sendKeys(zip);
    }

    public void ClickOnContinue() {
        continueButton.click();
    }

    public String GetItemTotalString() {
        return itemTotalValue.getText();
    }

    public Double GetItemTotal() {
        return Double.parseDouble(KeepDigitsAndDecimalFromString(GetItemTotalString()));
    }

    public String KeepDigitsAndDecimalFromString(String str) {
        return str.replaceAll("[^0-9\\.]", "");
    }



    public Double GetSumPriceOfAllProducts() {
        return Double.parseDouble(KeepDigitsAndDecimalFromString(items.get(0).getText()))
                + Double.parseDouble(KeepDigitsAndDecimalFromString(items.get(1).getText()))
                + Double.parseDouble(KeepDigitsAndDecimalFromString(items.get(2).getText()));
    }

    public String GetTotalString() {
        return totalValue.getText();
    }

    public Double GetTotal() {
        return Double.parseDouble(KeepDigitsAndDecimalFromString(GetTotalString()));
    }

    public Double GetTax() {
        return Double.parseDouble(KeepDigitsAndDecimalFromString(tax.getText()));
    }

    public Double SumTaxAndTotal() {
        return Math.round((GetItemTotal() + GetTax()) * 100.0) / 100.0;
    }

    public void ClickOnFinish() {
        finishButton.click();
    }

    public String GetCompleteHeader() {
        return completeHeader.getText();
    }

    public String GetCompleteText() {
        return completeText.getText();
    }

    public String GetBackToProductsText() {
        return backToProductsButton.getText();
    }

}
