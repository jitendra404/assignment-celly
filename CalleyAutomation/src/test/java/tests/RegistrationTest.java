package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.ConfigReader;


public class RegistrationTest extends BaseTest {

    @Test(description = "Register a new user and select Calley Teams plan")
    public void testUserRegistration() {

        // --- Read test data ---
        String name            = ConfigReader.getData("reg.name");
        String email           = ConfigReader.getData("reg.email");
        String phone           = ConfigReader.getData("reg.phone");
        String password        = ConfigReader.getData("reg.password");
        String country         = ConfigReader.getData("reg.country");

        // --- Initialise page ---
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // --- Step 1: Navigate to registration page ---
        registrationPage.openPage(registrationUrl);
        System.out.println("Navigated to: " + registrationUrl);

        // --- Step 2: Fill in registration form ---
        registrationPage.enterName(name);
        registrationPage.enterEmail(email);
        registrationPage.enterPhone(phone);
        registrationPage.enterPassword(password);
        registrationPage.selectCountry(country);

        // --- Step 3: Select Calley Teams plan ---
        registrationPage.selectCalleyTeamsPlan();
        System.out.println("Selected Calley Teams plan.");

        // --- Step 4: Submit the form ---
        registrationPage.clickRegister();
        System.out.println("Registration form submitted.");

        // --- Step 5: Validate success ---
        boolean isSuccess = registrationPage.isRegistrationSuccessful();
        if (!isSuccess) {
            String error = registrationPage.getErrorMessage();
            System.out.println("Registration error: " + error);
        }

        Assert.assertTrue(isSuccess,
            "Registration failed. Error: " + registrationPage.getErrorMessage());
        System.out.println("Registration test PASSED.");
    }
}
