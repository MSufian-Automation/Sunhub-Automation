

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;



public class Website_loading {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);
        
		System.setProperty("webdriver.gecko.driver", projectPath+"//drivers/geckodriver/geckodriver");
		WebDriver driver =  new ChromeDriver();
		
		try{
		driver.get("https://qas.sunhub.com/login");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

		// Wait for and enter the email
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        emailField.sendKeys("sufian987456@gmail.com");

        // Wait for and enter the password
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        passwordField.sendKeys("Sufian@123");
		// Wait for and click the login button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        loginButton.click();
		// Optional: Verify successful login or other actions after login
        System.out.println("Login button clicked successfully!");

    } catch (Exception e) {
        System.out.println("An error occurred: " + e.getMessage());
    } finally {
        // Close the browser
        driver.quit();
    }
////		// Maximize the browser window
////        driver.manage().window().maximize();
//
//        // Create a WebDriverWait object
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
//
//		
//		 WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
//                 By.xpath("//*[@id='root']/div/div[2]/div[1]/div/nav/div/ul[1]/li[2]/div")
//         ));
//
//         // Click on the button
//         button.click();
//         System.out.println("Button clicked successfully!");
//         
//         WebDriverWait wait_1 = new WebDriverWait(driver, Duration.ofSeconds(10));
//         
//         // Find the element by its CSS selector
//         WebElement signInButton = driver.findElement(By.cssSelector("a.dropdown-item[href='/login']"));
//
//         // Click on the element
//         signInButton.click();
//
//         // Optional: Verify the resulting page or action
//         System.out.println("Element clicked successfully!");
//         
//         // Optionally, validate redirection
//         System.out.println("Redirected to the login page.");
//
	}

}