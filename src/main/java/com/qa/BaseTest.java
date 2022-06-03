package com.qa;

import com.qa.utils.TestUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;


public class BaseTest {
    protected static AppiumDriver driver;
    protected static Properties props;
    InputStream inputStream;

    public BaseTest(){
        System.out.println("printing Driver from base test: " + driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @Parameters({"platformName","platformVersion","deviceName"})
    @BeforeTest
    public void beforeTest(String platformName, String platformVersion, String deviceName) throws Exception {
        try {
            //instantiate a new Properties object.
            props = new Properties();
            final String PROPERTY_FILE_NAME = "config.properties";
            //created an inputStream for our config file.
            inputStream = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE_NAME);
            props.load(inputStream);

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability(MobileCapabilityType.PLATFORM_NAME, platformName);
            caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, props.getProperty("androidAutomationName"));
            caps.setCapability("appPackage", props.getProperty("androidAppPackage"));
            caps.setCapability("appActivity", props.getProperty("androidAppActivity"));
            URL appURL = getClass().getClassLoader().getResource(props.getProperty("androidAppLocation"));
            caps.setCapability("app",appURL);
            caps.setCapability("appWaitActivity", "com.swaglabsmobileapp.MainActivity");
            caps.setCapability("udid", "emulator-5554");
            caps.setCapability("newCommandTimeout", 300);

            URL url = new URL(props.getProperty("appiumURL"));
            driver = new AndroidDriver(url, caps);
            String sessionId = driver.getSessionId().toString();
            //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            System.out.println("Session ID: " + sessionId);
            System.out.println("Driver address from @BeforeTest: " + driver);
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public void waitForVisibility(MobileElement e){
        WebDriverWait wait = new WebDriverWait(driver, TestUtils.WAIT);
        wait.until(ExpectedConditions.visibilityOf(e));
    }
    public void click(MobileElement e){
        waitForVisibility(e);
        e.click();
    }
    public void sendKeys(MobileElement e,String keysToSend){
        waitForVisibility(e);
        e.sendKeys(keysToSend);
    }
    public String getAttribute(MobileElement e,String attribute){
        waitForVisibility(e);
        return e.getAttribute(attribute);
    }
    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
