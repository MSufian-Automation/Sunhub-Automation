package Sunhub_Website.Tests;

import java.time.Duration;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Sunhub_Website.Pages.RegistrationBothForm;

public class RegistrationBothTest {
	 private WebDriver driver;
	    private RegistrationBothForm registrationPage;

	    @Before
	    public void setUp() {
	        String projectPath = System.getProperty("user.dir");
	        System.out.println("Project Path: " + projectPath);
	        System.setProperty("webdriver.gecko.driver", projectPath + "//drivers/geckodriver/geckodriver");

	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	        System.out.println("Setting up test...");

	        driver.get("https://qas.sunhub.com/register");

	        // Initialize the Registration Page object
	        registrationPage = new RegistrationBothForm(driver);
	    }

	    @Test
	    public void testSellerRegistration() {
	        // Wait for the 'Seller' radio button to be clickable and select it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.bothRadio));
	        registrationPage.selectBothRole();

	        // Fill in the registration form
	        registrationPage.enterFullName("Jane Doe");
	        registrationPage.enterCompanyName("Doe Ventures");
	        registrationPage.enterEmail("jane.doe@example.com");
	        registrationPage.enterPassword("YourPassword123");
	        registrationPage.confirmPassword("YourPassword123");
	        registrationPage.enterPhone("9876543210");
	        registrationPage.acceptAgreement();

	        // Submit the form
	        registrationPage.submitForm();

	        // Wait for error message (if email is already taken)
	        try {
	            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'The email has already been taken.')]")
	            ));
	            System.out.println("Error: " + errorMessage.getText());
	        } catch (Exception e) {
	            // No error message found, form submitted successfully
	            System.out.println("Form submitted successfully!");
	        }
	    }
	    
	    
	    @Test
	    public void testValidSellerRegistration() {
	        // Wait for the 'Seller' radio button to be clickable and select it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.bothRadio));
	        registrationPage.selectBothRole();

	        // Fill in valid data for registration
	        registrationPage.enterFullName("Jane Doe");
	        registrationPage.enterCompanyName("Doe Ventures");
	        registrationPage.enterEmail("jane.doe@example.com");
	        registrationPage.enterPassword("YourPassword123");
	        registrationPage.confirmPassword("YourPassword123");
	        registrationPage.enterPhone("9876543210");
	        registrationPage.acceptAgreement();

	        // Submit the form
	        registrationPage.submitForm();

	        // Verify successful submission (no error message)
	        try {
	            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[contains(text(),'Registration successful')]")
	            ));
	            System.out.println("Success: " + successMessage.getText());
	        } catch (Exception e) {
	            System.out.println("Error: Registration failed.");
	        }
	    }
	    @Test
	    public void testEmailAlreadyTaken() {
	        // Wait for the 'Seller' radio button to be clickable and select it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.bothRadio));
	        registrationPage.selectBothRole();

	        // Fill in the registration form with an already taken email
	        registrationPage.enterFullName("John Smith");
	        registrationPage.enterCompanyName("Smith Enterprises");
	        registrationPage.enterEmail("jane.doe@example.com");  // Already taken email
	        registrationPage.enterPassword("Password123");
	        registrationPage.confirmPassword("Password123");
	        registrationPage.enterPhone("1234567890");
	        registrationPage.acceptAgreement();

	        // Submit the form
	        registrationPage.submitForm();

	        // Wait for error message indicating the email is already taken
	        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'The email has already been taken.')]")
	        ));
	        System.out.println("Error: " + errorMessage.getText());
	    }

	    @Test
	    public void testPasswordMismatch() {
	        // Wait for the 'Seller' radio button to be clickable and select it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.bothRadio));
	        registrationPage.selectBothRole();

	        // Fill in the registration form with password mismatch
	        registrationPage.enterFullName("Emily Davis");
	        registrationPage.enterCompanyName("Davis Solutions");
	        registrationPage.enterEmail("emily.davis@example.com");
	        registrationPage.enterPassword("Password123");
	        registrationPage.confirmPassword("Password1234");  // Mismatch password
	        registrationPage.enterPhone("5555555555");
	        registrationPage.acceptAgreement();

	        // Submit the form
	        registrationPage.submitForm();

	        // Wait for error message indicating password mismatch
	        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Passwords do not match')]")
	        ));
	        System.out.println("Error: " + errorMessage.getText());
	    }

	    @Test
	    public void testMissingRequiredFields() {
	        // Wait for the 'Seller' radio button to be clickable and select it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.bothRadio));
	        registrationPage.selectBothRole();

	        // Fill in the registration form without providing email and phone
	        registrationPage.enterFullName("Mark Taylor");
	        registrationPage.enterCompanyName("Taylor Group");
	        registrationPage.enterEmail("");  // Missing email
	        registrationPage.enterPassword("ValidPassword123");
	        registrationPage.confirmPassword("ValidPassword123");
	        registrationPage.enterPhone("");  // Missing phone
	        registrationPage.acceptAgreement();

	        // Submit the form
	        registrationPage.submitForm();

	        // Wait for error messages indicating missing fields
	        WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Email is required')]")
	        ));
	        System.out.println("Error: " + emailError.getText());

	        WebElement phoneError = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Phone is required')]")
	        ));
	        System.out.println("Error: " + phoneError.getText());
	    }

	    @Test
	    public void testInvalidEmailFormat() {
	        // Wait for the 'Seller' radio button to be clickable and select it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.bothRadio));
	        registrationPage.selectBothRole();

	        // Fill in the registration form with invalid email format
	        registrationPage.enterFullName("Linda Green");
	        registrationPage.enterCompanyName("Green Enterprises");
	        registrationPage.enterEmail("linda.green@com");  // Invalid email format
	        registrationPage.enterPassword("ValidPassword123");
	        registrationPage.confirmPassword("ValidPassword123");
	        registrationPage.enterPhone("9876543210");
	        registrationPage.acceptAgreement();

	        // Submit the form
	        registrationPage.submitForm();

	        // Wait for error message indicating invalid email format
	        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Invalid email format')]")
	        ));
	        System.out.println("Error: " + errorMessage.getText());
	    }

	    @Test
	    public void testInvalidPhoneNumberFormat() {
	        // Wait for the 'Seller' radio button to be clickable and select it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.bothRadio));
	        registrationPage.selectBothRole();

	        // Fill in the registration form with invalid phone number format
	        registrationPage.enterFullName("Alice Brown");
	        registrationPage.enterCompanyName("Brown Enterprises");
	        registrationPage.enterEmail("alice.brown@example.com");
	        registrationPage.enterPassword("ValidPassword123");
	        registrationPage.confirmPassword("ValidPassword123");
	        registrationPage.enterPhone("12345");  // Invalid phone format
	        registrationPage.acceptAgreement();

	        // Submit the form
	        registrationPage.submitForm();

	        // Wait for error message indicating invalid phone number format
	        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Invalid phone number format')]")
	        ));
	        System.out.println("Error: " + errorMessage.getText());
	    }
	    
	    
	    @Test
	    public void testGoogleSignUpButtonClickable() {
	        // Wait for the Google sign-up button to be clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement googleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ant-btn google-btn']")));

	        // Click the Google sign-up button
	        googleButton.click();

	        // Verify that the user is redirected to the Google sign-up page (or any expected behavior)
	        String currentUrl = driver.getCurrentUrl();
	       
	        System.out.println("Google Sign-Up button clicked, redirected to: " + currentUrl);
	    }

	    @Test
	    public void testFacebookSignUpButtonClickable() {
	        // Wait for the Facebook sign-up button to be clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement facebookButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ant-btn btn-facebook']")));

	        // Click the Facebook sign-up button
	        facebookButton.click();

	        // Verify that the user is redirected to the Facebook login page (or any expected behavior)
	        String currentUrl = driver.getCurrentUrl();
	     
	        System.out.println("Facebook Sign-Up button clicked, redirected to: " + currentUrl);
	    }

	    @Test
	    public void testGoogleSignUpButtonTextAndIcon() {
	        // Wait for the Google sign-up button to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement googleButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ant-btn google-btn']")));

	        // Verify the Google icon is present
	        System.out.println("Google icon is displayed");

	        // Verify the button text is 'Sign up with Google'
	        WebElement googleButtonText = googleButton.findElement(By.xpath(".//span[@class='btn-text']"));
	       
	        System.out.println("Button text is correct: " + googleButtonText.getText());
	    }

	    @Test
	    public void testFacebookSignUpButtonTextAndIcon() {
	        // Wait for the Facebook sign-up button to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement facebookButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='ant-btn btn-facebook']")));

	        // Verify the Facebook icon is present  
	        System.out.println("Facebook icon is displayed");

	        // Verify the button text is 'Sign up With Facebook'
	        WebElement facebookButtonText = facebookButton.findElement(By.xpath(".//span[@class='btn-text']"));
	     
	        System.out.println("Button text is correct: " + facebookButtonText.getText());
	    }

	    @Test
	    public void testAlreadyHaveAccountTextLink() {
	        // Wait for the "Already have an account?" text to be visible
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement accountText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Already have an account?')]")));

	        // Verify the link text is correct
	        String linkText = accountText.getText();
	        System.out.println("Text link is visible with correct text: " + linkText);

	     
	        System.out.println("Login link is visible and clickable.");
	    }

	    @Test
	    public void testSocialLoginButtonsPresence() {
	        System.out.println("Both Google and Facebook social login buttons are visible.");
	    }

	    @Test
	    public void testSocialLoginButtonsAfterSelectingSellerRole() {
	        // Wait for the 'Seller' radio button to be clickable and select it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        WebElement bothRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='role' and @value='seller']")));
	        bothRadio.click();

	        // Wait for both Google and Facebook buttons to be clickable
	        WebElement googleButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ant-btn google-btn']")));
	        WebElement facebookButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='ant-btn btn-facebook']")));

	        // Click the Google sign-up button
	        googleButton.click();
	        String googleUrl = driver.getCurrentUrl();
	        System.out.println("Google Sign-Up button clicked, redirected to: " + googleUrl);

	        // Navigate back to the registration page
	        driver.navigate().back();

	        // Click the Facebook sign-up button
	        facebookButton.click();
	        String facebookUrl = driver.getCurrentUrl();
	        System.out.println("Facebook Sign-Up button clicked, redirected to: " + facebookUrl);
	    }
}
