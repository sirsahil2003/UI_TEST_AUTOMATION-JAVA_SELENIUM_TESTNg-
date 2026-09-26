package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DemoQaLogin;

public class DemoQaTest extends BaseTest {
    private DemoQaLogin demoQaLogin;

    @BeforeMethod
    public void initPages() {
        demoQaLogin = new DemoQaLogin(driver);
    }

    @Test(dataProvider = "loginData")
    public void verifyLoginFunctionality(String username,String password){
        demoQaLogin.login(username,password);
    }

    @DataProvider(name = "loginData",parallel = false)
    public Object[][] getLoginData(){
        return new Object[][]{
                {"sahil","Sahil@123"},
                {"sahil","Sahil@127"},
                {"sahil","Sahil@123"}

        };
    }


}
