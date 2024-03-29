package stepDefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pageObjects.LoginPage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.User;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * This class contains step definitions for login feature scenarios.
 */
public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private Map<String, User> users;

    @Before
    public void setUp() throws Exception {
        // Initialize the WebDriver and LoginPage objects
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);

        // Load users data from JSON
        Type userType = new TypeToken<Map<String, User>>(){}.getType();
        users = new Gson().fromJson(new FileReader("src/test/resources/loginData.json"), userType);
    }

    @Given("^I am on the login page$")
    public void iAmOnTheLoginPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @When("^I enter valid credentials$")
    public void iEnterValidCredentials() {
        // Retrieve user data for valid credentials
        User validUser = users.get("validUser");
        loginPage.setUsername(validUser.getUsername());
        loginPage.setPassword(validUser.getPassword());
        loginPage.clickLoginButton();
    }

    @Then("^I should be redirected to the dashboard page$")
    public void iShouldBeRedirectedToTheDashboardPage() {
        // Assert the current URL to verify if the login was successful
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"), "User is not redirected to dashboard page.");
    }

    @When("^I enter invalid credentials$")
    public void iEnterInvalidCredentials() {
        // Retrieve user data for invalid credentials
        User invalidUser = users.get("invalidUser");
        loginPage.setUsername(invalidUser.getUsername());
        loginPage.setPassword(invalidUser.getPassword());
        loginPage.clickLoginButton();
    }

    @Then("^I should see an error message$")
    public void iShouldSeeAnErrorMessage() {
        // Verify that an error message is displayed
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertFalse(errorMessage.isEmpty(), "Error message should be displayed.");
    }

    @After
    public void tearDown() {
        // Quit the WebDriver and close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}
