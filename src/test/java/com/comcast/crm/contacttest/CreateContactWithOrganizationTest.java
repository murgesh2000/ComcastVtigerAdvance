package com.comcast.crm.contacttest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactWithOrganizationTest extends BaseClass {

	@Test
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

		if (actOrgName.contains(orgNameData)) {
			System.out.println(orgNameData + " organization is created==>PASS");
		} else {
			System.out.println(orgNameData + " organization is not created==>FAIL");
		}

		// ------------Organization Creation Done--------------------------------

		Thread.sleep(2000);

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
