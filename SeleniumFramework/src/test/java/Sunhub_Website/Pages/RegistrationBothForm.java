package Sunhub_Website.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegistrationBothForm {

	 WebDriver driver;

	    // Locators for the registration form elements
	    public By bothRadio = By.xpath("//*[@id=\"role\"]/label[3]");
	    By fullNameInput = By.id("firstname");
	    By companyNameInput = By.id("companyname");
	    By emailInput = By.id("email");
	    By passwordInput = By.id("password");
	    By confirmPasswordInput = By.id("password_confirmation");
	    By phoneInput = By.id("phone");
	    By agreementCheckbox = By.id("agreement");
	    By submitButton = By.xpath("//button[@type='submit']");

	    // Constructor
	    public RegistrationBothForm(WebDriver driver) {
	        this.driver = driver;
	    }

	    // Actions
	    public void selectBothRole() {
	        driver.findElement(bothRadio).click();
	    }

	    public void enterFullName(String fullName) {
	        driver.findElement(fullNameInput).sendKeys(fullName);
	    }

	    public void enterCompanyName(String companyName) {
	        driver.findElement(companyNameInput).sendKeys(companyName);
	    }

	    public void enterEmail(String email) {
	        driver.findElement(emailInput).sendKeys(email);
	    }

	    public void enterPassword(String password) {
	        driver.findElement(passwordInput).sendKeys(password);
	    }

	    public void confirmPassword(String password) {
	        driver.findElement(confirmPasswordInput).sendKeys(password);
	    }

	    public void enterPhone(String phone) {
	        driver.findElement(phoneInput).sendKeys(phone);
	    }

	    public void acceptAgreement() {
	        WebElement checkbox = driver.findElement(agreementCheckbox);
	        if (!checkbox.isSelected()) {
	            checkbox.click();
	        }
	    }

	    public void submitForm() {
	        driver.findElement(submitButton).click();
	    }
}
