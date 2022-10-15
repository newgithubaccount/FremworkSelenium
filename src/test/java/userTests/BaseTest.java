package userTests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReports;
	public ExtentTest extentTest;
	WebDriver driver;

	@BeforeTest
	public void launchBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterTest
	public void quitBrowser() {
		driver.quit();
	}

	@BeforeSuite
	public void createReports() {
		htmlReporter = new ExtentHtmlReporter("./report/ExtentReport_" + System.currentTimeMillis() + ".html");
		// Create an object of Extent Reports
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);

	}

	@AfterSuite
	public void flushReports() {
		extentReports.flush();
	}

	public void assertIntValues(int actual, int expected, ExtentTest test) {

	}

	public void assertTrue(boolean result, ExtentTest test) {
		if (!result)
			test.info("Test failed due to comparision mismatch");
		test.addScreenCaptureFromBase64String("passscreenshot");
	
		Assert.assertTrue(result);
	}

	public void assertFalse(boolean result, ExtentTest test) {
		if (result)
			test.info("test passed ad the assert is passed ");
		Assert.assertTrue(result);
	}

	public String getScreenshotPath() {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = "./screenshots/" + System.currentTimeMillis() + ".png";
		try {
			FileUtils.copyFile(src, new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
