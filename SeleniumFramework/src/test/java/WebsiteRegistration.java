
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WebsiteRegistration {
    public static void main(String[] args) {
    	String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);
        
		System.setProperty("webdriver.gecko.driver", projectPath+"//drivers/geckodriver/geckodriver");
		WebDriver driver =  new ChromeDriver();

		 try {
	            // Navigate to the registration page
	            driver.get("https://qas.sunhub.com/register");

	            // Wait for the form to load
	            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	            // Select the 'Buyer' radio button
	            WebElement buyerRadio = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"role\"]/label[1]")));
	            buyerRadio.click();

	            // Fill in the Full Name field
	            WebElement fullNameInput = driver.findElement(By.id("firstname"));
	            fullNameInput.sendKeys("John Doe");

	            // Fill in the Email Address field
	            WebElement emailInput = driver.findElement(By.id("email"));
	            emailInput.sendKeys("john.doe@example.com");

	            // Fill in the Password field
	            WebElement passwordInput = driver.findElement(By.id("password"));
	            passwordInput.sendKeys("YourPassword123");

	            // Fill in the Confirm Password field
	            WebElement confirmPasswordInput = driver.findElement(By.id("password_confirmation"));
	            confirmPasswordInput.sendKeys("YourPassword123");

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

	            // Optionally, verify form submission by checking the presence of a success message or redirection
	            wait.until(ExpectedConditions.urlContains("success_page"));

	            System.out.println("Form submitted successfully!");

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the driver
	            driver.quit();
	        }
	    }
	}