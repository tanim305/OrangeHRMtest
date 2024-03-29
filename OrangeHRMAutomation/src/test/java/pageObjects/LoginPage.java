package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

/**
 * This class represents the Page Object Model (POM) for the login page.
 * It encapsulates the functionality and elements related to the login page.
 */
public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators updated to use CSS selectors with placeholders
    By usernameLocator = By.cssSelector("input[placeholder='Username']");
    By passwordLocator = By.cssSelector("input[placeholder='Password']"); // Assuming similar placeholder for password
    By loginButtonLocator = By.xpath("//button[normalize-space()='Login']"); // Keep or adjust based on actual attributes
    By errorMessageLocator = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text']"); // Assuming error message class remains the same

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Sets the username in the username input field.
     * @param username The username to set.
     */
    public void setUsername(String username) {
        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameLocator));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    /**
     * Sets the password in the password input field.
     * @param password The password to set.
     */
    public void setPassword(String password) {
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordLocator));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    /**
     * Clicks the login button.
     */
    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(loginButtonLocator));
        loginButton.click();
    }

    /**
     * Retrieves the error message displayed on the login page.
     * @return The error message text.
     */
    public String getErrorMessage() {
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessageLocator));
        return errorMessage.getText();
    }
    
}
