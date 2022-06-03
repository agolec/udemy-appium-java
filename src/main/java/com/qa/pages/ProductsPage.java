package com.qa.pages;

import com.qa.BaseTest;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.testng.Assert;

public class ProductsPage extends BaseTest {
    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"PRODUCTS\"]") private MobileElement productHeader;

    public String getTitle(){
        return getAttribute(productHeader,"text");

    }
}