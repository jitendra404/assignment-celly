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

> **ChromeDriver** is automatically downloaded by WebDriverManager — no manual setup needed.

---

## Setup Steps

1. **Clone / download** the project
2. Open it in **IntelliJ IDEA** (or Eclipse)
3. Maven will auto-download all dependencies from `pom.xml`
4. Update credentials in `src/test/resources/config/config.properties`:
   ```properties
   user.email=your_registered_email@example.com
   user.password=YourPassword
   ```
5. Update registration data in `testdata.properties` if needed

---

## How to Run Tests

### Option 1: Run all via Maven
```bash
mvn test
```

### Option 2: Run via testng.xml in IntelliJ
- Right-click `testng.xml` → **Run**

### Option 3: Run individual test class
- Right-click any test class (e.g., `LoginTest.java`) → **Run**

---

## Test Scenarios

| # | Test Class          | What it tests                                   |
|---|---------------------|-------------------------------------------------|
| 1 | RegistrationTest    | New user registration with Calley Teams plan    |
| 2 | LoginTest           | Valid and invalid login scenarios               |
| 3 | AgentTest           | Login → Add new agent                           |
| 4 | CSVUploadTest       | Login → Power Import → Upload CSV → Map → Import|

---

## Configuration Files

### config.properties
```properties
browser=chrome          # chrome or firefox
headless=false          # true to run without opening browser window
implicit.wait=10        # seconds
explicit.wait=20        # seconds
```

### testdata.properties
Edit this file to change registration/agent/CSV test inputs without touching code.

---

## Key Design Patterns

- **Page Object Model (POM)**: Each page is a separate class under `pages/`
- **BasePage**: All pages extend `BasePage` which has shared Selenium helpers
- **BaseTest**: All test classes extend `BaseTest` for `@Before/@After` setup
- **Data-Driven**: All test inputs are in `.properties` files, not hardcoded
- **WebDriverManager**: Auto-handles ChromeDriver download and version matching

---

## Notes for Locator Adjustment

Since the live Calley application may differ from what's in this project, you may need to inspect element IDs using **Chrome DevTools (F12)** and update locators in the Page classes.

For example, in `RegistrationPage.java`:
```java
@FindBy(id = "txtName")  // ← Update if actual ID differs
private WebElement nameField;
```
