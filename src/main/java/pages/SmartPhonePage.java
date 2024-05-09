package pages;

import constants.LocatorConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class SmartPhonePage extends BasePage{


    public SmartPhonePage(WebDriver driver){
        this.driver = driver;
    }
    public void addToChartOneItem(int NthItem) {
        WebElement list = driver.findElement(By.className(LocatorConstants.LINK_LIST_SMATPHONE_CLASS));
        List<WebElement> listElements = list.findElements(By.className("inStock"));

        if (!listElements.isEmpty()) {
            WebElement firstElement = listElements.get(NthItem);

            WebElement button = firstElement.findElement(By.cssSelector("button[type='submit']"));
            button.click();
        }
    }

    public int getPriceOfItem(int NthItem) {
        WebElement list = driver.findElement(By.className(LocatorConstants.LINK_LIST_SMATPHONE_CLASS));
        List<WebElement> listElements = list.findElements(By.className(LocatorConstants.GET_LIST_LOCATOR_CLASS));
        WebElement firstElement = listElements.get(NthItem);
        List<WebElement> priceElements = firstElement.findElements(By.className(LocatorConstants.PRICE_LOCATOR_CLASS));

        if (!priceElements.isEmpty()) {
            if (priceElements.size() == 1) {
                return Integer.parseInt(priceElements.getFirst().getText().replaceAll("[^0-9]", ""));
            } else if (priceElements.size() == 2) {
                return Integer.parseInt(priceElements.get(1).getText().replaceAll("[^0-9]", ""));
            }
        }
        return 0;
    }
    public String getNameOfItem(int NthItem) {
        WebElement list = driver.findElement(By.className(LocatorConstants.LINK_LIST_SMATPHONE_CLASS));
        List<WebElement> listElements = list.findElements(By.className(LocatorConstants.GET_LIST_LOCATOR_CLASS));
        WebElement firstElement = listElements.get(NthItem);
        List<WebElement> nameElements = firstElement.findElements(By.className(LocatorConstants.ITEM_NAME_CLASS));

        if (!nameElements.isEmpty()) {
            return nameElements.getFirst().getText();
        }
        return "";
    }
    public ProductPage goToProductPage(int NthItem) {
        WebElement list = driver.findElement(By.className(LocatorConstants.LINK_LIST_SMATPHONE_CLASS));
        List<WebElement> listElements = list.findElements(By.className(LocatorConstants.GET_LIST_LOCATOR_CLASS));
        WebElement firstElement = listElements.get(NthItem);
        firstElement.click();
        return new ProductPage(driver);
    }

    public void sortAscending() {
        WebElement sortElement = driver.findElement(By.cssSelector("a.sort-desc, a.sort-asc"));

        // Check if the element has class "sort-desc"
        if (sortElement.getAttribute("class").contains("sort-desc")) {
            //Click to be ascending, otherwise keep the same
            sortElement.click();
        }
    }

    public void sortDescending() {
        WebElement sortElement = driver.findElement(By.cssSelector("a.sort-desc, a.sort-asc"));

        // Check if the element has class "sort-desc"
        if (sortElement.getAttribute("class").contains("sort-asc")) {
            //Click to be descending, otherwise keep the same
            sortElement.click();
        }
    }
}
