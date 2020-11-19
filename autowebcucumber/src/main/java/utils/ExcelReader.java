package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelReader {

	private String fileName;
	private String sheetName;
	private int sheetIndex;
	private HSSFWorkbook book;

	private ExcelReader(ExcelReaderBuilder excelReaderBuilder) {
		this.fileName = excelReaderBuilder.fileName;
		this.sheetIndex = excelReaderBuilder.sheetIndex;
		this.sheetName = excelReaderBuilder.sheetName;
	}

	public static class ExcelReaderBuilder {

		private String fileName;
		private String sheetName;
		private int sheetIndex;

		public ExcelReaderBuilder setFileLocation(String location) {
			this.fileName = location;
			return this;
		}

		public ExcelReaderBuilder setSheet(String sheetName) {
			this.sheetName = sheetName;
			return this;
		}

		public ExcelReaderBuilder setSheet(int index) {
			this.sheetIndex = index;
			return this;
		}

		public ExcelReader build() {
			return new ExcelReader(this);
		}

	}

	private HSSFWorkbook getWorkBook(String filePath) throws InvalidFormatException, IOException {
		return new HSSFWorkbook(new FileInputStream(filePath));
	}

	private HSSFSheet getWorkBookSheet(String fileName, String sheetName) throws InvalidFormatException, IOException {
		this.book = getWorkBook(fileName);
		return this.book.getSheet(sheetName);
	}

	private HSSFSheet getWorkBookSheet(String fileName, int sheetIndex) throws InvalidFormatException, IOException {
		this.book = getWorkBook(fileName);
		return this.book.getSheetAt(sheetIndex);
	}

	public List<List<String>> getSheetData() throws IOException{
		HSSFSheet sheet;
		List<List<String>> outerList = new LinkedList<>();

		try {
			sheet = getWorkBookSheet(fileName, sheetName);
			outerList = getSheetData(sheet);
		} catch (InvalidFormatException e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			//this.book.close();
		}
		return outerList;
	}

	public List<List<String>> getSheetDataAt() throws InvalidFormatException, IOException {

		HSSFSheet sheet;
		List<List<String>> outerList = new LinkedList<>();

		try {
			sheet = getWorkBookSheet(fileName, sheetIndex);
			outerList = getSheetData(sheet);
		} catch (InvalidFormatException e) {
			throw new RuntimeException(e.getMessage());
		}finally {
			//this.book.close();
		}
		return outerList;
	}

	private List<List<String>> getSheetData(HSSFSheet sheet) {
		List<List<String>> outerList = new LinkedList<>();
		prepareOuterList(sheet, outerList);
		return Collections.unmodifiableList(outerList);
	}

	private void prepareOuterList(HSSFSheet sheet, List<List<String>> outerList) {
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			List<String> innerList = new LinkedList<>();
			HSSFRow hssfRow = sheet.getRow(i);

			for (int j = hssfRow.getFirstCellNum(); j < hssfRow.getLastCellNum(); j++) {
				prepareInnerList(innerList, hssfRow, j);
			}
			outerList.add(Collections.unmodifiableList(innerList));
		}
	}

	private void prepareInnerList(List<String> innerList, HSSFRow hssfRow, int j) {
		/*switch (hssfRow.getCell(j).getCellType()) { //Commented by Siva

		case Cell.CELL_TYPE_BLANK:
			innerList.add("");
			break;

		case Cell.CELL_TYPE_STRING:
			innerList.add(hssfRow.getCell(j).getStringCellValue());
			break;

		case Cell.CELL_TYPE_NUMERIC:
			innerList.add(hssfRow.getCell(j).getNumericCellValue() + "");
			break;

		case Cell.CELL_TYPE_BOOLEAN:
			innerList.add(hssfRow.getCell(j).getBooleanCellValue() + "");
			break;

		default:
			throw new IllegalArgumentException("Cannot read the column : " + j);
		}*/
	}
}