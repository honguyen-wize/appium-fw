package ecommerce_test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.net.MalformedURLException;

public class ShoppingTest extends BaseTest {
    AndroidDriver<AndroidElement> driver;
    FormPage formPage;
    HomePage homePage;
    CheckoutPage checkoutPage;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        driver = capabilities();
        formPage = new FormPage(driver);
    }

    @Test
    public void checkPreference() throws MalformedURLException {
        homePage = formPage.fillForm("Ho Nguyen");
        checkoutPage = homePage.doShopping();
        Assert.assertTrue(checkoutPage.isTotalPriceDisplayed());
    }
}
