package grabHespek;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import general.Repository;

class MishnaBrura {

	static WebDriver driver;
	static String dafBehalacha;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		dafBehalacha = "https://www.dafhalacha.com/todays-limud/";
		driver = Repository.getChromeDriver();
		Repository.setup(driver);
		driver.get(dafBehalacha);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	void hespek() throws InterruptedException {
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='tip_text']"));
		WebElement halachaElement = elements.get(3);
		WebElement mussarElement = elements.get(4);
		String halachaText = halachaElement.getText();
		String mussarText = mussarElement.getText();
		String halachaMessage = "Today's halacha hespek:\n" + halachaText;
		String mussarMessage = "Today's mussar hespek:\n" + mussarText;
		System.out.println(halachaMessage + "\n");
		System.out.println(mussarMessage);
	}
	
	@Test
	void bechina() throws InterruptedException{
		driver.get("https://www.dafhalacha.com/upcoming-bechina/");
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class='tip_text']"));
		WebElement dateElement = elements.get(0);
		WebElement mussarElement = elements.get(1);
		WebElement halachaElement = elements.get(2);
		String dateText = dateElement.getText();
		String mussarText = mussarElement.getText();
		String halachaText = halachaElement.getText();
		StringBuilder sb = new StringBuilder();
		sb.append("\nThe next test is scheduled for " + dateText + "\n");
		sb.append("The halacha portion will be " + halachaText + "\n");
		sb.append("The mussar portion will be " + mussarText  +"\n");
		System.out.println(sb.toString());
	}

}
