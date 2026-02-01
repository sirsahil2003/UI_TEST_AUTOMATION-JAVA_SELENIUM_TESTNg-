package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.DemoQaLogin;

public class DemoQaTest extends BaseTest {
    private DemoQaLogin loginPage;

    @BeforeMethod
    public void initPages() {
        loginPage = new DemoQaLogin(driver);
    }


    @Test(dataProvider = "loginData")
    public void verifyLoginFunctionality(String username,String password){
        loginPage.login(username,password);

    }
    @DataProvider(name = "loginData",parallel = true)
    public Object[][] getLoginData(){
        return new Object[][]{
                {"sahil","Sahil@123"},
                {"sahil","Sahil@123"}

        };
    }


}
