package ApiTest;


import utility.HelperUtility;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

/**
 * @author John L
 * @version 1.0
 * @since 
 * 
 *        
 * @author Owner
 *
 */

public class ApiTest_ng {

	private WebDriver driver;
	private String baseUrl;
	
	
	private String ApiId = "39b893cfb2a3eb0734da4f290d4750ae";
	/* try link http://openweathermap.org/appid to know more details*/
	
	public WebDriver getFirefoxDriver() {

		File profileDir = new File("firefoxprofile");
		FirefoxProfile fxProfile = new FirefoxProfile(profileDir);
		return new FirefoxDriver(fxProfile);
	}

	 
	 @BeforeClass

	public void setUp() throws Exception {
		driver = getFirefoxDriver();
		baseUrl = "http://openweathermap.org/current";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}

	@Test
	public void test() throws ClientProtocolException, IOException {
		driver.get(baseUrl);
		driver.navigate().to("http://api.openweathermap.org/data/2.5/weather?q=London&APPID=" + ApiId);
		WebElement webElement = driver.findElement(By.tagName("pre"));
		ApiResponse ApiResponse = new ApiResponse();
		String ExpectedString = ApiResponse.GetResponse();
		assertTrue(webElement.getText().equals(ExpectedString));
	}
}