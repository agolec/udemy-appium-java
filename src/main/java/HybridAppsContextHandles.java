import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class HybridAppsContextHandles {

    public static void main(String[] args) throws Exception {
        AppiumDriver driver = CreateDriverSession.initializeDriver("Android");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //By views = MobileBy.AccessibilityId("Views");
        driver.findElement(MobileBy.AccessibilityId("Views")).click();

        Dimension size = driver.manage().window().getSize();

        for (int i = 0; i < 4; i++) {
            TouchAction t = new TouchAction(driver);
            t.press(PointOption.point(size.width / 2, (int) (size.height * 0.8)))
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
                    .moveTo(PointOption.point(size.width / 2, (int) (size.height * 0.2)))
                    .release()
                    .perform();
        }

        driver.findElement(MobileBy.AccessibilityId("WebView2")).click();
        Set<String> contextHandles = driver.getContextHandles();

        for(String contextHandle: contextHandles){
            System.out.println(contextHandle);
        }

        driver.context(contextHandles.toArray()[1].toString());

        System.out.println("first element " + driver.findElement(By.cssSelector("body > h1")).getText());
        System.out.println("second element " + driver.findElement(By.xpath("//*[@id=\"i_am_a_textbox\"]")).getText());

        driver.context("NATIVE_APP");
    }

}
