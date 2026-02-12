package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class DriverManager {

    private static WebDriver driver;
    private static WebDriverWait wait;

    
    public static WebDriver initDriver() {
        String browser    = ConfigReader.get("browser");
        boolean headless  = Boolean.parseBoolean(ConfigReader.get("headless"));
        int implicitWait  = Integer.parseInt(ConfigReader.get("implicit.wait"));
        int pageLoad      = Integer.parseInt(ConfigReader.get("page.load.timeout"));
        int explicitWait  = Integer.parseInt(ConfigReader.get("explicit.wait"));

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=new");
            }
            options.addArguments("--start-maximized");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-popup-blocking");
            driver = new ChromeDriver(options);

        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();

        } else {
            throw new RuntimeException("Browser '" + browser + "' is not supported. Use chrome or firefox.");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoad));
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(explicitWait));

        return driver;
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new RuntimeException("Driver not initialised. Call initDriver() first.");
        }
        return driver;
    }

    public static WebDriverWait getWait() {
        return wait;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
