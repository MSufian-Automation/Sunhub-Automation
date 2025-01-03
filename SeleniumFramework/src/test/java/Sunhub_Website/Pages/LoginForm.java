package Sunhub_Website.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginForm {
	 private WebDriver driver;
	    private WebDriverWait wait;
	    private By forgotPasswordLink = By.linkText("Forgot your password?");
	    
	 // Locator for the Google and Facebook buttons
	    private By googleButton = By.cssSelector("button.google-btn");
	    private By facebookButton = By.cssSelector("button.btn-facebook");
	    // Locators
	    private By emailField = By.id("email");
	    private By passwordField = By.id("password");
	    private By loginButton = By.cssSelector("button[type='submit']");

	    // Constructor
	    public LoginForm(WebDriver driver) {
	        this.setDriver(driver);
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50));
	    }

	    // Methods
	    public void enterEmail(String email) {
	        WebElement emailElement = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
	        emailElement.sendKeys(email);
	    }

	    public void enterPassword(String password) {
	        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
	        passwordElement.sendKeys(password);
	    }

	    public void clickLoginButton() {
	        WebElement loginElement = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	        loginElement.click();
	    }

		public WebDriver getDriver() {
			return driver;
		}

		public void setDriver(WebDriver driver) {
			this.driver = driver;
		}
		 // Method to check if the "Forgot Password" link is visible
	    public boolean isForgotPasswordLinkVisible() {
	        try {
	            WebElement link = driver.findElement(forgotPasswordLink);
	            return link.isDisplayed();
	        } catch (Exception e) {
	            return false;
	        }
	    }
	 // Method to get the Google button
	    public WebElement getGoogleButton() {
	        return driver.findElement(googleButton);
	    }

	    // Method to get the Facebook button
	    public WebElement getFacebookButton() {
	        return driver.findElement(facebookButton);
	    }

	    // Method to click the "Forgot Password" link
	    public void clickForgotPasswordLink() {
	        WebElement link = driver.findElement(forgotPasswordLink);
	        link.click();
	    }
	}
