package e_commerce_sample;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class ECommerceBase {
    public static AndroidDriver<AndroidElement> capabilities(String deviceType) throws MalformedURLException {

        File appDir = new File("src");
        File app = new File(appDir, "main/resources/General-Store.apk");

        DesiredCapabilities cap = new DesiredCapabilities();

        if(deviceType.equalsIgnoreCase("real")){
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device"); // get all android device to run regardless of real/emulator
        } else if (deviceType.equalsIgnoreCase("emulator")){
            cap.setCapability(MobileCapabilityType.DEVICE_NAME, "My Emulator - Pixel 2");
        }

        cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");

        AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), cap);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        return driver;
    }
}
