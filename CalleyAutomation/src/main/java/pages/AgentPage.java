package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AgentPage extends BasePage {

    @FindBy(xpath = "//a[contains(text(),'Add Agent')] | //button[contains(text(),'Add Agent')]")
    private WebElement addAgentButton;

    @FindBy(id = "txtAgentName")
    private WebElement agentNameField;

    @FindBy(id = "txtAgentEmail")
    private WebElement agentEmailField;

    @FindBy(id = "txtAgentPhone")
    private WebElement agentPhoneField;

    @FindBy(id = "btnSaveAgent")
    private WebElement saveAgentButton;

 
    @FindBy(xpath = "//*[contains(@class,'success') or contains(text(),'successfully') or contains(text(),'added')]")
    private WebElement successMessage;


    @FindBy(xpath = "//*[contains(@class,'error') or contains(@class,'alert-danger')]")
    private WebElement errorMessage;

  
    public AgentPage(WebDriver driver) {
        super(driver);
    }

    // ─── Navigation ──────────────────────────────────────────────────────────

    public void navigateToAgentsPage() {
   
        try {
            WebElement agentMenu = driver.findElement(
                By.xpath("//a[contains(text(),'Agents') or contains(@href,'agent')]"));
            click(agentMenu);
        } catch (Exception e) {
            System.out.println("Agent menu item not found via link: " + e.getMessage());
        }
    }

 
    public void clickAddAgent() {
        click(addAgentButton);
    }


    public void enterAgentName(String name) {
        type(agentNameField, name);
    }

    
    public void enterAgentEmail(String email) {
        type(agentEmailField, email);
    }


    public void enterAgentPhone(String phone) {
        type(agentPhoneField, phone);
    }


    public void clickSaveAgent() {
        click(saveAgentButton);
    }

    public void addAgent(String name, String email, String phone) {
        clickAddAgent();
        enterAgentName(name);
        enterAgentEmail(email);
        enterAgentPhone(phone);
        clickSaveAgent();
    }

  
    public boolean isAgentAddedSuccessfully() {
        try {
            return successMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    public String getErrorMessage() {
        try {
            return getText(errorMessage);
        } catch (Exception e) {
            return "";
        }
    }
}
