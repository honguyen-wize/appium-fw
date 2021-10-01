package pages;

import common.BasePage;
import common.Utils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FormPage extends BasePage {
    public FormPage(AndroidDriver driver){
        super(driver);
    }

    @AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(xpath = "//*[@text='Female']")
    private WebElement femaleOption;

    @AndroidFindBy(id = "android:id/text1")
    private WebElement countrySelection;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    private WebElement btnLetShop;

    @AndroidFindBy(xpath = "//android.widget.Toast[1]")
    private WebElement toastMessage;

    public HomePage fillForm(String name, String countryName){
        // select country
        countrySelection.click();
        Utils.scrollToText(driver, countryName);
        driver.findElement(By.xpath("//*[@text=\"" + countryName + "\"]")).click();

        // input name
        nameField.sendKeys(name);

        // change gender to female
        femaleOption.click();

        // goto Let shop
        btnLetShop.click();

        return new HomePage(driver);
    }

    public void goShopping(){
        btnLetShop.click();
    }

    public String getToastMessage(){
        return toastMessage.getAttribute("name");
    }
}
