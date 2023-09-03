package test_data;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excel_Data {
	public static Workbook wb;
	public static Sheet sheet;
	public static Object[][] read_data(String sheetname){
		FileInputStream file = null;
		try {
			file = new FileInputStream("C:\\Users\\jvnsi\\eclipse-workspace\\HR_Management\\src\\test\\java\\test_data\\Login_data.xlsx");
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			wb = WorkbookFactory.create(file);
		}
		catch(IOException a) {
			a.printStackTrace();
		}
		sheet = wb.getSheet(sheetname);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for(int i=0;i<sheet.getLastRowNum();i++) {
			for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
				data[i][j] = sheet.getRow(i+1).getCell(j).toString();
			}
		}
		return data;
	}	
}
