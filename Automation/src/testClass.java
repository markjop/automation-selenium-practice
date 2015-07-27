import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class testClass {
	private WebDriver driver;
	@Before
	public void setUp() throws Exception{
		/*switch for chrome browser
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
		*/
		
		//for IE
		System.setProperty("webdriver.ie.driver", "C://Selenium//IEDriverServer_Win32_2.44.0//IEDriverServer.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		//Should be changed later on, see http://jimevansmusic.blogspot.com/2012/08/youre-doing-it-wrong-protected-mode-and.html
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		driver = new InternetExplorerDriver(capabilities);
	}
	@Test
	public void mainTestClass() {
		//TC - SP001
		driver.get("http://www.google.com.ph");
		driver.findElement(By.id("gb_70")).click();
//		Assert.
//		Assert.assertEquals("Wikipedia", driver.getTitle());
		
		//TC - SP002
		driver.findElement(By.id("Email")).sendKeys("testEmail");
		driver.findElement(By.id("next")).click();
		
		//TC - SP003
		
	}
	
	@After
	public void tearDown() throws Exception{
//		driver.quit();
	}
}
