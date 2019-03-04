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
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		driver.quit();
	}

	@Test
	void test() {
		driver.get(timeis);
		String[] infoParts = getInfo();
		driver.quit();
		String sunrise = processInfo(infoParts[1]);
		String sunset = processInfo(infoParts[3]);
		int minutesInDayShort = calculateMinInRange(sunrise, sunset);
		String[] sunriseParts = sunrise.split(":");
		String[] sunsetParts = sunset.split(":");
		String alosHashachar = getAlos(sunriseParts);
		String tzesHakochavim = getTzes(sunsetParts);
		int minutesInDayLong = calculateMinInRange(alosHashachar, tzesHakochavim);
		String[] timesShort = calculateTimes(minutesInDayShort, sunrise);
		String[] timesLong = calculateTimes(minutesInDayLong, alosHashachar);
		System.out.println("Based on sunrise (" + sunrise + ") and sunset (" + sunset +"):\n");
		printTimes(timesShort);
		for(int i = 0; i < 100; i++) {
			System.out.print("-");
		}
		System.out.println("\nBased on alos (" + alosHashachar + ") and tzeis ("+ tzesHakochavim + "):\n");
		printTimes(timesLong);
	}

	private void printTimes(String[] times) {
		StringBuilder sb = new StringBuilder();
		sb.append("Sof zman krias shema is " + times[0] + ".\n");
		sb.append("Sof zman shacharis is " + times[1] + ".\n");
		sb.append("Chatzos is " + times[2] + ".\n");
		sb.append("Mincha gedolah is " + times[3] + ".\n");
		sb.append("Mincha ketanah is " + times[4] + ".\n");
		sb.append("Plag hamincha is " + times[5]+ ".\n");
		System.out.println(sb.toString());
	}

	private String[] calculateTimes(int minutes, String start) {
		String[] startParts = start.split(":");
		int minutesTillStart = calculateMinFromMidnight(startParts);
		double kriasShemaDouble = new Double(minutes) / 4 + minutesTillStart;
		double tefillahDouble = new Double(minutes) / 3 + minutesTillStart;
		double chatzosDouble = new Double(minutes) / 2 + minutesTillStart;
		double minchaDouble = new Double(minutes) / 12 * 6.5 + minutesTillStart;
		double minchaKetanaDouble = new Double(minutes) / 12 * 9.5 + minutesTillStart;
		double plagDouble = new Double(minutes) / 12 * 10.75 + minutesTillStart;
		String kriasShemaString = getTime(kriasShemaDouble);
		String tefillahString = getTime(tefillahDouble);
		String chatzosString = getTime(chatzosDouble);
		String minchaString = getTime(minchaDouble);
		String minchaKetanaString = getTime(minchaKetanaDouble);
		String plagString = getTime(plagDouble);
		String[] times = 
			{kriasShemaString, tefillahString, chatzosString, minchaString, minchaKetanaString, plagString};
		return times;
	}

	private String getTime(double minutesPassed) {
		int hours = (int) (minutesPassed / 60);
		int minutes = (int) (minutesPassed % 60);
		String time = String.valueOf(hours) + ":" + String.valueOf(minutes);
		return time;
	}

	private int calculateMinInRange(String start, String finish) {
		String[] startParts = start.split(":");
		String[] finishParts = finish.split(":");
		int startMinutes = calculateMinFromMidnight(startParts);
		int finishMinutes = calculateMinFromMidnight(finishParts);
		int totalMinutes = finishMinutes - startMinutes;
		return totalMinutes;
	}

	private int calculateMinFromMidnight(String[] parts) {
		int hours = Integer.parseInt(parts[0]);
		int minutes = Integer.parseInt(parts[1]);
		int totalMinutes = (hours * 60) + minutes;
		return totalMinutes;
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
		if(string.startsWith("0")) string = string.substring(1);
		return string;
	}

	private String[] getInfo() {
		WebElement info = driver.findElement(By.id("sun"));
		String infoString = info.getText();
		String[] infoParts = infoString.split(" ");
		return infoParts;
	}

}
