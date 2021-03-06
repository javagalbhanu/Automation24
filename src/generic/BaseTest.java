package generic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public abstract class BaseTest implements IAutoConst {
	public WebDriver driver;
	static{
		System.setProperty(CHROME_KEY,CHROME_VALUE);
		System.setProperty(GECKO_KEY,GECKO_VALUE);
	}
	
	@Parameters({"node","browser"})
	@BeforeMethod
	public void openApplication(String node,String browser)
	throws MalformedURLException{
		URL whichSystem=new URL(node);
		DesiredCapabilities whichBrowser=new DesiredCapabilities();
		whichBrowser.setBrowserName(browser);
		driver=new RemoteWebDriver(whichSystem,whichBrowser);
		String url=Lib.getProperty(CONFIG_PATH,"URL");
		driver.get(url);
		String IOT=Lib.getProperty(CONFIG_PATH,"IOT");
		long lIOT=Long.parseLong(IOT);
		driver.manage().timeouts().implicitlyWait(lIOT,TimeUnit.SECONDS);    
	}
	
	
	
	@AfterMethod
	public void closeApplication(){
		driver.quit();
	}
}


