package com.ultimatix.testconfig;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.ultimatix.utils.GenericUtilLib;

public class TestConfiguration extends GenericUtilLib{
//	public WebDriver driver;
//	String sheetName="CrossBrowser"; 
	String docPath="Z:/Eclipse_Selenium/mortgage-express/Screenshot/Mortgage-Express_Login.docx";

	Object [][]data;
	@DataProvider
	public Object [][] getData_Launch()
	{
		String sheetName="CrossBrowser"; 
		int lastRow=GenericUtilLib.returnRow(sheetName);
		data= new Object[lastRow][3];
		System.out.println("Last Row is: "+lastRow);
		//cell=3 meaning ,storing 3 types of values
		
		for(int row=0;row<lastRow;row++)
		{
			int lastCell= GenericUtilLib.returnCellCount(sheetName, row);
			System.out.println("Last Cell is: "+lastCell);
			
			for(int cell=0;cell<lastCell-1;cell++)
			{
				data[row][cell]= GenericUtilLib.cellValue(sheetName, row+1, cell+1);
				System.out.println(data[row][cell]);
			}
		}

		return data;
	}


	@DataProvider
	public Object [][] getData_LoginUx()
	{
		
		String sheetName="LoginTC"; 
		int lastRow=GenericUtilLib.returnRow(sheetName);
		data= new Object[lastRow][4];
		System.out.println("Last Row is: "+lastRow);
		//cell=4 meaning ,storing 4 types of values
		
		for(int row=0;row<lastRow;row++)
		{
			int lastCell= GenericUtilLib.returnCellCount(sheetName, row);
			System.out.println("Last Cell is: "+lastCell);
			
			for(int cell=0;cell<lastCell-1;cell++)
			{
				data[row][cell]= GenericUtilLib.cellValue(sheetName, row+1, cell+1);
				System.out.println(data[row][cell]);
			}
		}

		return data;
	}
	@BeforeMethod
	public void createScreenShotDoc()
	{
		
		doc= new XWPFDocument();
		r= doc.createParagraph().createRun();
		
	}
	@AfterMethod
	public void storeScreenShot()
	{
		try {
			sShot= new FileOutputStream(docPath);
			doc.write(sShot);
			sShot.close();
			doc.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

