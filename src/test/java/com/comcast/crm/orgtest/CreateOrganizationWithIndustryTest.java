package com.comcast.crm.orgtest;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrganizationWithIndustryTest extends BaseClass {

	@Test
	public void OrganizationCreationWithIndustryTest() throws Exception {

		OrganizationsPage orgPage = new OrganizationsPage(driver);
		WebDriverUtility webUtility = new WebDriverUtility();

		orgPage.getLnkOrganization().click();

		orgPage.getIcnAddOrganization().click();

		String orgNameData = excelUtil.getDataFromExcell("Organization", 4, 2) + javaUtil.getRandomNumber();

		orgPage.getInputOrgName().sendKeys(orgNameData);

		webUtility.selectByValue(driver, orgPage.getSlctIndustry(), "Consulting");

		orgPage.getBtnSave().click();

		String actOrgName = orgPage.getActOrgName()+ "sdsd";

		Assert.assertEquals(actOrgName, orgNameData);
	}

}
