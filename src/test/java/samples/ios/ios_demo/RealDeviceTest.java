package samples.ios.ios_demo;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;

public class RealDeviceTest {
    public static void main (String[] args) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone xsmax");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "IOS");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);

        capabilities.setCapability("xcodeSigningId","iPhone Developer");
        capabilities.setCapability("udid","00008020-00166C503A29002E");
        capabilities.setCapability("updatedWDABundleId","com.example.apple-samplecode.UICatalog");
        capabilities.setCapability("xcodeOrgId","QHK4V423FJ");
        capabilities.setCapability(MobileCapabilityType.APP, "/Users/ho.nguyen/Documents/Trainings/Appium/demo-apps/UIKitCatalog_real.app");

        IOSDriver driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        // open Alert View
        driver.findElementByAccessibilityId("Alert Views").click();

        // Enter Text Entry
        driver.findElementByXPath("//XCUIElementTypeStaticText[@name=\"Text Entry\"]").click();
        driver.findElementByXPath("//XCUIElementTypeTextField").sendKeys("Hello");
        driver.findElementByAccessibilityId("OK").click();

        // Select Confirm and check the message
        driver.findElementByAccessibilityId("Confirm / Cancel").click();

        WebElement messageEl = driver.findElementByXPath("//*[contains(@name,'message')]");
        Assert.assertEquals(messageEl.getText(), "A message should be a short, complete sentence.");
        System.out.println(messageEl.getText());

    }
}
