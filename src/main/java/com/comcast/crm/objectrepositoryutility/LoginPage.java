package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.Status;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage extends WebDriverUtility {

	public WebDriver driver;
	UtilityClassObject utilObj = new UtilityClassObject();

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "user_name")
	private WebElement inputUsername;

	@FindBy(name = "user_password")
	private WebElement inputPassword;

	@FindBy(id = "submitButton")
	private WebElement btnSubmit;

	@FindBy(xpath = "//img[contains(@src,'Home')]")
	private WebElement lnkHomeIcon;

	public void loginIntoPage(String Url, String username, String password) {

		implicitlyWait(driver);
		pageLoadWait(driver);
		maximizeWindow(driver);
		driver.get(Url);

		inputUsername.sendKeys(username);

		inputPassword.sendKeys(password);

		btnSubmit.click();
	}

	public WebElement getLnkHomeIcon() {
		return lnkHomeIcon;
	}

}
