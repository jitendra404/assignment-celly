package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

    @FindBy(id = "txtEmailId")
    private WebElement emailField;

    @FindBy(id = "txtPassword")
    private WebElement passwordField;

    @FindBy(id = "btnLogIn")
    private WebElement loginButton;


    @FindBy(xpath = "//*[@id='dashboardHeader'] | //*[contains(@class,'dashboard')] | //*[contains(text(),'Dashboard')]")
    private WebElement dashboardElement;

  
    @FindBy(xpath = "//*[contains(@class,'error') or contains(@class,'alert-danger') or contains(text(),'Invalid')]")
    private WebElement loginErrorMessage;

   
    public LoginPage(WebDriver driver) {
        super(driver);
    }

  
    public void openPage(String url) {
        navigateTo(url);
    }

 
    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

  
    public void clickLogin() {
        click(loginButton);
    }

 
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        try {
           
            return !getCurrentUrl().toLowerCase().contains("login");
        } catch (Exception e) {
            return false;
        }
    }

   
    public String getErrorMessage() {
        try {
            return getText(loginErrorMessage);
        } catch (Exception e) {
            return "";
        }
    }
}
