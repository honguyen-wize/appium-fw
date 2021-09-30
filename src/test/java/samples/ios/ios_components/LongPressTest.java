package samples.ios.ios_components;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class LongPressTest extends BaseIOS {
    IOSDriver driver;

    @Test
    public void longPressTest() throws MalformedURLException, InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11 Pro Max");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 30000);
        capabilities.setCapability("commandTimeouts", "10000");
        // Get long press app
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/ho.nguyen/Documents/Trainings/Appium/demo-apps/longtap.app");

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

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
