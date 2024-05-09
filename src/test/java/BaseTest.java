import com.google.common.io.Files;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import pages.HomePage;

public class BaseTest{
    public static final String BASE_URL = "https://nout.am/am/";
    public static final String WEBDRIVER = "webdriver.geco.driver";
    public static final String DRIVER_PATH = "src/drivers/gecodriver";
    public WebDriver driver;
    public HomePage homePage;
    FirefoxOptions firefoxOptions;

    @BeforeClass
    public void setUp(){
        System.setProperty(WEBDRIVER,DRIVER_PATH);
        firefoxOptions = new FirefoxOptions();
        try {
            driver =  new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),firefoxOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        Dimension size = new Dimension(2000, 800);
        driver.manage().window().setSize(size);
        moveToLoginPage();
    }

    @BeforeMethod
    public void moveToLoginPage(){
        driver.get(BASE_URL);
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
}
