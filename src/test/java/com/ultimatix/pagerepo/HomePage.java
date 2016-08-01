package com.ultimatix.pagerepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	//Web-element of Visa-Tracker link
	@FindBy(partialLinkText="Visa Tracker")
	private WebElement visaLink;
	
	//Web-Element of Employee services
	@FindBy(partialLinkText="Employee Services")
	private WebElement employeeServices;
	
	// constructor
	public HomePage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	//click on Visa-Tracker link
	public void clickVisaLink()
	{
		visaLink.click();
	}
	// Alternate way to click Visa link using mouse-hover
	public void navigateVisaLink()
	{
		Actions act = new Actions(driver);
		act.moveToElement(employeeServices).perform();
		visaLink.click();
		
		
	}

}
