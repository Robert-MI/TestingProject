package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import constants.LocatorConstants;


public class BasePage {
    protected WebDriver driver;
    private final By noutButton = By.className(LocatorConstants.NOUT_CLASS_NAME);
    private final By chartButton = By.className(LocatorConstants.CHART_CLASS_NAME);
    private final By chartNumber= By.className(LocatorConstants.CHART_NUMBER_CLASS_NAME);

    public HomePage clickToBasePage(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(noutButton));
        button.click();
        return new HomePage(driver);
    }

    public ChartPage openChart(){
        WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(chartButton));
        button.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.jsReturnsValue("return document.readyState === 'complete';"));
        return new ChartPage(driver);
    }

    public int chartNumber(){
        WebElement element = driver.findElement(chartNumber);
        String text = element.getText();
        int number;
        if (text.isEmpty()){
            number = 0;
        }else{
            number = Integer.parseInt(text);
        }
        return number;
    }
    public void closeBar(){

        try {
            WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.id("onesignal-slidedown-cancel-button")));
            button.click();
        } catch (TimeoutException _){}
    }
}
