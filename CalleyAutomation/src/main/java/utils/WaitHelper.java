package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class WaitHelper {

    private WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        int seconds = Integer.parseInt(ConfigReader.get("explicit.wait"));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
    }

    /** Wait until element is visible */
    public WebElement waitForVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
 

    public WebElement waitForClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

   
    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    
    public boolean waitForUrl(String urlFragment) {
        return wait.until(ExpectedConditions.urlContains(urlFragment));
    }

  
    public boolean waitForText(By locator, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

  
    public boolean waitForInvisibility(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }
}
