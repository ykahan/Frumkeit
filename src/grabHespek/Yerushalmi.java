package grabHespek;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import general.ExplicitlyWait;
import general.Repository;


class Yerushalmi {
	static WebDriver driver;
	static String currentDate;
	static ExplicitlyWait ew;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		driver = Repository.getChromeDriver();
		Repository.setup(driver);
		Date date = new Date();
		currentDate = new SimpleDateFormat("MMM d, yyyy").format(date);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	void test() {
		String hespek = "";
		driver.get("https://www.dafyomi.co.il/calendars/calendaryeru.htm");
		driver.switchTo().frame("calendar");
		List<WebElement> cells = driver.findElements(By.xpath("//div[@id='eng']/table//td"));
		for(int cell = 0; cell < cells.size(); cell++) {
			if(currentDate.contentEquals(cells.get(cell).getText())) {
				hespek = cells.get(cell+3).getText();
				System.out.println("Yerushalmi for the day is " + hespek);
				break;
			}
		}
	}

}
