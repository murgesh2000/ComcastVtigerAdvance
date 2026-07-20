package com.comcast.crm.contacttest;

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcellUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;

public class CreateContactWithLastNameTest extends BaseClass {

	@Test
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

}
