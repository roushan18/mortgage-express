package com.ultimatix.functionaltests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ultimatix.testconfig.TestConfiguration;
import com.ultimatix.utils.GenericUtilLib;

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class LaunchMultiBrowser extends TestConfiguration
{
//	String sheetName="CrossBrowser";
	@Test(dataProvider="getData_Lauch")
	public void launch(String browser, String url,String pageTitle)
	{
		WebDriver driver=GenericUtilLib.runBrowser(browser, url);
		String titleName= driver.getTitle();
		System.out.println(titleName);
		System.out.println("Execution Started" );
		Assert.assertEquals(pageTitle, titleName);
		driver.close();
		
	}
	
	
	
}
//    public LaunchMultiBrowser( String testName )
//    {
//        super( testName );
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite()
//    {
//        return new TestSuite( LaunchMultiBrowser.class );
//    }
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp()
//    {
//        assertTrue( true );
//    }
//}
