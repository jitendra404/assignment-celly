package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;


public class CSVUploadPage extends BasePage {

   
    @FindBy(xpath = "//a[contains(text(),'Power Import')] | //a[contains(text(),'power import')]")
    private WebElement powerImportLink;


    @FindBy(id = "txtListName")
    private WebElement listNameField;

   
    @FindBy(id = "ddlAgent")
    private WebElement agentDropdown;

   
    @FindBy(xpath = "//input[@type='file']")
    private WebElement fileInput;

 
    @FindBy(xpath = "//button[contains(text(),'Upload')] | //input[@value='Upload'] | //button[contains(text(),'Next')]")
    private WebElement uploadButton;


    @FindBy(xpath = "//button[contains(text(),'Import')] | //input[@value='Import']")
    private WebElement importButton;


    @FindBy(xpath = "//*[contains(@class,'success') or contains(text(),'imported') or contains(text(),'successfully')]")
    private WebElement importSuccessMessage;


    @FindBy(xpath = "//*[contains(@class,'error') or contains(@class,'alert-danger')]")
    private WebElement errorMessage;

  
    public CSVUploadPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToPowerImport() {
     
        try {
            WebElement callListMenu = driver.findElement(
                By.xpath("//a[contains(text(),'Call List')] | //span[contains(text(),'Call List')]"));
            click(callListMenu);
        } catch (Exception e) {
            System.out.println("Call List menu not found: " + e.getMessage());
        }

      
        try {
            click(powerImportLink);
        } catch (Exception e) {
            System.out.println("Power Import link not found: " + e.getMessage());
        }
    }

 
    public void enterListName(String listName) {
        type(listNameField, listName);
    }

  
    public void selectAgent(String agentName) {
        try {
            selectByText(agentDropdown, agentName);
        } catch (Exception e) {
           
            try {
                WebElement option = driver.findElement(
                    By.xpath("//*[contains(text(),'" + agentName + "') and (ancestor::select or @role='option')]"));
                click(option);
            } catch (Exception ex) {
                System.out.println("Could not select agent '" + agentName + "': " + ex.getMessage());
            }
        }
    }

   
    public void uploadCSVFile(String filePath) {
        File file = new File(filePath);
        String absolutePath = file.getAbsolutePath();
        System.out.println("Uploading file: " + absolutePath);
        fileInput.sendKeys(absolutePath);
    }

 
    public void clickUpload() {
        click(uploadButton);
    }

   
    public void mapFields() {
       
        try {
            java.util.List<WebElement> mappingDropdowns = driver.findElements(
                By.xpath("//select[contains(@id,'map') or contains(@name,'map') or contains(@class,'map')]"));

            String[] expectedHeaders = {"Name", "Phone", "Email", "Notes"};
            for (int i = 0; i < mappingDropdowns.size() && i < expectedHeaders.length; i++) {
                try {
                    selectByText(mappingDropdowns.get(i), expectedHeaders[i]);
                } catch (Exception e) {
                    System.out.println("Could not map field " + i + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("No field mapping dropdowns found (auto-mapped): " + e.getMessage());
        }
    }

   
    public void clickImport() {
        click(importButton);
    }

 
    public void uploadCallList(String listName, String agentName, String csvFilePath) {
        enterListName(listName);
        selectAgent(agentName);
        uploadCSVFile(csvFilePath);
        clickUpload();
        mapFields();
        clickImport();
    }

   
    public boolean isImportSuccessful() {
        try {
            return importSuccessMessage.isDisplayed();
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
