package pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ExtentTestManager;
import utils.WaitUtils;

public class LoginPage {
    //POM Design Pattern
    private WebDriver driver;
    WebDriverWait wait ;

    //locators
    private By usernameField = By.xpath("//input[@placeholder = 'Your email']");
    private By passwordField = By.xpath("//input[@placeholder = 'Your password']");
    private By loginButton =
            By.xpath("//div/button[starts-with(@class,'q-click-wrapper') and @type='button']");

    //constructor'
    public LoginPage(WebDriver driver){
        this.driver = driver;
        System.out.println("Constructor called successfully");
    }

    //Actions
    public void enterUsername(String username){
        WebElement element = WaitUtils.waitForElementToBeClickable(driver,usernameField);
        element.sendKeys(username);
        //step logging using extent report
        ExtentTestManager.getTest().log(Status.INFO,"user name has has been entered "+username);

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
