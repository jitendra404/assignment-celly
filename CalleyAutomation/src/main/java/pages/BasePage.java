package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.WaitHelper;


public class BasePage {

    protected WebDriver driver;
    protected WaitHelper wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WaitHelper(driver);
        PageFactory.initElements(driver, this);
    }

 
    protected void type(WebElement element, String text) {
        wait.waitForClickable(element);
        element.clear();
        element.sendKeys(text);
    }

   
    protected void click(WebElement element) {
        wait.waitForClickable(element);
        element.click();
    }

    protected void jsClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

   
    protected void selectByText(WebElement element, String text) {
        wait.waitForClickable(element);
        new Select(element).selectByVisibleText(text);
    }


    protected void selectByValue(WebElement element, String value) {
        wait.waitForClickable(element);
        new Select(element).selectByValue(value);
    }

    
    protected boolean isDisplayed(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

 
    protected String getText(WebElement element) {
        return element.getText().trim();
    }

  
    public String getPageTitle() {
        return driver.getTitle();
    }

    
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

   
    public void navigateTo(String url) {
        driver.get(url);
    }


    protected void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
