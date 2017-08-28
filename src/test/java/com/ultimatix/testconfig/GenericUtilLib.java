package com.ultimatix.testconfig;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GenericUtilLib 
{
	public static WebDriver driver;
	static int lastRow;
	static int lastCell;
	//*****Reading data from a particular row of a sheet ***************
	public static void readExcel(String sheetName,int rowNo)
	{
		int rowcount=1;
		int cellcount=0;
		String s="abc";
		List<String> cellValue;
		Map<String,List<String>> map;
		File f= new File("./Data/Test-Data.xlsx");
		try {
			map=new HashMap<String,List<String>>();
			FileInputStream fis= new FileInputStream(f);
			Workbook wb= WorkbookFactory.create(fis);
			int totalrow=wb.getSheet(sheetName).getLastRowNum();
			while(rowcount<totalrow)
				{
					Row r= wb.getSheet(sheetName).getRow(rowcount);
					cellValue= new ArrayList<String>();
					int totalcell=wb.getSheet(sheetName).getRow(rowcount).getLastCellNum();
					while(cellcount<totalcell)
					{
						try{

							cellValue.add(r.getCell(cellcount).getStringCellValue());
							
						}
						catch(Exception e)
						{							
							r.getCell(cellcount).toString();
						}
						finally{
							System.out.println("*******Unable to read data***************");
							System.out.println("***Sheet : "+sheetName);
							System.out.println("***Row Number : "+rowcount);
							System.out.println("***Cell number : "+ cellcount);
						}
						map.put(r.getCell(cellcount).toString(),cellValue);
						
						cellcount++;
					}
					
					rowcount++;
				}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	// Return no. of row *****************************************************
	public static int returnRow(String sheetName) 
	{		
			FileInputStream fis;
			try {
				fis = new FileInputStream("./Data/Test-Data.xlsx");
				Workbook wb= WorkbookFactory.create(fis);
				lastRow= wb.getSheet(sheetName).getLastRowNum();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Terminated");
				e.printStackTrace();
				
			}		
		return lastRow;
	}
	
	// Return no. of cell in a row***********************
	public static int returnCellCount(String sheetName,int rowNo) 
	{		
			FileInputStream fis;
			try {
				fis = new FileInputStream("./Data/Test-Data.xlsx");
				Workbook wb= WorkbookFactory.create(fis);
				lastCell = wb.getSheet(sheetName).getRow(rowNo).getLastCellNum();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Terminated");
				e.printStackTrace();
				
			}		
		return lastCell;
	}
	
	//Return data of particular cell
	public static String cellValue(String sheetName,int row,int cell)
	{   String value=null;
		FileInputStream fis;
		try {
			fis = new FileInputStream("./Data/Test-Data.xlsx");
			Workbook wb= WorkbookFactory.create(fis);
			value=wb.getSheet(sheetName).getRow(row).getCell(cell).toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
		
	}
	
	//Run browser
	
	public static WebDriver runBrowser(String browser, String url)
	{
		switch (browser)
		{
		case "Mozilla" : driver= new FirefoxDriver();break;
		case "Chrome" : {System.setProperty("webdriver.chrome.driver", "./Browser-exe/chromedriver.exe");
							driver =new ChromeDriver();
						}break;
//		case "InternetExplorer" :
		}
		driver.manage().window().maximize();
		driver.get(url);
		
		
		
		return driver;
	}
	public static List <String> returnExecutionSheetName()
	{
		FileInputStream fis;
		List <String> sheetName=new ArrayList<String>();
		try {
			fis = new FileInputStream("./Data/Test-Data.xlsx");
			Workbook wb= WorkbookFactory.create(fis);
			int lastRow=wb.getSheet("Execution").getLastRowNum();
			for(int r=1;r<lastRow;r++)
			{
				String flag= wb.getSheet("Execution").getRow(r).getCell(1).toString();
				if(flag.equals("Y"))
				{
					sheetName.add(wb.getSheet("Execution").getRow(r).getCell(2).toString());
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return sheetName;
		
	}

}
