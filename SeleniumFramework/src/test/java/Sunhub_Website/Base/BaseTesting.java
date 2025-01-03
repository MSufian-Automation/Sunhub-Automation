package Sunhub_Website.Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTesting {
	 protected WebDriver driver;

	    public void setup() {
	        String projectPath = System.getProperty("user.dir");
	        System.out.println("Project Path: " + projectPath);
	        System.setProperty("webdriver.gecko.driver", projectPath + "//drivers/geckodriver/geckodriver");
	        
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
	    }

	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}

