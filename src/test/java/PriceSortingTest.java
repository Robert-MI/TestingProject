import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SmartPhonePage;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;


public class PriceSortingTest extends BaseTest{
    @Test
    public void sortAscendingTest() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        smartPhonePage.closeBar();//Closing pop up Bar as it gives errors
        smartPhonePage.sortAscending();

        //Wait page to load, as page loads too slow, function in BaseTest at the end
        waitToLoad(wait);

        //Checking if upcoming items are more expensive
        Assert.assertTrue(smartPhonePage.getPriceOfItem(0) <= smartPhonePage.getPriceOfItem(10));
    }

    @Test
    public void sortDescendingTest() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        smartPhonePage.closeBar();//Closing pop up Bar as it gives errors
        smartPhonePage.sortDescending();
        waitToLoad(wait);

        //Checking if upcoming items are less expensive
        Assert.assertTrue(smartPhonePage.getPriceOfItem(0) >= smartPhonePage.getPriceOfItem(10));
    }
}

