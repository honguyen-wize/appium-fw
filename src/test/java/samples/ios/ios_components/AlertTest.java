package samples.ios.ios_components;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class AlertTest extends BaseIOS {
    IOSDriver driver;

    @Test
    public void alertTest() throws MalformedURLException {
        driver = capabilitiesIOS();

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

        driver.findElementByAccessibilityId("Confirm").click();
    }
}
