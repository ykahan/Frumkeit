package grabHespek;

import static org.junit.jupiter.api.Assertions.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import general.ExplicitlyWait;
import general.Repository;

import java.util.List;

class Bavli {
	static String dafCalendar;
	static WebDriver driver;
	static SimpleDateFormat dateFormat;
	static TimeZone timezone;
	static String month;
	static String currentDate;
	ExplicitlyWait ew;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Date date = new Date();
		currentDate = new SimpleDateFormat("MMM dd yyyy").format(date);
		System.out.println("Current Date: " + currentDate);

		dafCalendar = "http://www.dafyomi.org/machzor.php";
		driver = Repository.getChromeDriver();
		Repository.setup(driver);
		driver.get(dafCalendar);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	void test() throws InterruptedException {
		List<WebElement> cells = driver.findElements(By.xpath("//center/table//td"));
		boolean found = false;
		String masechta;
		String daf;
		for (int cell = 0; cell < cells.size(); cell++) {
			if (currentDate.contentEquals(cells.get(cell).getText())) {
				found = true;
				masechta = cells.get(cell + 1).getText();
				daf = cells.get(cell + 2).getText();
				System.out.println("Today's daf is " + masechta + " " + daf);
			}

		}
		if (!found) {
			System.out.println("not found");
		}

	}

}
