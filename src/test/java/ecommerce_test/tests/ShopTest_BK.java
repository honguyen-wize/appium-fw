package ecommerce_test.tests;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

public class ShopTest_BK extends BaseTest {
    AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void gotoShoppingPage() throws IOException, InterruptedException {
        driver = capabilities();

        // Input your name
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
        driver.hideKeyboard();

        // Select Female radio
        driver.findElement(By.xpath("//*[@text='Female']")).click();

        // Open the dropdown country, scroll and select the right one
        driver.findElementById("android:id/text1").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))").click();

        // Click on Let's Shopp
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    @Test (enabled = true)
    public void shoppingTwoItemTest() throws MalformedURLException, InterruptedException {
        // Select 2 first items
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

        // go to Checkout page
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);

        // find total price
        String totalPriceValue = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        double totalAmountValue = getAmount(totalPriceValue);

        // find sum of products
        List<AndroidElement> productPriceItems = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        double sumOfProducts = 0;
        for(AndroidElement priceEl: productPriceItems){
            double amount = getAmount(priceEl.getText());
            sumOfProducts += amount;
        }

        // compare sum of products vs total price
        Assert.assertEquals(sumOfProducts, totalAmountValue);

        // Complete the checkout process
        WebElement chkSendEmail = driver.findElementByClassName("android.widget.CheckBox");
        WebElement lblTerm = driver.findElementByXPath("//*[@text='Please read our terms of conditions']");
        WebElement btnComplete = driver.findElementById("com.androidsample.generalstore:id/btnProceed");

        TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(chkSendEmail))).perform();

        touchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(lblTerm))
                .withDuration(Duration.ofSeconds(2))).release().perform();

        WebElement btnCloseTermDialog = driver.findElementById("android:id/button1");
        btnCloseTermDialog.click();
        Thread.sleep(2000);

        btnComplete.click();
    }


    private double getAmount(String value){
        return Double.parseDouble(value.substring(1));
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

}
