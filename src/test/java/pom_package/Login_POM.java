package pom_package;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base_package.BaseHRM;

public class Login_POM extends BaseHRM {
	@FindBy(name="username") WebElement username;
	@FindBy(name="password") WebElement password;
	@FindBy(css="#app > div.orangehrm-login-layout > div > div.orangehrm-login-container > div > div.orangehrm-login-slot > div.orangehrm-login-form > form > div.oxd-form-actions.orangehrm-login-action > button") 
	WebElement login_btn;
	
	//Initiate web elements of the page
	public Login_POM() {
		PageFactory.initElements(driver,this);
	}
	
	public void type_uname(String uname) {
		username.sendKeys(uname);
	}
	
	public void clear_uname() {
		username.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	}
	
	public void type_pwd(String pwd) {
		password.sendKeys(pwd);
	}
	
	public void clear_pwd() {
		password.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	}
	
	public void click() {
		login_btn.click();
	}
	
	public String get_title() {
		return driver.getTitle();
	}
	
	public String get_url() {
		return driver.getCurrentUrl();
	}
}
