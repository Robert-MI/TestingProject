package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import constants.LocatorConstants;

public class ChartPage extends BasePage{
    private final By price = By.className(LocatorConstants.PRICE_LOCATOR_CLASS);

    public ChartPage(WebDriver driver){
        this.driver = driver;
    }

    public int getPrice() {
        List<WebElement> priceElements = driver.findElements(price);

        // Check if any elements with the class name "price" were found
        if (!priceElements.isEmpty()) {

            // Overall price appears the first in html
            WebElement firstPriceElement = priceElements.getFirst();
            return Integer.parseInt(firstPriceElement.getText().replaceAll("[^0-9]", ""));
        } else {
            // If no elements with the class name "price" found return 0
            return 0;
        }
    }

    public boolean isProductNameInShoppingCart(String productName) {
        //Wait for the shopping cart table to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement shoppingCartTable = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorConstants.CHART_TABLE_ID)));
        boolean productFound = false;
        try {

            String cssSelector = "#shopping-cart-table .product-item-name a";
            //Get all elements matching the CSS selector
            java.util.List<WebElement> productElements = shoppingCartTable.findElements(By.cssSelector(cssSelector));

            for (WebElement productElement : productElements) {
                if (productElement.getText().equals(productName)) {
                    productFound = true; // Product name found in the shopping cart
                }
            }
        } catch (TimeoutException _) {}
        return productFound;
    }

    public void clickPlus() {
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.className(LocatorConstants.CHART_PLUS_CLASS)));
        button.click();
    }
    public void clickMinus() {
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.className(LocatorConstants.CHART_MINUS_CLASS)));
        button.click();
    }

    public void deleteAllItemsFromCart() {
        try {
            WebElement cartTable = driver.findElement(By.id(LocatorConstants.CHART_TABLE_ID));

            List<WebElement> deleteButtons = cartTable.findElements(By.cssSelector(".action-delete"));

            for (int i = 0; i < deleteButtons.size(); i++) {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                //Wait page to load
                try {
                    wait.until(ExpectedConditions.elementToBeClickable(By.id("nonExistingId")));
                } catch (TimeoutException _) {}
                deleteCartItem();
            }
        }catch (Exception _) {}
    }

    public void deleteCartItem() {
        WebElement cartTable = driver.findElement(By.id(LocatorConstants.CHART_TABLE_ID));
        List<WebElement> deleteButtons = cartTable.findElements(By.cssSelector(".action-delete"));
        deleteButtons.getFirst().click();
    }
}
