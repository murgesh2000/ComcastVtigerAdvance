package com.comcast.crm.contacttest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;

public class CreateContactWithSupportDateTest extends BaseClass {

	@Test
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

}
