# Calley Automation Testing Project

Selenium + TestNG + Java automation for the Calley Team Account setup.

---

## Project Structure

```
CalleyAutomation/
│
├── pom.xml                          ← Maven dependencies
├── testng.xml                       ← TestNG suite (run all tests from here)
│
├── src/
│   ├── main/java/
│   │   ├── pages/
│   │   │   ├── BasePage.java        ← Common Selenium actions (click, type, etc.)
│   │   │   ├── RegistrationPage.java
│   │   │   ├── LoginPage.java
│   │   │   ├── AgentPage.java
│   │   │   └── CSVUploadPage.java
│   │   └── utils/
│   │       ├── DriverManager.java   ← WebDriver setup/teardown
│   │       ├── ConfigReader.java    ← Reads .properties files
│   │       └── WaitHelper.java      ← Explicit wait methods
│   │
│   └── test/
│       ├── java/tests/
│       │   ├── BaseTest.java        ← @BeforeMethod/@AfterMethod
│       │   ├── RegistrationTest.java
│       │   ├── LoginTest.java
│       │   ├── AgentTest.java
│       │   └── CSVUploadTest.java
│       │
│       └── resources/
│           ├── config/
│           │   ├── config.properties    ← URLs, browser, timeouts
│           │   └── testdata.properties  ← All test input data
│           └── testdata/
│               └── sample_contacts.csv ← CSV file for upload test
```

---

## Prerequisites

| Tool           | Version  |
|----------------|----------|
| Java JDK       | 11+      |
| Maven          | 3.6+     |
| Chrome Browser | Latest   |
| IDE            | IntelliJ / Eclipse |


## Test Scenarios

| # | Test Class          | What it tests                                   |
|---|---------------------|-------------------------------------------------|
| 1 | RegistrationTest    | New user registration with Calley Teams plan    |
| 2 | LoginTest           | Valid and invalid login scenarios               |
| 3 | AgentTest           | Login → Add new agent                           |
| 4 | CSVUploadTest       | Login → Power Import → Upload CSV → Map → Import|

