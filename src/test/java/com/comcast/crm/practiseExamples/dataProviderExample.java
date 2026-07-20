package com.comcast.crm.practiseExamples;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcellUtility;

public class dataProviderExample {

	@Test(dataProvider = "getData")
	public void toCapturePhonePrice(String brand, String phoneName) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		driver.get("https://www.amazon.in/");

		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brand, Keys.ENTER);

		String x = "//span[contains(text(),'" + phoneName
				+ "')]/ancestor::div[@class='puisg-col-inner']/descendant::span[@class='a-price-whole']";

		System.out.println(phoneName + "---->" + driver.findElement(By.xpath(x)).getText());

		driver.quit();
	}

	@DataProvider
	public Object[][] getData() throws Exception {
		ExcellUtility excelUtil = new ExcellUtility();
		int rowCount = excelUtil.getRowCount("PhoneList");
		Object data[][] = new Object[rowCount][2];

		for (int i = 0; i < rowCount; i++) {
			data[i][0] = excelUtil.getDataFromExcell("PhoneList", i + 1, 0);
			data[i][1] = excelUtil.getDataFromExcell("PhoneList", i + 1, 1);
		}
		return data;
	}

}
