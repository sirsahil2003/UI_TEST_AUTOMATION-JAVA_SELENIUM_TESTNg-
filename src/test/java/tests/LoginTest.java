package tests;

import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    LoginPage loginPage = new LoginPage(driver);

    @Test (dataProvider = "loginData")
    public void verifyLoginFunctionality(String username,String password){
       loginPage.login(username,password);

    }
    @DataProvider(name = "loinData",parallel = true)
    public Object[][] getLoginData(){
        return new Object[][]{
            {"sahil123","u8479"},
                {"faix123","hfkjerhfj"}

        };
    }
}
