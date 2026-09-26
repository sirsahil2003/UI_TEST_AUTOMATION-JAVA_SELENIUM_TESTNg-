package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentTestManager;
import utils.WaitUtils;

public class DemoQaLogin {
    //POM Design Pattern
    private WebDriver driver;
    WebDriverWait wait ;

    //locators
    private By usernameField = By.id("userName");
    private By passwordField = By.id("password");
    private By loginButton =
            By.id("login");

    //constructor'
    public DemoQaLogin(WebDriver driver){
        this.driver = driver;
        System.out.println("Constructor called successfully");
    }

    //Actions
    public void enterUsername(String username){
        WebElement element = WaitUtils.waitForElementToBeClickable(driver,usernameField);
        element.sendKeys(username);
        //step logging using extent report

        ExtentTestManager.getTest().log(Status.INFO,"user name has been entered today "+username);

    }

    public void enterPassword(String password){
        WebElement element = WaitUtils.waitForElementToBeClickable(driver,passwordField);
        element.sendKeys(password);
        //step logging using extent report
        ExtentTestManager.getTest().log(Status.INFO,"user name has been entered "+password);
    }

    public void clickloginButton(){
        WaitUtils.waitForElementToBeClickable(driver,loginButton).click();
        if(this.getWindowTitle().contains("Quora")){
            ExtentTestManager.getTest().pass("Login case passed successfully");
        }
    }

    public String getWindowTitle(){
        return driver.getTitle();
    }

    //Business Method
    public void login(String username,String password)  {
        enterUsername(username);
        enterPassword(password);
        clickloginButton();
    }


}
