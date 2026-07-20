package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPage {

	WebDriver driver;
	
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='miniCal']/following-sibling::table/tbody/tr/td[@class='small']/table//a[text()='Contacts']")
	private WebElement lnkContact;

	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement icnAddContact;
	
	@FindBy(name = "lastname")
	private WebElement inpLastName;
	
	@FindBy(xpath = "//input[@name='account_name']/following-sibling::img")
	private WebElement icnAddOrganization;
	
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]")
	private WebElement btnSave;
	
	@FindBy(xpath = "//input[@id='search_txt']")
	private WebElement inpSearchTxt;
	
	@FindBy(xpath = "//select[@name='search_field']")
	private WebElement selectSearchField;
	
	@FindBy(xpath = "//tbody//tr[2]/td/a")
	private WebElement txtorgSpecific;
	
	@FindBy(xpath = "//input[@name='search']")
	private WebElement btnSearch;
	
	@FindBy(xpath = "//input[@name='support_start_date']")
	private WebElement dateStartSupport;
	
	@FindBy(xpath = "//input[@name='support_end_date']")
	private WebElement dateEndSupport;
	
	@FindBy(xpath = "//span[@id='dtlview_Support Start Date']")
	private WebElement StartDateInfo;
	
	@FindBy(xpath = "//span[@id='dtlview_Support End Date']")
	private WebElement EndDateInfo;
	
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement txtContactHeader;
	
	public WebElement getBtnSave() {
		return btnSave;
	}

	public WebElement getLnkContact() {
		return lnkContact;
	}

	public WebElement getIcnAddContact() {
		return icnAddContact;
	}

	public WebElement getInpLastName() {
		return inpLastName;
	}

	public WebElement getIcnAddOrganization() {
		return icnAddOrganization;
	}

	public WebElement getInpSearchTxt() {
		return inpSearchTxt;
	}

	public WebElement getSelectSearchField() {
		return selectSearchField;
	}
	
	public WebElement getTxtorgSpecific() {
		return txtorgSpecific;
	}

	public WebElement getBtnSearch() {
		return btnSearch;
	}

	public WebElement getDateStartSupport() {
		return dateStartSupport;
	}

	public WebElement getDateEndSupport() {
		return dateEndSupport;
	}

	public WebElement getStartDateInfo() {
		return StartDateInfo;
	}

	public WebElement getEndDateInfo() {
		return EndDateInfo;
	}

	public WebElement getTxtContactHeader() {
		return txtContactHeader;
	}
		
}
