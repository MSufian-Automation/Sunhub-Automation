import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Complete_Registartion_login_Form {
    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);

        System.setProperty("webdriver.gecko.driver", projectPath + "//drivers/geckodriver/geckodriver");
        WebDriver driver = new ChromeDriver();

        try {
            // Generate a unique email and password
            String email = generateUniqueEmail();
            String password = generateUniquePassword();

            // Save the email and password to a text file
            saveCredentialsToFile(email, password);

            // Navigate to the registration page
            driver.get("https://qas.sunhub.com/register");

            // Wait for the form to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Select the 'Buyer' radio button
            WebElement buyerRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"role\"]/label[1]")));
            buyerRadio.click();

            // Fill in the Full Name field
            WebElement fullNameInput = driver.findElement(By.id("firstname"));
            fullNameInput.sendKeys("Automation Ghost HEHE");

            // Fill in the Email Address field
            WebElement emailInput = driver.findElement(By.id("email"));
            emailInput.sendKeys(email);

            // Fill in the Password field
            WebElement passwordInput = driver.findElement(By.id("password"));
            passwordInput.sendKeys(password);

            // Fill in the Confirm Password field
            WebElement confirmPasswordInput = driver.findElement(By.id("password_confirmation"));
            confirmPasswordInput.sendKeys(password);

            // Fill in the Phone field (including the +1 prefix)
            WebElement phoneInput = driver.findElement(By.id("phone"));
            phoneInput.sendKeys("1234567890");

            // Check the agreement checkbox
            WebElement agreementCheckbox = driver.findElement(By.id("agreement"));
            if (!agreementCheckbox.isSelected()) {
                agreementCheckbox.click();
            }

            // Submit the form
            WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
            submitButton.click();

            // Wait for the success message to appear
            WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".thankyou-message h4")));

            // Verify the success message content
            if (successMessage.getText().contains("Thanks For Choosing Us!")) {
                System.out.println("Form submitted successfully! Success message displayed: " + successMessage.getText());
            } else {
                System.out.println("Success message not found.");
            }

            // Now, let's open Mailinator to check the email
            String mailinatorInboxUrl = "https://www.mailinator.com/v4/public/inboxes.jsp?to=" + email.split("@")[0];
            driver.get(mailinatorInboxUrl);

            // Wait until the first email (with the text 'QAS Sunhub') is clickable
            WebElement qasSunhubEmail = wait.until(ExpectedConditions.elementToBeClickable(
            		By.xpath("//tr[td[contains(text(), 'QA Sunhub')]]//td[@onclick]")));

            System.out.println("Clicked on the 'QAS Sunhub' email.");
            qasSunhubEmail.click();

            // Wait for the "Verify Email Address" link to be clickable and click it
            WebElement verifyEmailLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pills-links-tab\"]")));
            verifyEmailLink.click();

            WebElement verifyEmailLink1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href, '/v1/client/email/verify/')]")));
            verifyEmailLink1.click();
            System.out.println("Account Created Successfully...");

         // Switch to the new tab
            Set<String> allTabs = driver.getWindowHandles();
            Iterator<String> iterator = allTabs.iterator();
            String mainTab = iterator.next();
            String newTab = iterator.next();
            driver.switchTo().window(newTab);

         // Verify the content of the new tab
            WebElement verifySuccessMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            		By.xpath("//*[@id=\"root\"]/div/div[2]/div/div/div[1]")));
            if (verifySuccessMessage.getText().contains("Your email is successfully verified. You can now login!")) {
                System.out.println("Email verification successful! Message: " + verifySuccessMessage.getText());
            } else {
                System.out.println("Email verification failed.");
                driver.close();
                driver.switchTo().window(mainTab);
                return;
            }
            // Now, let's navigate to the login page
            driver.get("https://qas.sunhub.com/login");

            // Retrieve the credentials from the text file
            String[] credentials = retrieveCredentialsFromFile();

            // Retrieve email and password from the file
            String savedEmail = credentials[0];
            String savedPassword = credentials[1];

            // Wait for the email field to be visible
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
            emailField.clear();
            emailField.sendKeys(savedEmail);

            // Wait for the password field to be visible
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            passwordField.clear();
            passwordField.sendKeys(savedPassword);

            // Click the login button
            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();
            
            WebElement dashboardMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            		By.cssSelector("#root > div > div.main > div.position-relative.row > div > div > div > div > h1")));
            if (dashboardMessage.getText().contains("Find the solar equipment you need to get the job done.")) {
                System.out.println("Login successful! Message: " + dashboardMessage.getText());
                driver.quit();
            
            } else {
                System.out.println("Login failed.");
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to generate a unique email address
    private static String generateUniqueEmail() {
        long timestamp = (short) System.currentTimeMillis();
        return "automation." + timestamp + "@mailinator.com";
    }

    // Method to generate a unique password
    private static String generateUniquePassword() {
        String passwordChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        Random random = new Random();
        StringBuilder password = new StringBuilder(12);

        for (int i = 0; i < 12; i++) {
            password.append(passwordChars.charAt(random.nextInt(passwordChars.length())));
        }

        return password.toString();
    }

    // Method to save credentials (email and password) to a text file
    private static void saveCredentialsToFile(String email, String password) {
        try {
            File file = new File("credentials.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
                writer.write("Email: " + email);
                writer.newLine();
                writer.write("Password: " + password);
                writer.newLine();
                writer.newLine();
            }

            System.out.println("Credentials saved to credentials.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve credentials from the text file
    private static String[] retrieveCredentialsFromFile() {
        String[] credentials = new String[2];
        try {
            BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"));
            String emailLine = reader.readLine();
            String passwordLine = reader.readLine();

            // Extract email and password from the lines
            credentials[0] = emailLine.split(":")[1].trim();
            credentials[1] = passwordLine.split(":")[1].trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }
}