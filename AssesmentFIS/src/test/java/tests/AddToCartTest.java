package tests;

import base.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ItemPage;
import pages.SearchResultsPage;

public class AddToCartTest {
    WebDriver driver;
    HomePage homePage;
    SearchResultsPage resultsPage;
    ItemPage itemPage;

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.get("https://www.ebay.com");
        homePage = new HomePage(driver);
        resultsPage = new SearchResultsPage(driver);
        itemPage = new ItemPage(driver);
    }

    @Test
    public void verifyItemAddedToCart() throws InterruptedException {
        homePage.searchFor("book");
        resultsPage.clickFirstItem();
        itemPage.addToCart();
        Thread.sleep(3000); 
        String count = itemPage.getCartCount();
        Assert.assertTrue(Integer.parseInt(count) >= 1, "Cart count should be at least 1");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
