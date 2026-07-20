package com.comcast.crm.generic.listenerutility;

import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class ListenerImpClass implements ITestListener, ISuiteListener {

	public ExtentSparkReporter spark;
	public static ExtentReports report;
	public ExtentTest test;
	WebDriverUtility webDriverUtility = new WebDriverUtility();
	JavaUtility javaUtility = new JavaUtility();

	public void onTestStart(ITestResult result) {
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);

		test.log(Status.INFO, result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName());
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getTestName());

		String screenShotFilePath = webDriverUtility.getPathOfScreenShotFile(UtilityClassObject.getDriver(),
				result.getMethod().getMethodName());

		String screenShotBase64Path = webDriverUtility.getPathOfScreenShotBase64(UtilityClassObject.getDriver());

		test.addScreenCaptureFromPath(screenShotFilePath,
				result.getMethod().getMethodName() + " " + javaUtility.getTimeStamp());

		test.addScreenCaptureFromBase64String(screenShotBase64Path,
				result.getMethod().getMethodName() + " " + javaUtility.getTimeStamp());

	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName());
	}

	public void onStart(ITestContext context) {
		spark = new ExtentSparkReporter(
				"./AdvanceReport/" + context.getSuite().getName() + "-" + javaUtility.getTimeStamp());
		spark.config().setDocumentTitle(context.getSuite().getName());
//		spark.config().setReportName(context.getSuite().getXmlSuite().getTest());
		spark.config().setTheme(Theme.DARK);

		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-52");

	}

	public void onFinish(ITestContext context) {
		report.flush();
	}

}
