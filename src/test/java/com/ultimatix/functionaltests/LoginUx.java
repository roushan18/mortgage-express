package com.ultimatix.functionaltests;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.ultimatix.pagerepo.LoginPage;
import com.ultimatix.testconfig.TestConfiguration;
import com.ultimatix.utils.GenericUtilLib;



public class LoginUx extends TestConfiguration{
	
	@Test(dataProvider="getData_LoginUx")
	public void loginUltimatix_TC01(String browser, String url,String userName, String pwd)
	{
		WebDriver driver= GenericUtilLib.runBrowser( browser, url);
		GenericUtilLib.captureScreen(driver);
		LoginPage lp= new LoginPage(driver);
		lp.login(userName, pwd);
		GenericUtilLib.captureScreen(driver);
		lp.logout();
		driver.close();
	
	}

}
