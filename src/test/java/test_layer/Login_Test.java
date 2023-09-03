package test_layer;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base_package.BaseHRM;
import pom_package.Login_POM;
import test_data.Excel_Data;

public class Login_Test extends BaseHRM {
	Login_POM log;
//	public Login_Test() {				//TestNG will instantiate the class automatically
//		super();
//	}
	
	@BeforeMethod
	public void initial_setup() {
		initialize();
		log = new Login_POM();
	}

	@Test
	public void verifyTitle() {
		String actual = log.get_title();
		System.out.println("Current Page Title: "+actual);
		Assert.assertEquals(actual, "OrangeHRM");
	}
	
	@DataProvider
	public Object[][] getdata(){
		return Excel_Data.read_data("Sheet1");
	}
	
	@Test(dataProvider="getdata")
	public void login(String uname, String pwd) throws InterruptedException {
		log.type_uname(uname);
		log.type_pwd(pwd);
		log.click();
		Thread.sleep(2000);
		tk_screenshot("Login");
		String actual = log.get_url();
		System.out.println("Current Page URL: "+actual);
		String expected = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
		Assert.assertEquals(actual, expected);
	}
	
	@AfterMethod
	public void close() {
		driver.close();
	}
}
