package ecommerce_test.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.FormPage;

import java.io.IOException;
import java.net.MalformedURLException;

public class CheckoutTest extends BaseTest{

    public AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {
        startServer();
        driver = capabilities();
        formPage = new FormPage(driver);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
        stopServer();
    }

    @Test(enabled = true)
    public void checkPreference() throws MalformedURLException {
        homePage = formPage.fillForm("Ho Nguyen", "Argentina");
        checkoutPage = homePage.doShopping();
        Assert.assertTrue(checkoutPage.isTotalPriceDisplayed());
    }
}
