package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import constants.LocatorConstants;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{
    private By Electronics = By.id(LocatorConstants.ELECTRONICS_ID);

    public HomePage(WebDriver driver){
        this.driver = driver;
    }

    public ElectronicsPage clickElectronicsButton (){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(Electronics));
        button.click();
        return new ElectronicsPage(driver);
    }
}
