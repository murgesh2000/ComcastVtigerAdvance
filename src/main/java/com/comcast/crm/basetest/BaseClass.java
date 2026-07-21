package com.comcast.crm.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

	@BeforeSuite(alwaysRun = true)
	public void configBeforeSuite() {
		// dbUtil.getDbConnection();
	}

	@Parameters("BROWSER")
	@BeforeClass(alwaysRun = true)
	public void configBeforeClass(@Optional("chrome") String BROWSER) throws Exception {
		if (BROWSER.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless=new");
			driver = new ChromeDriver(options);
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		sDriver = driver;
		UtilityClassObject.setDriver(driver);

	}

	@BeforeMethod(alwaysRun = true)
	public void configBeforeMethod() throws Exception {
		LoginPage lp = new LoginPage(UtilityClassObject.getDriver());

		String URL = fileUtil.getDataFromPropertiesFile("URL");
		String UserName = fileUtil.getDataFromPropertiesFile("Username");
		String PassWord = fileUtil.getDataFromPropertiesFile("password");

		lp.loginIntoPage(URL, UserName, PassWord);
	}

	@AfterMethod(alwaysRun = true)
	public void configAfterMethod() {
		HomePage hp = new HomePage(UtilityClassObject.getDriver());
		hp.logOut(UtilityClassObject.getDriver());
	}

	@AfterClass(alwaysRun = true)
	public void configAfterClass() {
		UtilityClassObject.getDriver().quit();
	}

	@AfterSuite(alwaysRun = true)
	public void configAfterSuite() {
		// dbUtil.closeConnection();
	}
}
