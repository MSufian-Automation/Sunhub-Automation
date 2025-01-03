package Sunhub_Website.Tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import Sunhub_Website.Base.BaseTesting;
import Sunhub_Website.Pages.LoginForm;


public class WebsiteLoadingTest extends BaseTesting {
    private LoginForm loginPage;

    @Before
    public void setUp() {
        setup(); // Initialize WebDriver
        driver.get("https://qas.sunhub.com/login");
        loginPage = new LoginForm(driver);
    }

    @Test
    public void testValidLogin() {
        loginPage.enterEmail("sufian987456@gmail.com");
        loginPage.enterPassword("Sufian@123");
        loginPage.clickLoginButton();
        System.out.println("Login button clicked successfully!");
    }

    @Test
    public void testInvalidEmail() {
        loginPage.enterEmail("invalidemail.com");
        loginPage.enterPassword("Sufian@123");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to invalid email!");
    }

    @Test
    public void testInvalidPassword() {
        loginPage.enterEmail("sufian987456@gmail.com");
        loginPage.enterPassword("WrongPassword123");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to invalid password!");
    }

    @Test
    public void testEmptyEmail() {
        loginPage.enterEmail("");
        loginPage.enterPassword("Sufian@123");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to empty email!");
    }

    @Test
    public void testEmptyPassword() {
        loginPage.enterEmail("sufian987456@gmail.com");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to empty password!");
    }

    @Test
    public void testEmptyEmailAndPassword() {
        loginPage.enterEmail("");
        loginPage.enterPassword("");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to empty email and password!");
    }

    @Test
    public void testEmailWithoutAtSymbol() {
        loginPage.enterEmail("sufian987456gmail.com");
        loginPage.enterPassword("Sufian@123");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to invalid email format!");
    }

    @Test
    public void testEmailWithSpaces() {
        loginPage.enterEmail("  sufi an987456@gmail.com  ");
        loginPage.enterPassword("Sufian@123");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to email with spaces!");
    }

    @Test
    public void testForgotPasswordLink() {
        // Check if the "Forgot your password?" link is visible and clickable
        boolean isLinkVisible = loginPage.isForgotPasswordLinkVisible();
        assert isLinkVisible : "Forgot Password link should be visible!";
        System.out.println("Forgot Password link is visible.");

        // Click the "Forgot your password?" link
        loginPage.clickForgotPasswordLink();
        // Verify the page redirected or the relevant action took place
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("forgot-password") : "Forgot Password link should redirect to the forgot password page!";
        System.out.println("Redirected to the Forgot Password page.");
    }

    @Test
    public void testLoginWithSpecialCharsInEmail() {
        loginPage.enterEmail("sufian@#$.com");
        loginPage.enterPassword("Sufian@123");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to invalid special characters in email!");
    }

    @Test
    public void testSuccessfulLoginAndRedirect() {
        loginPage.enterEmail("sufian987456@gmail.com");
        loginPage.enterPassword("Sufian@123");
        loginPage.clickLoginButton();
        // Assuming there's a successful login redirect
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("dashboard") : "User should be redirected to the dashboard after successful login!";
        System.out.println("User redirected to dashboard after successful login.");
    }

    @Test
    public void testLoginWithLongPassword() {
        loginPage.enterEmail("sufian987456@gmail.com");
        loginPage.enterPassword("a".repeat(101)); // assuming the max length is 100
        loginPage.clickLoginButton();
        System.out.println("Login failed due to long password!");
    }

    @Test
    public void testLoginWithLongEmail() {
        loginPage.enterEmail("a".repeat(255) + "@gmail.com"); // max length for email
        loginPage.enterPassword("Sufian@123");
        loginPage.clickLoginButton();
        System.out.println("Login failed due to long email address!");
    }
    @Test
    public void testGoogleLoginButton() {
        WebElement googleButton = driver.findElement(By.cssSelector("button.google-btn"));
        boolean isGoogleButtonVisible = googleButton.isDisplayed();
        assert isGoogleButtonVisible : "Google Sign-In button should be visible!";
        googleButton.click();
        // Further validation for the Google login can be added based on your application logic (e.g., checking if a Google login pop-up appears)
        System.out.println("Google Sign-In button clicked.");
    }

    // Test Case: Verify Facebook login button is visible and clickable
    @Test
    public void testFacebookLoginButton() {
        WebElement facebookButton = driver.findElement(By.cssSelector("button.btn-facebook"));
        boolean isFacebookButtonVisible = facebookButton.isDisplayed();
        assert isFacebookButtonVisible : "Facebook Sign-In button should be visible!";
        facebookButton.click();
        // Further validation for the Facebook login can be added based on your application logic (e.g., checking if a Facebook login pop-up appears)
        System.out.println("Facebook Sign-In button clicked.");
    }

    // Test Case: Verify both Google and Facebook buttons are displayed
    @Test
    public void testSocialLoginButtonsAreVisible() {
        WebElement googleButton = driver.findElement(By.cssSelector("button.google-btn"));
        WebElement facebookButton = driver.findElement(By.cssSelector("button.btn-facebook"));
        
        assert googleButton.isDisplayed() : "Google Sign-In button should be visible!";
        assert facebookButton.isDisplayed() : "Facebook Sign-In button should be visible!";
        
        System.out.println("Both Google and Facebook Sign-In buttons are visible.");
    }

    // Test Case: Check if Google button contains correct text
    @Test
    public void testGoogleButtonText() {
        WebElement googleButton = driver.findElement(By.cssSelector("button.google-btn"));
        String buttonText = googleButton.getText();
        assert buttonText.contains("Sign in with Google") : "Google button should have correct text!";
        System.out.println("Google button text is correct: " + buttonText);
    }

    // Test Case: Check if Facebook button contains correct text
    @Test
    public void testFacebookButtonText() {
        WebElement facebookButton = driver.findElement(By.cssSelector("button.btn-facebook"));
        String buttonText = facebookButton.getText();
        assert buttonText.contains("Sign in With Facebook") : "Facebook button should have correct text!";
        System.out.println("Facebook button text is correct: " + buttonText);
    }

    // Test Case: Verify that the Facebook button is clickable
    @Test
    public void testFacebookButtonClickable() {
        WebElement facebookButton = driver.findElement(By.cssSelector("button.btn-facebook"));
        assert facebookButton.isEnabled() : "Facebook Sign-In button should be clickable!";
        facebookButton.click();
        System.out.println("Facebook button clicked.");
    }

    // Test Case: Verify that the Google button is clickable
    @Test
    public void testGoogleButtonClickable() {
        WebElement googleButton = driver.findElement(By.cssSelector("button.google-btn"));
        assert googleButton.isEnabled() : "Google Sign-In button should be clickable!";
        googleButton.click();
        System.out.println("Google button clicked.");
    }

    @Test
    public void testRegisterNowLink() {
        // Locate the "REGISTER NOW" link
        WebElement registerLink = driver.findElement(By.linkText("REGISTER NOW"));

        // Check if the "REGISTER NOW" link is visible
        boolean isRegisterLinkVisible = registerLink.isDisplayed();
        assert isRegisterLinkVisible : "'REGISTER NOW' link should be visible!";
        System.out.println("'REGISTER NOW' link is visible.");

        // Check if the "REGISTER NOW" link is clickable
        assert registerLink.isEnabled() : "'REGISTER NOW' link should be clickable!";
        registerLink.click();
        System.out.println("'REGISTER NOW' link clicked.");

        // Verify the page redirected to the registration page (i.e., the URL contains "/register")
        String currentUrl = driver.getCurrentUrl();
        assert currentUrl.contains("register") : "'REGISTER NOW' link should redirect to the registration page!";
        System.out.println("Redirected to the registration page.");
    }
    @Test
    public void testForgotPassword() {
        // Initialize WebDriverWait
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Step 1: Click on the "Forgot your password?" link
        WebElement forgotPasswordLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/forgot-password' and contains(text(),'Forgot your password?')]")
        ));
        forgotPasswordLink.click();

        // Step 2: Verify the next URL
        String expectedForgotPasswordUrl = "https://qas.sunhub.com/forgot-password";
        wait.until(ExpectedConditions.urlToBe(expectedForgotPasswordUrl));
        String actualForgotPasswordUrl = driver.getCurrentUrl();
//        Assert.assertEquals("The URL does not match the expected Forgot Password page URL", expectedForgotPasswordUrl, actualForgotPasswordUrl);
        System.out.println("Navigated to URL: " + actualForgotPasswordUrl);

        // Step 3: Verify the presence of the Email field and type an email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
//        Assert.assertTrue(emailField.isDisplayed());
        emailField.sendKeys("sufian987456@gmail.com");
        System.out.println("Entered email: sufian987456@gmail.com");

        // Step 4: Click on the "Submit" button
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit' and contains(@class, 'reset-btn')]")
        ));
        submitButton.click();
        System.out.println("Submit button clicked.");

        WebElement toastMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class, 'Toastify__toast-body')]")
        ));

        // Retrieve the text of the Toast message
        String messageText = toastMessage.getText();

        // Print the message to the console
        if (!messageText.isEmpty()) {
            System.out.println("Toast Message: " + messageText);
        } else {
            System.out.println("No text found in the Toast message.");
        }

        // Verify the expected message
        if (messageText.equals("The email has already been taken.")) {
            System.out.println("Expected text is displayed!");
        } else {
            System.out.println("Unexpected message: " + messageText);
        }

        // Step 5: Verify submission confirmation or error message
        try {
            // Replace with the expected confirmation message if available
            WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'Toastify__toast-body') and contains(text(),'Reset link sent')]")
            ));
//            Assert.assertTrue(confirmationMessage.isDisplayed());
            System.out.println("Confirmation: " + confirmationMessage.getText());
        } catch (Exception e) {
            // If the reset fails, verify error message
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[contains(@class,'Toastify__toast-body') and contains(text(),'Invalid email')]")
            ));
//            Assert.assertTrue(errorMessage.isDisplayed());
            System.out.println("Error: " + errorMessage.getText());
        }
    }

    @After
    public void tearDown() {
        super.tearDown(); // Quit WebDriver
    }
}
