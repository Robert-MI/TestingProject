import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import constants.AssertionMessages;

import pages.HomePage;

public class BaseTest{
    public static final String WEBDRIVER = "webdriver.geco.driver";
    public static final String DRIVER_PATH = "src/drivers/gecodriver";
    public WebDriver driver;
    public HomePage homePage;
    ChromeOptions chromeOptions;

    @BeforeClass
    public void setUp(){
        System.setProperty(WEBDRIVER,DRIVER_PATH);
        chromeOptions = new ChromeOptions();
        try {
            driver =  new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),chromeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Dimension size = new Dimension(2000, 2000);
        driver.manage().window().setSize(size);
        moveToLoginPage();
    }

    @BeforeMethod
    public void moveToLoginPage(){
        driver.get(AssertionMessages.BASE_URL);
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void recordFailure(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus())
        {
            var camera = (TakesScreenshot)driver;
            File screenshot = camera.getScreenshotAs(OutputType.FILE);
            try{
                Files.move(screenshot, new File("src/screenshots/" +  result.getName() + ".png"));
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    public void waitToLoad(WebDriverWait wait) {
        //Didn't find better way to wait page to load, sorry
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("nonExistingId")));
        } catch (TimeoutException _) {}
    }
}
