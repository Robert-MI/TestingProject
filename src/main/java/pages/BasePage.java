package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import pages.LoginPage;
import java.time.Duration;
import constants.LocatorConstants;


public class BasePage {
    protected WebDriver driver;
    private By noutButton = By.className(LocatorConstants.NOUT_CLASS_NAME);

    public HomePage clickToBasePage(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(noutButton));
        button.click();
        return new HomePage(driver);
    }
}
