import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class AndroidFindElements {

    public static void main(String[] args) throws Exception {
        AppiumDriver driver =  CreateDriverSession.initializeDriver("Android");
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

        By accessibility = MobileBy.AccessibilityId("Accessibility");
        System.out.println("text: " + driver.findElement(accessibility).getText());
        System.out.println("text: " + driver.findElement(accessibility).getAttribute("text"));

        String isChecked = "";
        String isEnabled = "";
        String isSelected = "";

        isChecked = driver.findElement(accessibility).getAttribute("checked");
        isEnabled = driver.findElement(accessibility).getAttribute("enabled");
        isSelected = driver.findElement(accessibility).getAttribute("selected");
        checkAttribute(isChecked);
        checkAttribute(isEnabled);
        checkAttribute(isSelected);

    }

    private static void checkAttribute(String booleanValue) {
        if(booleanValue.equalsIgnoreCase("false")){
            System.out.println(booleanValue);
        } else {
            System.out.println(booleanValue);
        }
    }
}
