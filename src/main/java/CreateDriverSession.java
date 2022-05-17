import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.URL;

public class CreateDriverSession {

    public static AppiumDriver initializeDriver(String platformName) throws Exception {
        int switchNum = -1;
        if(platformName.equalsIgnoreCase("Android")){
            switchNum = 1;
        } else if (platformName.equalsIgnoreCase("iOS")){
            switchNum = 2;
        }
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,platformName);
        caps.setCapability("newCommandTimeout",300);

        URL url = new URL("http://0.0.0.0:4723/wd/hub");

        switch(switchNum){
            case 1:
                caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel 3");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
                caps.setCapability(MobileCapabilityType.UDID,"emulator-5554");
                //  caps.setCapability("avd","Pixel_3");
                //  caps.setCapability("avdLaunchTimeout, 180000)
                String andAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                        + File.separator + "resources" + File.separator + "ApiDemos-debug.apk";
                caps.setCapability("appPackage","io.appium.android.apis");
                caps.setCapability("appActivity","io.appium.android.apis.ApiDemos");
                return new AndroidDriver(url, caps);
            case 2:
                caps.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 11");
                caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
                caps.setCapability(MobileCapabilityType.UDID,"77F6B8F0-8877-4EDF-8c8c-99DBE64A93FF");
                //      caps.setCapability("xcodeOrgId","L8T9J4R323);
                //      caps.setCapability("xcodeSigningId","iPhone Developer");
                String iOSAppURL = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator
                        + "resources" + File.separator + "UIKitCatalog-iphonesimulator.app";
                //  caps.setCapability(MobileCapabilityType.APP, iOSAppUrl);
                caps.setCapability("simulatorStartupTimeout", 180000);
                caps.setCapability("bundleId","com.example.apple-samplecode.UICatalog");
                return new IOSDriver(url, caps);
            default:
                throw new Exception("incorrectly set platform name");
        }
    }
}
