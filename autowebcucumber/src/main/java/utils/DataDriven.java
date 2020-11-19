package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataDriven 
{

	
	@Test
public void readExcel() throws BiffException, IOException
{
	FileInputStream fis=new FileInputStream("C:\\AceHardware\\OSAF_Acenet\\autowebcucumber\\autowebcucumber\\src\\test\\resources\\files\\Testingdata.xls");
	Workbook workbook =Workbook.getWorkbook(fis);
	Sheet sheet=workbook.getSheet("Acenet");
	int rows=sheet.getRows();
	System.out.println(rows);
	int columns=sheet.getColumns();
	String Testdata[][] =new String[rows-1][columns];
	int count=0;
	for(int i=1;i<rows;i++) {
		for(int j=0;j<columns;j++) {
			Cell a=sheet.getCell(j,i);
			Testdata[count][j]=a.getContents();
			System.out.println(Testdata[count][j]);
}
		count++;
}
	
}	
}

