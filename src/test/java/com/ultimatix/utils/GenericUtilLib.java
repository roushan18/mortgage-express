package com.ultimatix.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class GenericUtilLib 
{
	public static WebDriver driver;
	static int lastRow;
	static int lastCell;


	//*****Reading data from a particular row of a sheet************
	public static void readExcel(String sheetName,int rowNo)
	{
		int rowcount=1;
		int cellcount=0;
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
							System.out.println("*******Unable to read data************");
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
//	Taking screenShots
	public static XWPFRun r;
	public static XWPFDocument doc;
	public static FileOutputStream sShot;
	public static void captureScreen(WebDriver driver)
	{
//		docx= new XWPFDocument();
//		XWPFParagraph par= docx.createParagraph();
//		XWPFRun r= par.createRun();
//		EventFiringWebDriver edriver= new EventFiringWebDriver(driver);
//		File src= edriver.getScreenshotAs(OutputType.FILE);
//		File src= (new EventFiringWebDriver(driver)).getScreenshotAs(OutputType.FILE);
//		File dest = new File("Z:/Eclipse_Selenium/mortgage-express/Screenshot/testfile.png");
		try{
//			FileUtils.copyFile(src, dest);
			FileUtils.copyFile((new EventFiringWebDriver(driver)).getScreenshotAs(OutputType.FILE), new File("Z:/Eclipse_Selenium/mortgage-express/Screenshot/MX-Ux.png"));
			FileInputStream pic= new FileInputStream("Z:/Eclipse_Selenium/mortgage-express/Screenshot/MX-Ux.png");
			r.addBreak();
			r.setText("Image File");
			r.addPicture(pic, XWPFDocument.PICTURE_TYPE_PNG,"Z:/Eclipse_Selenium/mortgage-express/Screenshot/MX-Ux.png",Units.toEMU(500) , Units.toEMU(300));
//			fos = new FileOutputStream ("Z:/Eclipse_Selenium/mortgage-express/Screenshot/screen-print.docx");
//			docx.write(fos);
//			Thread.sleep(5000);
//			docx.close();
//			fos.close();
			pic.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
	}

}
