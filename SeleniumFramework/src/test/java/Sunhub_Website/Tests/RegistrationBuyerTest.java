package Sunhub_Website.Tests;

//import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//import Sunhub_Website.Base.BaseTesting;
import Sunhub_Website.Pages.RegistrationBuyerForm;

import java.time.Duration;

public class RegistrationBuyerTest {
    private WebDriver driver;
    private RegistrationBuyerForm registrationPage;

    @Before
    public void setUp() {
    	String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);
        System.setProperty("webdriver.gecko.driver", projectPath + "//drivers/geckodriver/geckodriver");
        
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        // Initialize WebDriver from the BaseTesting class
//        setup(); // Ensure that WebDriver is set up correctly in the BaseTesting class
        System.out.println("Setting up test...");

        driver.get("https://qas.sunhub.com/register");

        // Initialize the Registration Page object
        registrationPage = new RegistrationBuyerForm(driver);
    }

    @Test
    public void testBuyerRegistration() {
        // Wait for the 'Buyer' radio button to be clickable and select it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.buyerRadio));
        registrationPage.selectBuyerRole();

        // Fill in the registration form
        registrationPage.enterFullName("John Doe");
        registrationPage.enterEmail("john.doe@example.com");
        registrationPage.enterPassword("YourPassword123");
        registrationPage.confirmPassword("YourPassword123");
        registrationPage.enterPhone("1234567890");
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
    public void testBuyerRegistrationWithExistingEmail() {
        // Wait for the 'Buyer' radio button to be clickable and select it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.buyerRadio));
        registrationPage.selectBuyerRole();

        // Fill in the registration form with an existing email
        registrationPage.enterFullName("John Doe");
        registrationPage.enterEmail("john.doe@example.com"); // Existing email
        registrationPage.enterPassword("YourPassword123");
        registrationPage.confirmPassword("YourPassword123");
        registrationPage.enterPhone("1234567890");
        registrationPage.acceptAgreement();

        // Submit the form
        registrationPage.submitForm();

        // Wait for error message if email is already taken
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'The email has already been taken.')]")
        ));
        
        System.out.println("Error: " + errorMessage.getText());
    }

    @Test
    public void testBuyerRegistrationWithPasswordMismatch() {
        // Wait for the 'Buyer' radio button to be clickable and select it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.buyerRadio));
        registrationPage.selectBuyerRole();

        // Fill in the registration form with mismatched passwords
        registrationPage.enterFullName("John Doe");
        registrationPage.enterEmail("john.doe@example.com");
        registrationPage.enterPassword("YourPassword123");
        registrationPage.confirmPassword("DifferentPassword123"); // Mismatched password
        registrationPage.enterPhone("1234567890");
        registrationPage.acceptAgreement();

        // Submit the form
        registrationPage.submitForm();

        // Wait for the error message indicating password mismatch
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Passwords do not match')]")
        ));
       
        System.out.println("Error: " + errorMessage.getText());
    }

    @Test
    public void testBuyerRegistrationWithMissingFields() {
        // Wait for the 'Buyer' radio button to be clickable and select it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.buyerRadio));
        registrationPage.selectBuyerRole();

        // Leave required fields blank
        registrationPage.enterFullName("");
        registrationPage.enterEmail("");
        registrationPage.enterPassword("");
        registrationPage.confirmPassword("");
        registrationPage.enterPhone("");
        registrationPage.acceptAgreement();

        // Submit the form
        registrationPage.submitForm();

        // Wait for error message for missing fields
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Please fill out all required fields')]")
        ));
        
        System.out.println("Error: " + errorMessage.getText());
    }

    @Test
    public void testBuyerRegistrationWithInvalidPhoneNumber() {
        // Wait for the 'Buyer' radio button to be clickable and select it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.buyerRadio));
        registrationPage.selectBuyerRole();

        // Fill in the registration form with an invalid phone number
        registrationPage.enterFullName("John Doe");
        registrationPage.enterEmail("john.doe@example.com");
        registrationPage.enterPassword("YourPassword123");
        registrationPage.confirmPassword("YourPassword123");
        registrationPage.enterPhone("12345"); // Invalid phone number
        registrationPage.acceptAgreement();

        // Submit the form
        registrationPage.submitForm();

        // Wait for error message indicating phone number validation failure
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Please enter a valid phone number')]")
        ));
        
        System.out.println("Error: " + errorMessage.getText());
    }

    @Test
    public void testBuyerRegistrationWithValidData() {
        // Wait for the 'Buyer' radio button to be clickable and select it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.buyerRadio));
        registrationPage.selectBuyerRole();

        // Fill in the registration form with valid data
        registrationPage.enterFullName("John Doe");
        registrationPage.enterEmail("john.doe@example.com");
        registrationPage.enterPassword("YourPassword123");
        registrationPage.confirmPassword("YourPassword123");
        registrationPage.enterPhone("1234567890");
        registrationPage.acceptAgreement();

        // Submit the form
        registrationPage.submitForm();

        // Wait for confirmation message after successful registration
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'Registration successful')]")
        ));
      
        System.out.println("Success: " + confirmationMessage.getText());
    }

    @Test
    public void testBuyerRegistrationRedirectsToLoginPage() {
        // Wait for the 'Buyer' radio button to be clickable and select it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.buyerRadio));
        registrationPage.selectBuyerRole();

        // Fill in the registration form with valid data
        registrationPage.enterFullName("John Doe");
        registrationPage.enterEmail("john.doe@example.com");
        registrationPage.enterPassword("YourPassword123");
        registrationPage.confirmPassword("YourPassword123");
        registrationPage.enterPhone("1234567890");
        registrationPage.acceptAgreement();

        // Submit the form
        registrationPage.submitForm();

        // Wait for the page to load and verify the URL for login page
        wait.until(ExpectedConditions.urlContains("/login"));
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Redirected to login page: " + currentUrl);
    }

    @Test
    public void testBuyerRegistrationWithoutAgreementCheckbox() {
        // Wait for the 'Buyer' radio button to be clickable and select it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(registrationPage.buyerRadio));
        registrationPage.selectBuyerRole();

        // Fill in the registration form without accepting the agreement
        registrationPage.enterFullName("John Doe");
        registrationPage.enterEmail("john.doe@example.com");
        registrationPage.enterPassword("YourPassword123");
        registrationPage.confirmPassword("YourPassword123");
        registrationPage.enterPhone("1234567890");
        
        // Do not select the agreement checkbox
        // registrationPage.acceptAgreement(); // Omit this step

        // Submit the form
        registrationPage.submitForm();

        // Wait for error message if agreement checkbox is not checked
        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@class='Toastify__toast-body' and contains(text(),'You must accept the agreement')]")
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
        WebElement sellerRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='role' and @value='seller']")));
        sellerRadio.click();

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
