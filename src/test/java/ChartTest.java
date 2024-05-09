import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ChartPage;
import pages.ProductPage;
import pages.SmartPhonePage;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import constants.AssertionMessages;


public class ChartTest extends BaseTest{
    @Test
    public void SingleAddition() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ChartPage chartPage = homePage.openChart();

        chartPage.closeBar();//Closing pop up Bar as it gives errors
        int expectedNumber = chartPage.chartNumber();
        int expectedSum = chartPage.getPrice();
        String item;

        homePage = chartPage.clickToBasePage();
        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        smartPhonePage.addToChartOneItem(0);
        expectedSum += smartPhonePage.getPriceOfItem(0);
        item = smartPhonePage.getNameOfItem(0);
        expectedNumber +=1;

        //Wait page to load, as page loads too slow, function in BaseTest at the end
        waitToLoad(wait);

        chartPage = smartPhonePage.openChart();
        waitToLoad(wait);

        //Checking if all selected items are in chart, chart items number(top left) is correct and Sum is computed correct
        Assert.assertTrue(chartPage.isProductNameInShoppingCart(item) && (expectedSum == chartPage.getPrice()) && (expectedNumber == chartPage.chartNumber()));
    }

    @Test
    public void multipleAddition() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ChartPage chartPage = homePage.openChart();

        chartPage.closeBar();//Closing pop up Bar as it gives errors
        int expectedNumber = chartPage.chartNumber();
        int expectedSum = chartPage.getPrice();
        String[] arr = new String[3];

        homePage = chartPage.clickToBasePage();
        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        smartPhonePage.addToChartOneItem(0);
        expectedSum += smartPhonePage.getPriceOfItem(0);
        arr[0] = smartPhonePage.getNameOfItem(0);
        expectedNumber +=1;

        smartPhonePage.addToChartOneItem(1);
        expectedSum += smartPhonePage.getPriceOfItem(1);
        arr[1] = smartPhonePage.getNameOfItem(1);
        expectedNumber +=1;

        smartPhonePage.addToChartOneItem(2);
        expectedSum += smartPhonePage.getPriceOfItem(2);
        arr[2] = smartPhonePage.getNameOfItem(2);
        expectedNumber +=1;

        smartPhonePage.closeBar();
        waitToLoad(wait);

        chartPage = smartPhonePage.openChart();
        waitToLoad(wait);

        //Checking if all selected items are in chart, chart items number(top left) is correct and Sum is computed correct
        Assert.assertTrue(chartPage.isProductNameInShoppingCart(arr[0]) && chartPage.isProductNameInShoppingCart(arr[1])
        && chartPage.isProductNameInShoppingCart(arr[2]) && (expectedSum == chartPage.getPrice()) && (expectedNumber == chartPage.chartNumber()));
    }

    @Test
    public void SingleAdditionFromProductPage() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ChartPage chartPage = homePage.openChart();

        chartPage.closeBar();//Closing pop up Bar as it gives errors
        int expectedNumber = chartPage.chartNumber();
        int expectedSum = chartPage.getPrice();
        String item;

        homePage = chartPage.clickToBasePage();
        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        expectedSum += smartPhonePage.getPriceOfItem(3);
        item = smartPhonePage.getNameOfItem(3);
        expectedNumber +=1;
        ProductPage productPage = smartPhonePage.goToProductPage(3);
        productPage.addToChartItem();

        waitToLoad(wait);

        chartPage = productPage.openChart();
        waitToLoad(wait);

        //Checking if all selected items are in chart, chart items number(top left) is correct and Sum is computed correct
        Assert.assertTrue(chartPage.isProductNameInShoppingCart(item) && (expectedSum == chartPage.getPrice()) && (expectedNumber == chartPage.chartNumber()));
    }

    //Real bug, checked manually too
    @Test
    public void MultipleAdditionFromProductPage() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ChartPage chartPage = homePage.openChart();

        chartPage.closeBar();//Closing pop up Bar as it gives errors
        int expectedNumber = chartPage.chartNumber();
        int expectedSum = chartPage.getPrice();
        String item;

        homePage = chartPage.clickToBasePage();
        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        expectedSum += (smartPhonePage.getPriceOfItem(4))*AssertionMessages.QUANTITY_FOR_MULTIPLE_SELECTION;
        item = smartPhonePage.getNameOfItem(4);
        expectedNumber +=AssertionMessages.QUANTITY_FOR_MULTIPLE_SELECTION;
        ProductPage productPage = smartPhonePage.goToProductPage(4);
        productPage.setQuantity(String.valueOf(AssertionMessages.QUANTITY_FOR_MULTIPLE_SELECTION));
        productPage.addToChartItem();

        waitToLoad(wait);

        chartPage = productPage.openChart();
        waitToLoad(wait);

        //Checking if all selected items are in chart, chart items number(top left) is correct and Sum is computed correct
        Assert.assertTrue(chartPage.isProductNameInShoppingCart(item) && (expectedSum == chartPage.getPrice()) && (expectedNumber == chartPage.chartNumber()));
    }

    @Test
    public void increaseProductCount() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        homePage.closeBar();//Closing pop up Bar as it gives errors

        //Emptying chart in case there are items from other tests
        ChartPage chartPage = homePage.openChart();
        waitToLoad(wait);
        chartPage.deleteAllItemsFromCart();
        homePage = chartPage.clickToBasePage();

        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        smartPhonePage.addToChartOneItem(5);
        //Wait to load after Adding
        waitToLoad(wait);
        smartPhonePage.addToChartOneItem(5);

        int price = smartPhonePage.getPriceOfItem(5);
        waitToLoad(wait);
        int number = smartPhonePage.chartNumber();

        chartPage = smartPhonePage.openChart();
        waitToLoad(wait);
        int currentPrice = chartPage.getPrice();
        chartPage.clickPlus();
        waitToLoad(wait);

        //Checking if chart items number(top left) is correct and Sum is computed correct
        Assert.assertTrue((chartPage.getPrice() == (currentPrice+price)) && (chartPage.chartNumber() == (number+1)));
    }

    @Test
    public void decreaseProductCount() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        homePage.closeBar();//Closing pop up Bar as it gives errors

        //Emptying chart in case there are items from other tests
        ChartPage chartPage = homePage.openChart();
        waitToLoad(wait);
        chartPage.deleteAllItemsFromCart();
        homePage = chartPage.clickToBasePage();

        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        smartPhonePage.addToChartOneItem(6);
        waitToLoad(wait);
        smartPhonePage.addToChartOneItem(6);
        int price = smartPhonePage.getPriceOfItem(6);
        waitToLoad(wait);
        int number = smartPhonePage.chartNumber();

        chartPage = smartPhonePage.openChart();
        waitToLoad(wait);
        int currentPrice = chartPage.getPrice();
        chartPage.clickMinus();
        waitToLoad(wait);

        //Checking if chart items number(top left) is correct and Sum is computed correct
        Assert.assertTrue((chartPage.getPrice() == (currentPrice-price)) && (chartPage.chartNumber() == (number-1)));
    }

    @Test
    public void DeleteItem() throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        ChartPage chartPage = homePage.openChart();

        chartPage.closeBar();//Closing pop up Bar as it gives errors
        int expectedNumber = chartPage.chartNumber();
        int expectedSum = chartPage.getPrice();

        homePage = chartPage.clickToBasePage();
        SmartPhonePage smartPhonePage = homePage.goToSmartPhonePage();

        smartPhonePage.addToChartOneItem(0);
        expectedSum += smartPhonePage.getPriceOfItem(0);
        expectedNumber +=1;

        //Wait page to load, as page loads too slow, function in BaseTest at the end
        waitToLoad(wait);

        chartPage = smartPhonePage.openChart();
        waitToLoad(wait);
        chartPage.deleteCartItem();
        waitToLoad(wait);

        //Checking if all selected items are in chart, chart items number(top left) is correct and Sum is computed correct
        Assert.assertFalse((expectedSum == chartPage.getPrice()) && (expectedNumber == chartPage.chartNumber()));
    }
}
