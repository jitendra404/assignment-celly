package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ConfigReader;


public class LoginTest extends BaseTest {

    @Test(description = "Login with valid credentials and verify dashboard is shown")
    public void testValidLogin() {

        // --- Read credentials ---
        String email    = ConfigReader.get("user.email");
        String password = ConfigReader.get("user.password");

        // --- Initialise page ---
        LoginPage loginPage = new LoginPage(driver);

        // --- Step 1: Navigate to login page ---
        loginPage.openPage(loginUrl);
        System.out.println("Navigated to: " + loginUrl);

        // --- Step 2: Enter credentials and login ---
        loginPage.login(email, password);
        System.out.println("Login form submitted.");

        // --- Step 3: Validate login success ---
        boolean isLoggedIn = loginPage.isLoginSuccessful();

        if (!isLoggedIn) {
            System.out.println("Login error: " + loginPage.getErrorMessage());
            System.out.println("Current URL: " + loginPage.getCurrentUrl());
        }

        Assert.assertTrue(isLoggedIn,
            "Login failed. Still on login page. URL: " + loginPage.getCurrentUrl());
        System.out.println("✅ Login test PASSED. Current URL: " + loginPage.getCurrentUrl());
    }

    @Test(description = "Login with invalid credentials and verify error message")
    public void testInvalidLogin() {

        // --- Initialise page ---
        LoginPage loginPage = new LoginPage(driver);

        // --- Step 1: Navigate to login page ---
        loginPage.openPage(loginUrl);

        // --- Step 2: Enter wrong credentials ---
        loginPage.login("wrong@email.com", "WrongPass123");
        System.out.println("Invalid login form submitted.");

        // --- Step 3: Still on login page OR error shown ---
        boolean stillOnLoginPage = loginPage.getCurrentUrl().toLowerCase().contains("login");
        Assert.assertTrue(stillOnLoginPage,
            "Expected to stay on login page for invalid credentials.");
        System.out.println("✅ Invalid login test PASSED. Stayed on login page as expected.");
    }
}
