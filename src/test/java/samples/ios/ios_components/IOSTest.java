package samples.ios.ios_components;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class IOSTest {
    public static void main (String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "14.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 11 Pro Max");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability(IOSMobileCapabilityType.LAUNCH_TIMEOUT, 30000);
        capabilities.setCapability("commandTimeouts", "10000");

        capabilities.setCapability(MobileCapabilityType.APP, "/Users/ho.nguyen/Documents/Trainings/Appium/demo-apps/UIKitCatalog.app");

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

    }
}
