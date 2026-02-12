package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class RegistrationPage extends BasePage {

   
    @FindBy(id = "txtName")
    private WebElement nameField;

    @FindBy(id = "txtEmail")
    private WebElement emailField;

    

    @FindBy(id = "txtPassword")
    private WebElement passwordField;
    
    @FindBy(id = "txt_mobile")
    private WebElement phoneField;

    @FindBy(id = "txtConfirmPassword")
    private WebElement confirmPasswordField;

    @FindBy(id = "ddlCountry")
    private WebElement countryDropdown;

   
    @FindBy(xpath = "//input[@type='radio' and contains(@value,'Teams')]")
    private WebElement calleyTeamsPlanRadio;

    
    @FindBy(xpath = "//*[contains(text(),'Calley Teams')]")
    private WebElement calleyTeamsPlanLabel;

    @FindBy(id = "btnRegister")
    private WebElement registerButton;

    // Success / Error messages
    @FindBy(xpath = "//*[contains(@class,'success') or contains(text(),'successfully') or contains(text(),'registered')]")
    private WebElement successMessage;

    @FindBy(xpath = "//*[contains(@class,'error') or contains(@class,'alert-danger')]")
    private WebElement errorMessage;

    // ─── Constructor ─────────────────────────────────────────────────────────
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // ─── Actions ─────────────────────────────────────────────────────────────

    /** Navigate to the registration page */
    public void openPage(String url) {
        navigateTo(url);
    }

    /** Enter full name */
    public void enterName(String name) {
        type(nameField, name);
    }

    /** Enter email address */
    public void enterEmail(String email) {
        type(emailField, email);
    }

    /** Enter phone number */
    public void enterPhone(String phone) {
        type(phoneField, phone);
    }

    /** Enter password */
    public void enterPassword(String password) {
        type(passwordField, password);
    }

    /** Enter confirm password */
    public void enterConfirmPassword(String confirmPassword) {
        type(confirmPasswordField, confirmPassword);
    }

    /** Select country from dropdown */
    public void selectCountry(String country) {
        try {
            selectByText(countryDropdown, country);
        } catch (Exception e) {
            System.out.println("Country dropdown not found or value mismatch: " + e.getMessage());
        }
    }

    /** Select the Calley Teams plan */
    public void selectCalleyTeamsPlan() {
        try {
            jsClick(calleyTeamsPlanRadio);
        } catch (Exception e) {
            // Try clicking the label text as a fallback
            try {
                jsClick(calleyTeamsPlanLabel);
            } catch (Exception ex) {
                System.out.println("Could not select Calley Teams plan: " + ex.getMessage());
            }
        }
    }

    /** Click the Register button */
    public void clickRegister() {
        click(registerButton);
    }

    /**
     * Full registration flow in one call.
     */
    public void registerUser(String name, String email, String phone,
                             String password, String confirmPassword, String country) {
        enterName(name);
        enterEmail(email);
        enterPhone(phone);
        enterPassword(password);
        enterConfirmPassword(confirmPassword);
        selectCountry(country);
        selectCalleyTeamsPlan();
        clickRegister();
    }

    // ─── Verifications ───────────────────────────────────────────────────────

    /** Returns true if the success message is displayed */
    public boolean isRegistrationSuccessful() {
        try {
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Returns error message text if present */
    public String getErrorMessage() {
        try {
            return getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }
}

