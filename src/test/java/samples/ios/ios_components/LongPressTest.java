package samples.ios.ios_components;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;

public class LongPressTest extends BaseIOS {
    IOSDriver driver;

    @Test
    public void longPressTest() throws MalformedURLException, InterruptedException {
        driver = capabilitiesIOS();

        // long press
        MobileElement longtapEl = (MobileElement) driver.findElementByName("Long tap");
        IOSTouchAction iosTouchAction = new IOSTouchAction(driver);
        iosTouchAction.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(longtapEl)).withDuration(Duration.ofSeconds(2)))
                .release().perform();

        // tap Name
        MobileElement nameEl = (MobileElement) driver.findElementByXPath("(//XCUIElementTypeSwitch)[1]");
        iosTouchAction.tap(TapOptions.tapOptions().withElement(ElementOption.element(nameEl))).perform();


    }
}
