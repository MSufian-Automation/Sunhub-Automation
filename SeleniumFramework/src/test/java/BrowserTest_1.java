import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserTest_1 {

    public static void main(String[] args) {
        // Get the current project directory
        String projectPath = System.getProperty("user.dir");
        System.out.println("Project Path: " + projectPath);

        // Set the path for ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", projectPath + "/drivers/chromedriver/chromedriver");

        // Initialize the ChromeDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the desired URL
            driver.get("https://qas.sunhub.com/");
            System.out.println("Successfully opened the website!");

            // Add additional actions or validations here if required
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser after execution
            driver.quit();
        }
    }
}
