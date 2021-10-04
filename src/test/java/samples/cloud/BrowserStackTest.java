package samples.cloud;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackTest {

    AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        // Setup CloudDriver
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browserstack.user", "honguyen3");
        cap.setCapability("browserstack.key", "1aMwo6RkK7zHmbx6M1JE");
        cap.setCapability("device", "Samsung Galaxy S21");
        cap.setCapability("os_version", "11.0");
        cap.setCapability("app", "bs://76372c9ae91de6736f125fbf87b774eaf5607435");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        driver = new AndroidDriver<AndroidElement>(new URL("http://hub.browserstack.com/wd/hub"), cap);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }

    @Test (enabled = true)
    public void gotoShopSuccessfullyTest() throws MalformedURLException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
        driver.hideKeyboard();

        // Select Female radio
        driver.findElement(By.xpath("//*[@text='Female']")).click();

        // Open the dropdown country, scroll and select the right one
        driver.findElementById("android:id/text1").click();
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))").click();

        // Click on Let's Shopp
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();

//        driver.quit();
    }

    @Test (enabled = true)
    public void verifyToastMessage() throws MalformedURLException {

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
