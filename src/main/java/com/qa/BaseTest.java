package com.qa;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected AppiumDriver driver;

    @BeforeTest
    public void beforeTest(){
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Any Device Name");
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            caps.setCapability("appPackage", "com.swaglabsmobileapp");
            caps.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
            //caps.setCapability("avd","Pixel_2");
            //caps.setCapability("app","C:\\Users\\agolec\\Downloads\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
            caps.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
            caps.setCapability("udid", "emulator-5554");
            caps.setCapability("newCommandTimeout", 300);

            URL url = new URL("http://0.0.0.0:4723/wd/hub");
            driver = new AndroidDriver(url, caps);
            String sessionId = driver.getSessionId().toString();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @AfterTest
    public void afterTest(){

    }
}
