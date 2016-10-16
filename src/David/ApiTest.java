package David;


import utility.HelperUtility;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.http.client.ClientProtocolException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

public class ApiTest {

	private WebDriver driver;
	private String baseUrl;
	
	
	private String ApiId = "39b893cfb2a3eb0734da4f290d4750ae";
	/* try link http://openweathermap.org/appid to know more details*/
	
	public WebDriver getFirefoxDriver() {

		File profileDir = new File("firefoxprofile");
		FirefoxProfile fxProfile = new FirefoxProfile(profileDir);
		return new FirefoxDriver(fxProfile);
	}

	@Before
	public void setUp() throws Exception {
		//driver = getFirefoxDriver();
		String CHROME_DRIVER = "chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--test-type");
		driver = new ChromeDriver(options);
		baseUrl = "http://www.cic.gc.ca/";
		
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		// Maximize browser size
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
		driver.quit();
	}

	@Test
	public void test() throws ClientProtocolException, IOException, InterruptedException {
		driver.get(baseUrl);
		WebElement english = driver.findElement(By.xpath("/html/body/main/div/div[1]/div[2]/section[1]/p/a"));
		english.click();
		Thread.sleep(5000);
		
		WebElement searchInput = driver.findElement(By.id("wb-srch-q"));
		searchInput.clear();
		searchInput.sendKeys("sponsorship");
		
		WebElement searchButton = driver.findElement(By.id("wb-srch-sub"));
		searchButton.click();
		
		Thread.sleep(5000);
		WebElement firstResult = driver.findElement(By.xpath("//*[@id='contentPlaceHolderMainContent_panelMainContent']/ol/li[1]/strong/a"));
		System.out.println(firstResult.getText());
		System.out.println("test finished");
		
	}
}
