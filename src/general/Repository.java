package general;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Repository {
	
	public static String getLetsKodeIt() {
		return "https://learn.letskodeit.com";
	}
	
	public static String getDicta() {
		return "https://experintsol--cranky-banach-377068.netlify.com/result?text=%D7%93%D7%95%D7%93";
	}
	
	public static String getPracticeSite() {
		return "https://learn.letskodeit.com/p/practice";
	}
	
	public static WebDriver getChromeDriver() {
		String chromeProperty = "webdriver.chrome.driver";
		String chromePath = "C:\\Users\\USER\\Java_Workspace_Yehoshua\\Selenium Dependencies\\chromedriver.exe";

		System.setProperty(chromeProperty, chromePath);

		WebDriver cd = new ChromeDriver();
		return cd;
	}
	
	public static WebDriver getFireFoxDriver() {
		String firefoxProperty = "webdriver.gecko.driver";
		String firefoxPath = "C:\\Users\\USER\\Java_Workspace_Yehoshua\\Selenium Dependencies\\geckodriver.exe";
		System.setProperty(firefoxProperty, firefoxPath);
		
		WebDriver ffd = new FirefoxDriver();
		return ffd;
	}
	
	public static WebDriver getOperaDriver() {
		String operaProperty = "webdriver.opera.driver";
		String operaPath = "C:\\Users\\USER\\Java_Workspace_Yehoshua\\Selenium Dependencies\\operadriver.exe";
		System.setProperty(operaProperty, operaPath);
		
		WebDriver od = new OperaDriver();
		return od;
	}
	
	public static WebDriver getIEDriver() {
		String ieProperty = "webdriver.ie.driver";
		String iePath = "C:\\Users\\USER\\Java_Workspace_Yehoshua\\Selenium Dependencies\\IEDriverServer.exe";
		System.setProperty(ieProperty, iePath);

		WebDriver ied = new InternetExplorerDriver();
		return ied;
	}
	
	public static WebDriver getEdgeDriver() {
		String edgeProperty = "webdriver.edge.driver";
		String edgePath = "C:\\Users\\USER\\Java_Workspace_Yehoshua\\Selenium Dependencies\\MicrosoftWebDriver.exe";
		System.setProperty(edgeProperty, edgePath);

		WebDriver ed = new EdgeDriver();
		return ed;
	}
	
	public static WebDriver getSafariDriver() {
		String safariProperty = "webdriver.edge.driver";
		String safariPath = "C:\\Users\\USER\\Java_Workspace_Yehoshua\\Selenium Dependencies\\MicrosoftWebDriver.exe";
		System.setProperty(safariProperty, safariPath);

		WebDriver sd = new SafariDriver();
		return sd;
	}
	
	public static String getGoogle() {
		return "http://search.clearch.org";
	}

	public static void setup(WebDriver driver) {
		Dimension dim = new Dimension(0, 0);
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

}
