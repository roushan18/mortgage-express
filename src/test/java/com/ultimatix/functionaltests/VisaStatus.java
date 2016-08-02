package com.ultimatix.functionaltests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.ultimatix.pagerepo.HomePage;
import com.ultimatix.pagerepo.VisaPage;
import com.ultimatix.testconfig.TestConfiguration;
import com.ultimatix.utils.GenericUtilLib;

public class VisaStatus extends TestConfiguration{
	public static String browser= "Chrome";
	public static String url="https://www.ultimatix.net";
	public static String userName;
	protected static String pwd;
	LoginUx lu;
	public static String uxHome;

	
	
	@BeforeClass
	public void loginUx()
	{
		lu= new LoginUx();	
//		InputStreamReader isr= new InputStreamReader(System.in);
//		BufferedReader br= new BufferedReader (isr);
//		
//		try {
//			System.out.println("Enter username");
//			userName=br.readLine();
//			System.out.println("Enter Password");
//			pwd=br.readLine();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		userName="roushan18";
		pwd="xyz02";
		lu.loginUltimatix_TC01(browser, url, userName, pwd);		
		String homeTitle=driver.getTitle();
		WebDriverWait wait= new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.titleContains("Home : Ultimatix - Digitally Connected !"));
		
	}
  @Test
  public void CheckVisaStatus() {
	  HomePage hp= new HomePage(driver);
	  hp.navigateVisaLink();
	  GenericUtilLib.captureScreen(driver);
	  Set<String> handles= driver.getWindowHandles();
	  Iterator<String>it= handles.iterator();
	  uxHome=it.next();
	  String uxVisa=it.next();
	  driver.switchTo().window(uxVisa);
	  System.out.println("Control has been moved to Visa page");
	  WebDriverWait wait= new WebDriverWait(driver,30);
	  wait.until(ExpectedConditions.titleContains("Welcome to Associate Home Page"));
	  GenericUtilLib.captureScreen(driver);
	  VisaPage vp= new VisaPage(driver);
	  vp.clickOn_592407();
	  GenericUtilLib.captureScreen(driver);
  }
  @AfterMethod
  public void logout_Visa()
  {
	  lu.logOutUltimatix_Tc02();
	  driver.switchTo().window(uxHome);
  }
  
  @AfterClass
  public void logout()
  {
	  lu.logOutUltimatix_Tc02();
  }
  
}
