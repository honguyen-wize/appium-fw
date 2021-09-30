package pages;

import common.BasePage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class FormPage extends BasePage {
    public FormPage(AndroidDriver driver){
        super(driver);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    public WebElement nameField;

    @AndroidFindBy(xpath = "//*[@text='Female']")
    public WebElement femaleOption;

    @AndroidFindBy(id = "android:id/text1")
    public WebElement countrySelection;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    public WebElement btnLetShop;


    public HomePage fillForm(String name){
        nameField.sendKeys(name);
        femaleOption.click();
        btnLetShop.click();
        return new HomePage(driver);
    }
}
