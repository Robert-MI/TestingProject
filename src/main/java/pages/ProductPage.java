package pages;

import constants.LocatorConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage{
    public ProductPage(WebDriver driver){
        this.driver = driver;
    }
    public void addToChartItem() {
        WebElement button = driver.findElement(By.id(LocatorConstants.PRODUCT_PAGE_ADD_ID));
        button.click();
    }

    public void setQuantity(String number) {
        WebElement quantityInput = driver.findElement(By.id(LocatorConstants.PRODUCT_QUANTITY_ID));
        quantityInput.clear(); // Clear any existing value
        quantityInput.sendKeys(number); // Set the quantity
    }
}
