package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AgentPage;
import pages.LoginPage;
import utils.ConfigReader;


public class AgentTest extends BaseTest {

    @Test(description = "Login and add a new agent")
    public void testAddAgent() {

   
        String email       = ConfigReader.get("user.email");
        String password    = ConfigReader.get("user.password");
        String agentName   = ConfigReader.getData("agent.name");
        String agentEmail  = ConfigReader.getData("agent.email");
        String agentPhone  = ConfigReader.getData("agent.phone");

        // --- Step 1: Login ---
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage(loginUrl);
        loginPage.login(email, password);
        Assert.assertTrue(loginPage.isLoginSuccessful(),
            "Login failed before agent test. URL: " + loginPage.getCurrentUrl());
        System.out.println("Login successful. URL: " + loginPage.getCurrentUrl());

        // --- Step 2: Navigate to Agents page ---
        AgentPage agentPage = new AgentPage(driver);
        agentPage.navigateToAgentsPage();
        System.out.println("Navigated to Agents page. URL: " + agentPage.getCurrentUrl());

        // --- Step 3: Add new agent ---
        agentPage.addAgent(agentName, agentEmail, agentPhone);
        System.out.println("Add Agent form submitted.");

        // --- Step 4: Validate success ---
        boolean isAdded = agentPage.isAgentAddedSuccessfully();
        if (!isAdded) {
            System.out.println("Add agent error: " + agentPage.getErrorMessage());
        }

        Assert.assertTrue(isAdded,
            "Agent was not added. Error: " + agentPage.getErrorMessage());
        System.out.println("✅ Add Agent test PASSED.");
    }
}
