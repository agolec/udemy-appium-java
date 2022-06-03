package com.qa.tests;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;

    @BeforeClass
    public void beforeClass(){

    }
    @AfterClass
    public void afterClass(){

    }
    @BeforeMethod
    public void beforeMethod(Method method){
        loginPage = new LoginPage();
        //System.out.println("\n" + "****** Starting test:" + method.getName() + "*****" + "\n");
    }
    @AfterMethod
    public void afterMethod(){

    }
    @Test
    public void invalidUserName() {
        loginPage.enterUserName("invalidusername");
        loginPage.enterPassword("secret_sauce");
        loginPage.pressLoginBtn();
        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = "Username and password do not match any user in this service.";
        System.out.println("Actual error text:" + actualErrorText + "\n" + "Expected Error text: " + expectedErrorText);

        Assert.assertEquals(actualErrorText,expectedErrorText);


    }

    @Test
    public void invalidPassword(){
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("invalidPassword");
        loginPage.pressLoginBtn();

        String actualErrorText = loginPage.getErrorText();
        String expectedErrorText = "Username and password do not match any user in this service.";
        System.out.println("Actual error text:" + actualErrorText + "\n" + "Expected Error text: " + expectedErrorText);

        Assert.assertEquals(actualErrorText,expectedErrorText);
    }

    @Test
    void successfulLogin() {
        loginPage.enterUserName("standard_user");
        loginPage.enterPassword("secret_sauce");
        //initializing a new productsPage instance, since clicking login is expected
        //to bring us to the Products page.
        productsPage = loginPage.pressLoginBtn();

        String actualProductTitle = productsPage.getTitle();
        String expectedProductTitle = "PRODUCTS";
        System.out.println("Actual product title is " + actualProductTitle + "\n" + "Expected title is: " + expectedProductTitle);

        Assert.assertEquals(actualProductTitle,expectedProductTitle);

    }


}
