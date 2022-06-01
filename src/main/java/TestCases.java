import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TestCases {
    AppiumDriver driver;

    // Test to just check that TestNG works.
    //Will print out 20 . symbols and between generating these,
    //it will wait a random number of milliseconds (upper bound 500, or half a second,
    //before printing the next . symbol.
    @Test
    void setup() throws InterruptedException {

    }

    @Test
    public void invalidUserName() {
        MobileElement userNameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        userNameTxtFld.sendKeys("invalid user name");
        passwordTxtFld.sendKeys("lol");
        loginBtn.click();

        MobileElement errText = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");

        String actualErrorText = errText.getAttribute("text");
        System.out.println("Actual error text is " + actualErrorText);

        String expectedText =  "Username and password do not match any user in this service.";

        Assert.assertEquals(actualErrorText,expectedText);

    }

    @Test
    public void invalidPassword(){
        MobileElement userNameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        userNameTxtFld.sendKeys("standard_user");
        passwordTxtFld.sendKeys("invalidPassword");
        loginBtn.click();

        MobileElement errText = (MobileElement) driver.findElementByXPath("//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView");

        String actualErrorText = errText.getAttribute("text");
        System.out.println("Actual error text is " + actualErrorText);
        String expectedText =  "Username and password do not match any user in this service.";

        Assert.assertEquals(actualErrorText,expectedText);

    }

    @Test
    void successfulLogin() {
        MobileElement userNameTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Username");
        MobileElement passwordTxtFld = (MobileElement) driver.findElementByAccessibilityId("test-Password");
        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("test-LOGIN");

        userNameTxtFld.sendKeys("standard_user");
        passwordTxtFld.sendKeys("secret_sauce");
        loginBtn.click();

        MobileElement productHeader = (MobileElement) driver.findElementByXPath("//android.widget.TextView[@text=\"PRODUCTS\"]");

        String actualProductTitle = productHeader.getAttribute("text");
        System.out.println("Actual product title is " + actualProductTitle);
        String expectedProductTitle = "PRODUCTS";

        Assert.assertEquals(actualProductTitle,expectedProductTitle);

    }

    @Test
    void tearDown() {

    }

    @BeforeClass
    public void beforeClass() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Any Device Name");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability("appPackage", "com.swaglabsmobileapp");
        caps.setCapability("appActivity", "com.swaglabsmobileapp.SplashActivity");
        //caps.setCapability("avd","Pixel_2");
        //caps.setCapability("app","C:\\Users\\agolec\\Downloads\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");
        caps.setCapability("appWaitActivity","com.swaglabsmobileapp.MainActivity");
        caps.setCapability("udid","emulator-5554");
        caps.setCapability("newCommandTimeout", 300);

        URL url = new URL("http://0.0.0.0:4723/wd/hub");
        driver = new AndroidDriver(url, caps);
        String sessionId = driver.getSessionId().toString();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
