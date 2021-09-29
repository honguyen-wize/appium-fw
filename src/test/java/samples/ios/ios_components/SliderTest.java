package samples.ios.ios_components;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SliderTest  extends BaseIOS {
    IOSDriver driver;

    @Test
    public void sliderTest() throws MalformedURLException, InterruptedException {
        driver = capabilitiesIOS();

        driver.findElementByAccessibilityId("Sliders").click();
        IOSElement sliderEl = (IOSElement) driver.findElementByXPath("(//XCUIElementTypeSlider)[1]");
        sliderEl.setValue("0.7%");
        sliderEl.setValue("0%");
        sliderEl.setValue("1%");

        System.out.println(sliderEl.getAttribute("value"));
        System.out.println(sliderEl.getText());

        Assert.assertEquals("100%", sliderEl.getAttribute("value"));
    }
}
