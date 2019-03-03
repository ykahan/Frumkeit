package calculations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import general.Repository;

class daveningTimes {
	static WebDriver driver;
	static String timeis;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		timeis = "https://time.is/";
		driver = Repository.getChromeDriver();
		Repository.setup(driver);
		System.out.println("setup complete");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	void test() {
		driver.get(timeis);
		System.out.println("site acquired");
		String[] infoParts = getInfo();
		String sunrise = processInfo(infoParts[1]);
		String sunset = processInfo(infoParts[3]);
		String hoursInDay = processInfo(infoParts[4]);
		String minutesLeftOver = processInfo(infoParts[5]);
		String[] sunriseParts = sunrise.split(":");
		String[] sunsetParts = sunset.split(":");
		String alosHashachar = getAlos(sunriseParts);
		String tzesHakochavim = getTzes(sunsetParts);
		printBasicInfo(sunrise, sunset, alosHashachar, tzesHakochavim, hoursInDay, minutesLeftOver);
	}

	private String getTzes(String[] sunsetParts) {
		int hours = Integer.parseInt(sunsetParts[0]);
		int minutes = Integer.parseInt(sunsetParts[1]);
		hours += 1;
		minutes += 12;
		if(minutes >= 60) {
			minutes -= 60;
			hours += 1;
		}
		String hoursString = Integer.toString(hours);
		String minutesString = Integer.toString(minutes);
		if(minutesString.length() == 1) minutesString = "0" + minutesString;
		String output = hoursString + ":" + minutesString;
		return output;
	}

	private String getAlos(String[] sunriseParts) {
		int hours = Integer.parseInt(sunriseParts[0]);
		int minutes = Integer.parseInt(sunriseParts[1]);
		hours -= 1;
		minutes -= 12;
		if(minutes <= 0) {
			minutes += 60;
			hours -= 1;
		}
		String hoursString = Integer.toString(hours);
		String minutesString = Integer.toString(minutes);
		if(minutesString.length() == 1) minutesString = "0" + minutesString;
		String output = hoursString + ":" + minutesString;
		return output;
	}

	private void printBasicInfo(String sunrise, String sunset, String alos, String tzes, String hoursInDay, String minutesLeftOver) {
		System.out.println("Sunrise: " + sunrise + "\nSunset: " + sunset);
		System.out.println("Alos Hashachar: " + alos + "\nTzes Hakochavim: " + tzes);
		System.out.println("Hours: " + hoursInDay + "\nMinutes: " + minutesLeftOver);
	}

	private String processInfo(String string) {
		string = string.replaceAll("[^0-9:]", "");
		return string;
	}

	private String[] getInfo() {
		WebElement info = driver.findElement(By.id("sun"));
		String infoString = info.getText();
		String[] infoParts = infoString.split(" ");
		return infoParts;
	}

}
