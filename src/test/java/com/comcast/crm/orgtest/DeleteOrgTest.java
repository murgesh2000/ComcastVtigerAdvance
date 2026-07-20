package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest extends BaseClass {

	@Test
	public void DeleteOrganization() throws Exception {

		ExcellUtility excelUtil = new ExcellUtility();
		JavaUtility javaUtil = new JavaUtility();
		WebDriverUtility WebUtility = new WebDriverUtility();

		OrganizationsPage orgPage = new OrganizationsPage(driver);

		orgPage.getLnkOrganization().click();

		orgPage.getIcnAddOrganization().click();

		String orgNameData = excelUtil.getDataFromExcell("Organization", 10, 2) + javaUtil.getRandomNumber();

		orgPage.getInputOrgName().sendKeys(orgNameData);

		orgPage.getBtnSave().click();

		Thread.sleep(500);

		String actOrgName = orgPage.getActOrgName();

		boolean orgStatus = actOrgName.contains(orgNameData);

		Assert.assertTrue(orgStatus);
		// // WebUtility.waitForElementClickable(driver, lp.getLnkHomeIcon());
		// lp.getLnkHomeIcon().click();
		// corgp.getLnkOrganization().click();

		WebUtility.waitForElementText(driver, orgPage.getLnkHeaderOrganization(), "Organizations");
		orgPage.getLnkHeaderOrganization().click();

		orgPage.getSearchBox().sendKeys(orgNameData);

		WebUtility.selectByVisualText(driver, orgPage.getSelectSearch(), "Organization Name");

		orgPage.getBtnSearch().click();

		driver.findElement(By.xpath("//a[text()='" + orgNameData + "']/parent::td/parent::tr/td[8]/a[text()='del']"))
				.click();

		WebUtility.alertAccept(driver);

	}

}
