package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {

	WebDriver driver;

	public OrganizationsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[@class='hdrLink']")
	private WebElement lnkOgranizationsHeader;

	@FindBy(xpath = "//div[@id='miniCal']/following-sibling::table/tbody/tr/td[@class='small']/table//a[text()='Organizations']")
	private WebElement lnkOrganization;

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement icnAddOrganization;

	@FindBy(xpath = "//input[@name='accountname']")
	private WebElement inputOrgName;

	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement btnSave;

	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement txtOrgSaving;

	@FindBy(name = "search_text")
	private WebElement SearchBox;

	@FindBy(id = "bas_searchfield")
	private WebElement SelectSearch;

	@FindBy(xpath = "//input[@name='submit']")
	private WebElement btnSearch;

	@FindBy(xpath = "//select[@name='industry']")
	private WebElement slctIndustry;

	@FindBy(xpath = "//a[@class='hdrLink' and text()='Organizations']")
	private WebElement lnkHeaderOrganization;

	public WebElement getLnkOgranizationsHeader() {
		return lnkOgranizationsHeader;
	}

	public WebElement getLnkOrganization() {
		return lnkOrganization;
	}

	public WebElement getIcnAddOrganization() {
		return icnAddOrganization;
	}

	public WebElement getInputOrgName() {
		return inputOrgName;
	}

	public WebElement getBtnSave() {
		return btnSave;
	}

	public WebElement getTxtOrgSaving() {
		return txtOrgSaving;
	}

	public WebElement getSearchBox() {
		return SearchBox;
	}

	public WebElement getSelectSearch() {
		return SelectSearch;
	}

	public WebElement getBtnSearch() {
		return btnSearch;
	}

	public String getActOrgName() {
		return txtOrgSaving.getText();
	}

	public WebElement getSlctIndustry() {
		return slctIndustry;
	}

	public WebElement getLnkHeaderOrganization() {
		return lnkHeaderOrganization;
	}

}
