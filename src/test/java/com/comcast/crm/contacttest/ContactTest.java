package com.comcast.crm.contacttest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class ContactTest extends BaseClass {

	@Test(groups = { "SmokeTest" })
	public void ContactCreationWithLastNameTest() throws Exception {

		ContactPage contactp = new ContactPage(driver);
		ExcellUtility excelUtility = new ExcellUtility();
		JavaUtility javaUtility = new JavaUtility();

		String lastNameData = excelUtility.getDataFromExcell("Contact", 1, 2) + javaUtility.getRandomNumber();

		contactp.getLnkContact().click();

		contactp.getIcnAddContact().click();

		contactp.getInpLastName().sendKeys(lastNameData);

		contactp.getBtnSave().click();
	}

	@Test(groups = { "RegressionTest" })
	public void ContactCreationWithSupportDateTest() throws Exception {

		ExcellUtility excelUtility = new ExcellUtility();
		WebDriverUtility webDriverUtility = new WebDriverUtility();

		ContactPage contactp = new ContactPage(driver);

		String startDate = javaUtil.getSystemDateYYYYDDMM();
		String endDate = javaUtil.getRequriredDateYYYYDDMM(30);
		String lastNameData = excelUtility.getDataFromExcell("Contact", 3, 2) + javaUtil.getRandomNumber();

		contactp.getLnkContact().click();

		contactp.getIcnAddContact().click();

		contactp.getInpLastName().sendKeys(lastNameData);

		contactp.getDateStartSupport().clear();
		contactp.getDateStartSupport().sendKeys(startDate);

		contactp.getDateEndSupport().clear();
		contactp.getDateEndSupport().sendKeys(endDate);

		contactp.getBtnSave().click();

		webDriverUtility.waitForElementText(driver, contactp.getTxtContactHeader(), lastNameData);

		if (contactp.getStartDateInfo().getText().equalsIgnoreCase(startDate)) {
			System.out.println("Start Supoort Date " + contactp.getStartDateInfo().getText() + " is Equal==>PASS");
		} else {
			System.out.println("Start Supoort Date " + contactp.getStartDateInfo().getText() + " is not Equal==>FAIL");
		}

		if (contactp.getEndDateInfo().getText().equalsIgnoreCase(endDate)) {
			System.out.println("End Supoort Date " + contactp.getEndDateInfo().getText() + " is Equal==>PASS");
		} else {
			System.out.println("End Supoort Date " + contactp.getEndDateInfo().getText() + " is not Equal==>FAIL");
		}

	}

	@Test(groups = { "RegressionTest" })
	public void ContactCreationWithOrgTest() throws Exception {
		ExcellUtility excelUtility = new ExcellUtility();
		WebDriverUtility webDriverUtility = new WebDriverUtility();

		OrganizationsPage orgPage = new OrganizationsPage(driver);
		ContactPage contactp = new ContactPage(driver);

		String orgNameData = excelUtility.getDataFromExcell("Contact", 6, 2) + javaUtil.getRandomNumber();
		String lastNameData = excelUtility.getDataFromExcell("Contact", 6, 3);

		orgPage.getLnkOrganization().click();

		orgPage.getIcnAddOrganization().click();

		orgPage.getInputOrgName().sendKeys(orgNameData);

		orgPage.getBtnSave().click();

		String actOrgName = orgPage.getActOrgName();

		boolean orgStatus = actOrgName.contains(orgNameData);

		Assert.assertTrue(orgStatus);

		Thread.sleep(500);

		contactp.getLnkContact().click();

		contactp.getIcnAddContact().click();

		contactp.getInpLastName().sendKeys(lastNameData);

		contactp.getIcnAddOrganization().click();

		webDriverUtility.switchToWindowPartialTitle(driver, orgNameData);

		contactp.getInpSearchTxt().sendKeys(orgNameData);

		webDriverUtility.selectByVisualText(driver, contactp.getSelectSearchField(), "Organization Name");

		contactp.getBtnSearch().click();

		webDriverUtility.waitForElementText(driver, contactp.getTxtorgSpecific(), orgNameData);

		contactp.getTxtorgSpecific().click();

		webDriverUtility.switchToWindowPartialUrl(driver, orgNameData);

		contactp.getBtnSave().click();

	}

}
