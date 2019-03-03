package general;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import general.ErrorTracker;

public class ExplicitlyWait {
	WebDriver driver;
	ErrorTracker et;

	public ExplicitlyWait(WebDriver driver, ErrorTracker errorTracker) {
		this.driver = driver;
		this.et = errorTracker;
	}

	public void explicitlyWait(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement sendKeys(By locator, int timeout, String message) {
		return sendKeys(this.driver, locator, timeout, message);
	}

	public WebElement sendKeys(By locator, String message) {
		return sendKeys(locator, 3, message);
	}

	public WebElement sendKeys(WebDriver driver, By locator, String message) {
		return sendKeys(driver, locator, 3, message);
	}

	public WebElement sendKeys(WebDriver driver, By locator, int timeout, String message) {
		WebElement we = null;

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			we = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
			we.sendKeys(message);
		} catch (Exception e) {
			String error = e.getMessage();
			System.out.println(error);
			noteError();
		}
		return we;
	}

	private void noteError() {
		et.incrementErrorsLocal();
	}

	public WebElement awaitElement(By locator, int timeout) {
		return awaitElement(driver, locator, timeout);
	}

	public WebElement awaitElement(WebDriver driver, By locator) {
		return awaitElement(driver, locator, 3);
	}

	public WebElement awaitElement(By locator) {
		return awaitElement(driver, locator, 3);
	}

	public WebElement awaitElement(String xpath) {
		System.out.println("awaitElement activated");
		By locator = By.xpath(xpath);
		return awaitElement(locator);
	}

	public WebElement awaitElement(WebDriver driver, By locator, int timeout) {
		WebElement we = null;
		try {
//			System.out.println("Waiting up to " + timeout + " seconds for element.");
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			we = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//			System.out.println("Element found");
		} catch (Exception e) {
			System.out.println("Element not found");
			System.out.println(e.getMessage());
			noteError();
		}
		return we;
	}

	public WebElement clickElementWhenReady(WebDriver driver, By locator, int timeout) {
		WebElement we = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			we = (WebElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
			we.click();
		} catch (Exception e) {
			System.out.println("EW message: " + e.getMessage());
			noteError();
		}
		return we;
	}

	public WebElement clickElementWhenReady(By locator, int timeout) {
		return clickElementWhenReady(driver, locator, timeout);
	}

	public WebElement clickElementWhenReady(WebDriver driver, By locator) {
		return clickElementWhenReady(driver, locator, 3);
	}

	public WebElement clickElementWhenReady(By locator) {
		return clickElementWhenReady(driver, locator, 3);
	}

	public WebElement clickElementWhenReady(String xpath) {
		By locator = By.xpath(xpath);
		return clickElementWhenReady(locator);
	}

	public List<WebElement> awaitElements(By locator, int timeout) {
		return awaitElements(this.driver, locator, timeout);
	}

	public List<WebElement> awaitElements(String xpath) {
		try {
			System.out.println("awaitElements activated");
			By locator = By.xpath(xpath);
			return awaitElements(locator);
		} catch (Exception e) {
			System.out.println("error occurred");
			return null;
		}
	}

	public List<WebElement> awaitElements(By locator) {
		return awaitElements(locator, 3);
	}

	public List<WebElement> awaitElements(WebDriver driver, By locator, int timeout) {
		List<WebElement> wel = null;
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			wel = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
			System.out.println("List of length " + wel.size() + " acquired.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			noteError();
		}
		return wel;
	}

	public List<WebElement> awaitElements(WebDriver driver, By locator) {
		return awaitElements(driver, locator, 3);
	}

	public static void clickElementRepeatedlyWithXpath(ExplicitlyWait ew, int reps, String xpath, int waitTime)
			throws InterruptedException {
		for (int i = 0; i < reps; i++) {
			ew.clickElementWhenReady(By.xpath(xpath));
			Thread.sleep(waitTime);
		}
	}

	public void clickElementRepeatedlyWithXpath(int reps, String xpath, int waitTime) throws InterruptedException {
		clickElementRepeatedlyWithXpath(this, reps, xpath, waitTime);
	}

	public static List<WebElement> clickElementsWhenReady(int waitTime, ExplicitlyWait ew, String xpath)
			throws InterruptedException {
		By locator = By.xpath(xpath);
		return clickElementsWhenReady(waitTime, ew, locator);
	}

	public static List<WebElement> clickElementsWhenReady(int waitTime, ExplicitlyWait ew, By locator)
			throws InterruptedException {
		List<WebElement> list = ew.awaitElements(locator);
		for (int index = 0; index < list.size(); index++) {
			list.get(index).click();
			Thread.sleep(waitTime);
		}
		return list;
	}

	public List<WebElement> clickElementsWhenReady(int waitTime, String xpath) throws InterruptedException {
		return clickElementsWhenReady(waitTime, this, xpath);
	}

	public List<WebElement> clickElementsWhenReady(String xpath) throws InterruptedException {
		By locator = By.xpath(xpath);
		return clickElementsWhenReady(locator);
	}

	private List<WebElement> clickElementsWhenReady(By locator) throws InterruptedException {
		return clickElementsWhenReady(this, locator);
	}

	private List<WebElement> clickElementsWhenReady(ExplicitlyWait ew, By locator) throws InterruptedException {
		return clickElementsWhenReady(3, ew, locator);

	}

	public WebElement sendKeys(String xpath, String message) {
		By locator = By.xpath(xpath);
		return sendKeys(locator, message);
	}

	public WebElement clearTextFromElementWhenReady(WebDriver driver, int waitTime, ExplicitlyWait ew, By locator)
			throws InterruptedException {
		WebElement element = awaitElement(driver, locator, waitTime);
		element.clear();
		return element;
	}

	public WebElement clearTextFromElementWhenReady(By locator) throws InterruptedException {
		return clearTextFromElementWhenReady(driver, 3, this, locator);
	}

	public WebElement clearTextFromElementWhenReady(String xpath) throws InterruptedException {
		By locator = By.xpath(xpath);
		return clearTextFromElementWhenReady(locator);
	}

	public WebElement awaitElement(WebElement element, int timeout) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, timeout);
			element = wait.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			noteError();
			element = null;
		}
		return element;
	}

	public void clickElementWhenReady(WebElement webElement) {
		// TODO Auto-generated method stub

	}

}
