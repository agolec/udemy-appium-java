package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest {
    @AndroidFindBy(accessibility = "test-Username") private MobileElement userNameTextField;
    @AndroidFindBy(accessibility = "test-Password") private MobileElement passwordTxtFld;
    @AndroidFindBy(accessibility = "test-LOGIN") private MobileElement loginBtn;
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView") private MobileElement errText;


    public LoginPage enterUserName(String userName){
        sendKeys(userNameTextField,userName);
        return this;
    }
    public LoginPage enterPassword(String password){
        sendKeys(passwordTxtFld,password);
        return this;
    }
    public ProductsPage pressLoginBtn(){
        click(loginBtn);
        return new ProductsPage();
    }

    public String getErrorText(){
        return getAttribute(errText,"text");
    }
}
