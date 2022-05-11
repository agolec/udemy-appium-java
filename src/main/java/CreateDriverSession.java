import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class CreateDriverSession {
    public static void main(String[] args) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        final String APP_PACKAGE = "io.appium.android.apis";

        String appUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "ApiDemos-debug.apk";

        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 3");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        caps.setCapability(MobileCapabilityType.UDID,"emulator-5554");
        /*
            Use the appPackage and appActivity capabilities to launch an emulator programatically.

            When using this, comment out code on line 30 where MobileCapability.APP has been set.

            This is so that you don't try to install an APK to an emulator that already has it.
         */
        caps.setCapability("appPackage", APP_PACKAGE);
        caps.setCapability("appActivity", APP_PACKAGE + ".accessibility.AccessibilityNodeProviderActivity");
        //caps.setCapability(MobileCapabilityType.APP,appUrl);
        //setting AVD name will allow us to launch an AVD, so
        //the emulator doesn't have to be open before we execute the code.
        caps.setCapability("avd","Pixel_3");
        caps.setCapability("avdLaunchTimeout","180000");

        URL url = new URL("http://0.0.0.0:4723/wd/hub");

        AppiumDriver driver = new AndroidDriver(url,caps);


    }
}
