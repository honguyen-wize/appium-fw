package ecommerce_test.tests;

import ecommerce_test.testdata.FormDataProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.io.IOException;
import java.net.MalformedURLException;

public class ShoppingTest extends BaseTest {

    public AndroidDriver driver;

    @BeforeMethod
    public void setup() throws IOException, InterruptedException {
        if(!isRunningOnCloud()){
            startServer();
        }
        driver = getDriver();
        formPage = new FormPage(driver);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
        if(!isRunningOnCloud()){
            stopServer();
        }
    }

    @Test (enabled = false, dataProvider = "InputFormData", dataProviderClass = FormDataProvider.class)
    public void checkFillFormWithDataProvider(String inputName, String inputCountry){
        homePage = formPage.fillForm(inputName, inputCountry);
        Assert.assertTrue(homePage.isShoppingCartDisplayed());
    }

    @Test (enabled = true)
    public void checkFillFormSuccessfully() throws MalformedURLException {
        homePage = formPage.fillForm("Ho Nguyen", "Argentina");
        Assert.assertTrue(homePage.isShoppingCartDisplayed());
    }

    @Test (enabled = true)
    public void checkToastMessage() throws IOException {
        formPage.goShopping();
        Assert.assertEquals(formPage.getToastMessage(), "Please enter your name");
    }
}
