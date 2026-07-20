package com.comcast.crm.orgtest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgTest extends BaseClass {

	@Test()
	public void OrganizationCreationTest() throws Exception {

		OrganizationsPage orgPage = new OrganizationsPage(driver);

		orgPage.getLnkOrganization().click();

		orgPage.getIcnAddOrganization().click();

		String orgNameData = excelUtil.getDataFromExcell("Organization", 1, 2) + javaUtil.getRandomNumber();

		orgPage.getInputOrgName().sendKeys(orgNameData);

		orgPage.getBtnSave().click();

		String actOrgName = orgPage.getActOrgName();

		boolean orgStatus = actOrgName.contains(orgNameData);

		Assert.assertTrue(orgStatus);

	}

}
