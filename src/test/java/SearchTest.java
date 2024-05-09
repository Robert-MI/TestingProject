import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import constants.AssertionMessages;


public class SearchTest extends BaseTest{
    @Test
    public void searchNonExistingItem() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        homePage.closeBar();//Closing pop up Bar as it gives errors

        //Wait page to load, as page loads too slow, function in BaseTest at the end
        waitToLoad(wait);

        Assert.assertFalse(homePage.Exists(AssertionMessages.NON_EXISTING_ITEM));
    }

    @Test
    public void searchExistingItem () throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        homePage.closeBar();//Closing pop up Bar as it gives errors
        waitToLoad(wait);

        //Search is macbook pro 16
        Assert.assertTrue(homePage.Exists(AssertionMessages.EXISTING_ITEM));
    }

    @Test
    public void searchExistingItemWithOneMistake () throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        homePage.closeBar();//Closing pop up Bar as it gives errors
        waitToLoad(wait);

        //Search is macbook pro 16
        //Mistake in word macbook
        Assert.assertTrue(homePage.Exists(AssertionMessages.EXISTING_ITEM_ONE_MISTAKE));
    }

    //No result if there is a mistake in the second word
    @Test
    public void searchExistingItemWithTwoMistake () throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        homePage.closeBar();//Closing pop up Bar as it gives errors
        waitToLoad(wait);

        //Search is macbook pro 16
        //Mistake in word macbook and pro
        Assert.assertTrue(homePage.Exists(AssertionMessages.EXISTING_ITEM_TWO_MISTAKES));
    }
}


