package samples.android.android_components;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import static io.appium.java_client.touch.LongPressOptions.*;
import static io.appium.java_client.touch.offset.ElementOption.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;

public class SwipeTest  extends Base {

    @Test
    public void testSwipe() throws MalformedURLException {
        System.out.println("================ Test case: testSwipe");
        AndroidDriver<AndroidElement> driver = capabilities("emulator");

        // go to date widget
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();
        driver.findElementByAndroidUIAutomator("text(\"2. Inline\")").click();
        driver.findElementByXPath("//*[@content-desc='9']").click();

        // swipe from 15 tp 30
        // first, long press on 1st element. Second, move to the second element and release
        WebElement firstEl = driver.findElementByXPath("//*[@content-desc='15']");
        WebElement secondEL = driver.findElementByXPath("//*[@content-desc='35']");

        TouchAction touchAction = new TouchAction(driver);
        touchAction.longPress(longPressOptions().withElement(element(firstEl)).withDuration(Duration.ofSeconds(2)))
                .moveTo(element(secondEL))
                .release().perform();
    }
}
