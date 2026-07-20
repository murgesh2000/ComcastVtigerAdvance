package com.comcast.crm.generic.webdriverutility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comcast.crm.basetest.BaseClass;

// Actions, Frames,Select ,Alert, WinTOWin,  ScreenShot, JavaScriptExecutor. Synchronization, 

public class WebDriverUtility {

	public void implicitlyWait(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	public void pageLoadWait(WebDriver driver) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
	}

	public void maximizeWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void MousedoubleClick(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.doubleClick(ele);
	}

	public void dragAndDropToElement(WebDriver driver, WebElement srcEle, WebElement destEle) {
		Actions act = new Actions(driver);
		act.dragAndDrop(srcEle, destEle);
	}

	public void MouseMoveToElement(WebDriver driver, WebElement ele) {
		Actions act = new Actions(driver);
		act.moveToElement(ele);
	}

	public void swithToFrame(WebDriver driver, int index) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void swithToFrame(WebDriver driver, String name) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(name));
	}

	public void swithToFrame(WebDriver driver, WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));
	}

	public void swithToParentFrame(WebDriver driver) {
		driver.switchTo().parentFrame();
	}

	public void swithToMainFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void selectByVisualText(WebDriver driver, WebElement ele, String VisualText) {
		Select select = new Select(ele);
		select.selectByVisibleText(VisualText);
	}

	public void selectByValue(WebDriver driver, WebElement ele, String value) {
		Select select = new Select(ele);
		select.selectByValue(value);
	}

	public void selectByIndex(WebDriver driver, WebElement ele, int index) {
		Select select = new Select(ele);
		select.selectByIndex(index);
	}

	public void deSelectByVisualText(WebDriver driver, WebElement ele, String VisualText) {
		Select select = new Select(ele);
		select.deselectByVisibleText(VisualText);
	}

	public void deSelectByValue(WebDriver driver, WebElement ele, String value) {
		Select select = new Select(ele);
		select.deselectByValue(value);
	}

	public void deSelectByIndex(WebDriver driver, WebElement ele, int index) {
		Select select = new Select(ele);
		select.deselectByIndex(index);
	}

	public void alertAccept(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.alertIsPresent()).accept();
	}

	public void alertDismiss(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.alertIsPresent()).dismiss();
	}

	public String alertDgetText(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		return wait.until(ExpectedConditions.alertIsPresent()).getText();
	}

	public void alertSendText(WebDriver driver, String sendText) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.alertIsPresent()).sendKeys(sendText);
	}

	public void switchToWindowPartialUrl(WebDriver driver, String partialUrl) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actUrl = driver.getCurrentUrl();
			if (actUrl.contains(partialUrl)) {
				break;
			}
		}
	}

	public void switchToWindowPartialTitle(WebDriver driver, String partialTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String windowID = it.next();
			driver.switchTo().window(windowID);

			String actTitle = driver.getTitle();
			if (actTitle.contains(partialTitle)) {
				break;
			}

		}
	}

	public void waitForElementVisibility(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(webElement));
	}

	public void waitForElementClickable(WebDriver driver, WebElement webElement) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(webElement));
	}

	public void waitForElementText(WebDriver driver, WebElement webElement, String text) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElement(webElement, text));
	}

	public String getPathOfScreenShotFile(WebDriver driver, String methodName) {
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sDriver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./ScreenShoot/" + methodName + "-" + timeStamp + ".png");
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destFile.getAbsolutePath();
	}

	public String getPathOfScreenShotBase64(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BASE64);
	}

}
