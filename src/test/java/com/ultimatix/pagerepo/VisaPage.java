package com.ultimatix.pagerepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VisaPage {
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='592407']")
	private WebElement visa_592407;

	public void clickOn_592407()
	{
		visa_592407.click();
	}
	public VisaPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
