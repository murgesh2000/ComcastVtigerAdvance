package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClass {

	public WebDriver driver;

	public FileUtility fileUtil = new FileUtility();
	public ExcellUtility excelUtil = new ExcellUtility();
	public JavaUtility javaUtil = new JavaUtility();
	public WebDriverUtility WebUtil = new WebDriverUtility();
	public DataBaseUtility dbUtil = new DataBaseUtility();
	public static WebDriver sDriver;

	@BeforeSuite(groups = { "SmokeTest", "RegressionTest" })
	public void configBeforeSuite() {
		// dbUtil.getDbConnection();
	}

	@Parameters("BROWSER")
	@BeforeClass(alwaysRun = true)
	public void configBeforeClass(@Optional("chrome") String BROWSER) throws Exception {
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		sDriver = driver;
		UtilityClassObject.setDriver(driver);

	}

	@BeforeMethod(groups = { "SmokeTest", "RegressionTest" })
	public void configBeforeMethod() throws Exception {
		LoginPage lp = new LoginPage(UtilityClassObject.getDriver());

		String URL = fileUtil.getDataFromPropertiesFile("URL");
		String UserName = fileUtil.getDataFromPropertiesFile("Username");
		String PassWord = fileUtil.getDataFromPropertiesFile("password");

		lp.loginIntoPage(URL, UserName, PassWord);
	}

	@AfterMethod(groups = { "SmokeTest", "RegressionTest" })
	public void configAfterMethod() {
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		hp.logOut(UtilityClassObject.getDriver());
	}

	@AfterClass(groups = { "SmokeTest", "RegressionTest" })
	public void configAfterClass() {
		UtilityClassObject.getDriver().quit();
	}

	@AfterSuite(groups = { "SmokeTest", "RegressionTest" })
	public void configAfterSuite() {
		// dbUtil.closeConnection();
	}
}
