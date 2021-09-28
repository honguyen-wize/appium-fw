package e_commerce_sample;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class GotoShopTest extends ECommerceBase {

    @Test
    public void gotoShopSuccessfullyTest() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");

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

    @Test
    public void verifyToastMessage() throws MalformedURLException {
        AndroidDriver<AndroidElement> driver = capabilities("emulator");

        // Input your name
//        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
//        driver.hideKeyboard();

        // Select Female radio
        driver.findElement(By.xpath("//*[@text='Female']")).click();

        // Open the dropdown country, scroll and select the right one
        driver.findElementById("android:id/text1").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))").click();

        // Click on Let's Shopp
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

        // check toast message
        String toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getAttribute("name");
        System.out.println(toastMessage);
        Assert.assertEquals("Please enter your name", toastMessage);

    }

}
