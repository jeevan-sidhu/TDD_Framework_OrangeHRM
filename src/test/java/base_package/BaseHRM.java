package base_package;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseHRM {
	public static WebDriver driver;
	public static Properties prop = new Properties();
	
	//Constructor to load configuration file
	public BaseHRM() {
		try {
			FileInputStream file = new FileInputStream("C:\\Users\\jvnsi\\eclipse-workspace\\HR_Management\\src\\test\\java\\Env_Variables\\config.properties");
			prop.load(file);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}		
	}
	//Method for the initial setup
	public static void initialize() {
		String Wb = prop.getProperty("browser");
		if (Wb.equalsIgnoreCase("Firefox")){
			System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if (Wb.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "chrome.exe");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(prop.getProperty("page_timeout"))));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicit_wait"))));
		driver.get(prop.getProperty("url"));
		
	}
	
	public static void tk_screenshot(String filename) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss-a");
        Date date = new Date();
		File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String loc="C:\\Users\\jvnsi\\eclipse-workspace\\HR_Management\\src\\test\\java\\screenshots\\Screenshots\\";
		try{
			FileUtils.copyFile(file, new File(loc+filename+"-"+dateFormat.format(date)+".jpg"));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
