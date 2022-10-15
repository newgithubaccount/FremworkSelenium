package userTests;

import org.testng.annotations.Test;



import pageObjects.HomePage;
import pageObjects.LoginPage;

public class DemoTest extends BaseTest {

	@Test
	public void loginUserTest() {
		extentTest = extentReports.createTest("loginUserTest");
		LoginPage loginPage = new LoginPage(driver, extentTest);
		HomePage homePage = loginPage.login("standard_user", "secret_sauce");
		assertTrue(homePage.verifyPageTitle(), extentTest);
		homePage.verifyLogout();
		//extentTest.log(LogStatus.PASS, "LoginTest passed");
	//	extentReport.endTest(extentTest);
	}

	@Test
	public void logoutUserTest() {
		extentTest = extentReports.createTest("logoutUserTest");
		LoginPage loginPage = new LoginPage(driver, extentTest);
		HomePage homePage = loginPage.login("standard_user", "secret_sauce");
		assertTrue(homePage.verifyLogout().verifyPageTitle(), extentTest);
		
//		extentTest.log(LogStatus.PASS, "LogoutTest passed");
//		extentReport.endTest(extentTest);
	}

	@Test
	public void lockedUserTest() {
		extentTest = extentReports.createTest("lockedUserTest");
		LoginPage loginPage = new LoginPage(driver, extentTest);
		boolean result = loginPage.verifyLockedUser("locked_out_user",
				"secret_sauce");
		assertTrue(result, extentTest);
	//	extentTest.log(LogStatus.PASS, "LockedTest passed");
	//	extentReport.endTest(extentTest);
	}
	@Test
	public void cartAdditionTest() {
		extentTest = extentReports.createTest("cartAdditionTest");
		LoginPage loginPage = new LoginPage(driver, extentTest);
		HomePage homePage = loginPage.login("standard_user", "secret_sauce");
		assertTrue(homePage.validateCart(), extentTest);
		homePage.verifyLogout();
	//	extentTest.log(LogStatus.PASS, "ValidateCartTsest passed");
	//	extentReport.endTest(extentTest);
	}
	@Test
	public void removeCartItemaTest() {
		extentTest = extentReports.createTest("removeCartItemaTest");
		LoginPage loginPage = new LoginPage(driver, extentTest);
		HomePage homePage = loginPage.login("standard_user", "secret_sauce");
		assertTrue(homePage.validateRemoveCart(), extentTest);
		homePage.verifyLogout();
	//	extentTest.log(LogStatus.PASS, "ValidateRemoveCartTsest passed");
	//	extentReport.endTest(extentTest);
	}
}
