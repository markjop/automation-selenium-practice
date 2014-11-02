import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class testClass {
	private WebDriver driver;
	@Before
	public void setUp() throws Exception{
		System.setProperty("webdriver.chrome.driver", "C://Selenium//chromedriver_win32//chromedriver.exe");
		// To remove message "You are using an unsupported command-line flag: --ignore-certificate-errors.
	    // Stability and security will suffer."
	    // Add an argument 'test-type'
	    DesiredCapabilities capabilities = DesiredCapabilities.chrome();
	    ChromeOptions options = new ChromeOptions();
	    options.addArguments("test-type");
	    capabilities.setCapability("chrome.binary","C://Program Files (x86)//Google//Chrome//Application//");
	    capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		driver = new ChromeDriver(capabilities);
	}
	
	@Test
	public void mainTestClass() {
		driver.get("http://www.google.com.ph");
		driver.findElement(By.name("q")).sendKeys("selenium");
		driver.findElement(By.name("btnG")).click();
		System.out.println("Test 1");
//		driver.get("http://www.wikipedia.org/");
//		Assert.assertEquals("Wikipedia", driver.getTitle());
	}
	
	/*@After
	public void tearDown() throws Exception{
		driver.quit();
	}*/

}
