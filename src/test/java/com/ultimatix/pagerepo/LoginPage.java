package com.ultimatix.pagerepo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage 
{
	WebDriver driver;
	
	//User-name or ID web-element
	@FindBy(id="USER")
	private WebElement userName;
	
	//Password web-element
	@FindBy(id="PASSWORD")
	private WebElement userPwd;
	
	//Login button web-element
	@FindBy(id="login_button")
	private WebElement userLogin;
	
	//Incorrect Login
	@FindBy(id="errorLabel")
	private WebElement errorLogin;
	
	//Forgot Username/Password
	@FindBy(id="ForgotUsernamePwdLink")
	private WebElement forgotUserNamePwd;
	
	//Log-out
	@FindBy(id="uam_modal")
	private WebElement uxLogout;
	
	//Constructor
	public LoginPage(WebDriver driver)
	{
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	public void login(String name,String pwd)
	{
		userName.sendKeys(name);
		userPwd.sendKeys(pwd);
		userLogin.click();
		
	}
	public void logout()
	{
		uxLogout.click();
	
	}

}
