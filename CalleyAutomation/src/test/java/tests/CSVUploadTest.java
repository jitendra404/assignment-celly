package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CSVUploadPage;
import pages.LoginPage;
import utils.ConfigReader;


public class CSVUploadTest extends BaseTest {

    @Test(description = "Login and upload a CSV call list via Power Import")
    public void testCSVUpload() {

        // --- Read test data ---
        String email      = ConfigReader.get("user.email");
        String password   = ConfigReader.get("user.password");
        String listName   = ConfigReader.getData("csv.list.name");
        String agentName  = ConfigReader.getData("agent.name");
        String csvPath    = ConfigReader.getData("csv.file.path");

        // --- Step 1: Login ---
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage(loginUrl);
        loginPage.login(email, password);
        Assert.assertTrue(loginPage.isLoginSuccessful(),
            "Login failed before CSV upload test.");
        System.out.println("Login successful. URL: " + loginPage.getCurrentUrl());

        // --- Step 2: Navigate to Power Import ---
        CSVUploadPage csvPage = new CSVUploadPage(driver);
        csvPage.navigateToPowerImport();
        System.out.println("Navigated to Power Import. URL: " + csvPage.getCurrentUrl());

        // --- Step 3: Enter list name ---
        csvPage.enterListName(listName);
        System.out.println("List name entered: " + listName);

        // --- Step 4: Select agent ---
        csvPage.selectAgent(agentName);
        System.out.println("Agent selected: " + agentName);

        // --- Step 5: Upload CSV file ---
        csvPage.uploadCSVFile(csvPath);
        System.out.println("CSV file uploaded from: " + csvPath);

        // --- Step 6: Click Upload/Next ---
        csvPage.clickUpload();

        // --- Step 7: Map fields ---
        csvPage.mapFields();
        System.out.println("Fields mapped.");

        // --- Step 8: Click Import ---
        csvPage.clickImport();
        System.out.println("Import button clicked.");

        // --- Step 9: Validate import success ---
        boolean isSuccess = csvPage.isImportSuccessful();
        if (!isSuccess) {
            System.out.println("CSV import error: " + csvPage.getErrorMessage());
        }

        Assert.assertTrue(isSuccess,
            "CSV import failed. Error: " + csvPage.getErrorMessage());
        System.out.println("✅ CSV Upload test PASSED.");
    }
}
