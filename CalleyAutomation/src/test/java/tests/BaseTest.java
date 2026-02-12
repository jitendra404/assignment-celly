package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.DriverManager;

public class BaseTest {

    protected WebDriver driver;

    protected String registrationUrl = ConfigReader.get("registration.url");
    protected String loginUrl        = ConfigReader.get("login.url");

    @BeforeMethod
    public void setUp() {
        driver = DriverManager.initDriver();
        System.out.println("Browser launched for test.");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
        System.out.println("Browser closed after test.");
    }
}
