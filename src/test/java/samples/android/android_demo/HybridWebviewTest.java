package samples.android.android_demo;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Set;

public class HybridWebviewTest extends ECommerceBase {

    AndroidDriver<AndroidElement> driver;

    @BeforeMethod
    public void gotoShoppingPage() throws MalformedURLException {
        driver = capabilities("emulator");

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

    private void gotoCheckoutPage(AndroidDriver<AndroidElement> driver) throws InterruptedException {
        // Select 2 first items
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();
        driver.findElements(By.xpath("//*[@text='ADD TO CART']")).get(0).click();

        // go to Checkout page
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        Thread.sleep(2000);

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

    @Test(enabled = true)
    public void webviewCheck() throws MalformedURLException, InterruptedException {
        gotoCheckoutPage(driver);

        // wait for webview loaded
        Thread.sleep(7000);

        // Get Context and Switch context to webview
        Set<String> contexts = driver.getContextHandles();
        for(String context: contexts){
            System.out.println(context);
        }

        String nativeContext = contexts.toArray()[0].toString();
        String webviewContext = contexts.toArray()[1].toString();

        // switch to WEB_VIEW
        driver.context(webviewContext);
        driver.findElement(By.name("q")).sendKeys("Hello World");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);

        // switch to NATIVE APP again
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context(nativeContext);
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Hello");
        driver.hideKeyboard();


    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
