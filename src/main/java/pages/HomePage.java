package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import constants.LocatorConstants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BasePage{
    private final By phones = By.id(LocatorConstants.PHONES_ID);
    private final By smartPhones = By.id(LocatorConstants.SMARTPHONES_ID);

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public SmartPhonePage goToSmartPhonePage (){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(phones));
        button.click();
        WebElement secondButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(smartPhones));
        secondButton.click();
        return new SmartPhonePage(driver);
    }

    public boolean Exists(String search){
        WebElement input = driver.findElement(By.id(LocatorConstants.SEARCH_ID));
        input.clear();
        input.sendKeys(search);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        //Wait page to load
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("nonExistingId")));
        } catch (TimeoutException e) {}

        //If there is no class "sqr-no-results", then there are results
        return driver.findElements(By.className(LocatorConstants.NO_RESULT_CLASS)).isEmpty();
    }
}
