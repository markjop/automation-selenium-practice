package com.test.Automation_247;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class testSuite {
	
	private WebDriver driver;
	private final String GOOGLE = "http://www.google.com.ph";
	private DesiredCapabilities capabilities;
	
	//TODO: browseTo, findElement/s, clickElement/s, sendKeys, and others
		public void browseTo(String location){
			driver.get(location);
		}
		
		public void findElement(By by){
			findElement(by);
		}
		
		public void clickElement(){
			
		}
	
	@BeforeClass
	//TODO: nice to have different browser handling
	public void setUp()throws Exception{
		System.setProperty("webdriver.ie.driver", "C://Selenium//IEDriverServer_Win32_2.44.0//IEDriverServer.exe");
		
		capabilities = DesiredCapabilities.internetExplorer();
		//Should be changed later, see http://jimevansmusic.blogspot.com/2012/08/youre-doing-it-wrong-protected-mode-and.html
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	}

	@BeforeMethod
	public void openBrowser() {
		driver = new InternetExplorerDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@AfterMethod
	public void closeBrowserWindow(){
		//close the browser
		driver.close();
	}
	
	@AfterClass
	public void tearDown() throws Exception{
		//quit driver
		driver.quit();
	}
		
	@Test
	//TC - SP001 (Browser and webpage is successfully opened)
	public void testCaseSP001() {
		browseTo(GOOGLE);
		Assert.assertEquals(driver.getTitle(), 
				"Google");
	}
	
	@Test
	//TC - SP002 (Element UserID exists)
	public void testCaseSP002() {
		browseTo(GOOGLE);
		driver.findElement(By.id("gb_70")).click();
		Assert.assertEquals(driver.findElement(By.id("Email")).isDisplayed(),
				true);
	}
	
	@Test
	//TC - SP003 (Element Password exists)
	public void testCaseSP003() {
		browseTo(GOOGLE);
		driver.findElement(By.id("gb_70")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("Email")).sendKeys("testUserMarkjop");
		driver.findElement(By.id("next")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Assert.assertEquals(driver.findElement(By.id("Passwd")).isDisplayed(), 
				true);
	}
	
	@Test
	//TC - SP004 (Successful Login)
	public void testCaseSP004() throws InterruptedException {
		browseTo(GOOGLE);
		driver.findElement(By.id("gb_70")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("Email")).sendKeys("testUserMarkjop");
		driver.findElement(By.id("next")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("Passwd")).sendKeys("testPassMarkjop");
		//To always uncheck the signed in checkbox
			if (driver.findElement(By.id("PersistentCookie")).isSelected() ){
				driver.findElement(By.id("PersistentCookie")).click();
				}
		driver.findElement(By.id("signIn")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='gb_ha gb_s gb_5c gb_r']")).getText(),
				"testusermarkjop@gmail.com");
	}
	
	@Test
	//TC - SP005 (incorrect UserID and correct Password)
	public void testCaseSP005(){
		browseTo(GOOGLE);
		driver.findElement(By.id("gb_70")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		int randomInt = (int) (Math.random()*1000);
		driver.findElement(By.id("Email")).sendKeys("testIncorrectUser"+ randomInt);
		driver.findElement(By.id("next")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//TODO: Make the assertion generic (not specific to Google)
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-msg']")).getText(),
//		Assert.assertEquals(driver.findElement(By.id("errormsg_0_Email")).getText(),
				"Sorry, Google doesn't recognize that email.");
	}
	
	@Test
	//TC - SP006 (correct UserID and incorrect Password)
	public void testCaseSP006(){
		browseTo(GOOGLE);
		driver.findElement(By.id("gb_70")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("Email")).sendKeys("testUserMarkjop");
		driver.findElement(By.id("next")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("Passwd")).sendKeys("testIncorrectPassword");
		//To always uncheck the signed in checkbox
			if (driver.findElement(By.id("PersistentCookie")).isSelected() ){
				driver.findElement(By.id("PersistentCookie")).click();
				}
		driver.findElement(By.id("signIn")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//TODO: Make the assertion generic (not specific to Google)
//		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-msg']")).getText(),
		Assert.assertEquals(driver.findElement(By.id("errormsg_0_Passwd")).getText(),
				"The email and password you entered don't match.");
	}
	
	//TC - SP007 (incorrect UserID and incorrect Password)
	//can't use google as it only allows correct UserID to enable password input
}
