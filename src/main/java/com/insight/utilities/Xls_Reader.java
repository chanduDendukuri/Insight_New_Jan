package com.insight.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.PrintOrientation;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCreationHelper;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;
import org.apache.poi.xssf.usermodel.XSSFPrintSetup;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Xls_Reader {

	public String filename = System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "TestData.xls";
	public String path;
	public FileInputStream fis = null;
	public FileOutputStream fileOut = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	public Xls_Reader(String path) {

		//LOG.info("file path: "+System.getProperty("user.dir"));
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// returns the row count in a sheet
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, String colName, int rowNum) {
		try {
			if (rowNum <= 0)
				return "";
			//LOG.info("sheet Name"+sheetName);
			int index = workbook.getSheetIndex(sheetName);
			int col_Num = -1;
			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				//LOG.info(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);

			if (cell == null)
				return "";
			//LOG.info(cell.getCellType());
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
				//String cellText = String.valueOf(cell.getNumericCellValue());
				String cellText = new BigDecimal(cell.getNumericCellValue()).toPlainString();
				
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();

					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText =
							(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" +
							cal.get(Calendar.MONTH) + 1 + "/" +
							cellText;

					//LOG.info(cellText);

				}

				return cellText;
			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colName + " does not exist in xls";
		}
	}

	public String evaluateFormula(String sheetName, int colNum, int rowNum) {

		FormulaEvaluator evaluator = null;
		String cellvalue = null;

		try {
			cellvalue = cell.getCellFormula();
			FileInputStream inputStream = new FileInputStream(new File(System.getProperty("user.dir") + File.separator + "TestData" + File.separator + "TestData.xlsx"));
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet firstSheet = workbook.getSheet(sheetName);
			XSSFRow row = null;
			XSSFCell cell = null;
			evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			CellReference cellReference = new CellReference(cellvalue);
			Row row1 = firstSheet.getRow(cellReference.getRow());
			Cell cell1 = row1.getCell(cellReference.getCol());
			CellValue cellValue = evaluator.evaluate(cell1);
			inputStream.close();
			return String.valueOf(cellValue.getStringValue());
		} catch (Exception e) {
			return cell.getStringCellValue();
		}
	}

	// returns the data from a cell
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {

			String cellvalue = null;
			if (rowNum <= 0)
				return "";

			int index = workbook.getSheetIndex(sheetName);

			if (index == -1)
				return "";

			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(colNum);
			if (cell == null)
				return "";
			//|| cell.getCellType() == Cell.CELL_TYPE_FORMULA
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				//return evaluateFormula(sheetName,colNum,rowNum);
				return cell.getStringCellValue();
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

				//String cellText = String.valueOf(cell.getNumericCellValue());
				String cellText = new BigDecimal(cell.getNumericCellValue()).toPlainString();
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText =
							(String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" +
							cal.get(Calendar.DAY_OF_MONTH) + "/" +
							cellText;
					return cellText;

					// LOG.info(cellText);

				}
				return cellText;
			   /* else
                    LOG.info("****************CELL VALUE*****************************");
                return evaluateFormula(sheetName,colNum,rowNum);
*/

			} else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		} catch (Exception e) {

			e.printStackTrace();
			return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
		}
	}

	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName, String colName, int rowNum, String data) {
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				//LOG.info(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(colName))
					colNum = i;
			}
			if (colNum == -1)
				return false;

			sheet.autoSizeColumn(colNum);
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			// cell style
			//CellStyle cs = workbook.createCellStyle();
			//cs.setWrapText(true);
			//cell.setCellStyle(cs);
			cell.setCellValue(data);

			fileOut = new FileOutputStream(path);

			workbook.write(fileOut);

			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if data is set successfully else false
	public boolean setCellData(String sheetName, String colName, int rowNum, String data, String url) {
		//LOG.info("setCellData setCellData******************");
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);

			if (rowNum <= 0)
				return false;

			int index = workbook.getSheetIndex(sheetName);
			int colNum = -1;
			if (index == -1)
				return false;

			sheet = workbook.getSheetAt(index);
			//LOG.info("A");
			row = sheet.getRow(0);
			for (int i = 0; i < row.getLastCellNum(); i++) {
				//LOG.info(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equalsIgnoreCase(colName))
					colNum = i;
			}

			if (colNum == -1)
				return false;
			sheet.autoSizeColumn(colNum); //ashish
			row = sheet.getRow(rowNum - 1);
			if (row == null)
				row = sheet.createRow(rowNum - 1);

			cell = row.getCell(colNum);
			if (cell == null)
				cell = row.createCell(colNum);

			cell.setCellValue(data);
			XSSFCreationHelper createHelper = workbook.getCreationHelper();

			//cell style for hyperlinks
			//by default hypelrinks are blue and underlined
			CellStyle hlink_style = workbook.createCellStyle();
			XSSFFont hlink_font = workbook.createFont();
			hlink_font.setUnderline(XSSFFont.U_SINGLE);
			hlink_font.setColor(IndexedColors.BLUE.getIndex());
			hlink_style.setFont(hlink_font);
			//hlink_style.setWrapText(true);

			XSSFHyperlink link = createHelper.createHyperlink(XSSFHyperlink.LINK_FILE);
			link.setAddress(url);
			cell.setHyperlink(link);
			cell.setCellStyle(hlink_style);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);

			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if sheet is created successfully else false
	public boolean addSheet(String sheetname) {

		FileOutputStream fileOut;
		try {
			workbook.createSheet(sheetname);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if sheet is removed successfully else false if sheet does not exist
	public boolean removeSheet(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return false;

		FileOutputStream fileOut;
		try {
			workbook.removeSheetAt(index);
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// returns true if column is created successfully
	public boolean addColumn(String sheetName, String colName) {
		//LOG.info("**************addColumn*********************");

		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return false;

			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

			sheet = workbook.getSheetAt(index);

			row = sheet.getRow(0);
			if (row == null)
				row = sheet.createRow(0);

			//cell = row.getCell();
			//if (cell == null)
			//LOG.info(row.getLastCellNum());
			if (row.getLastCellNum() == -1)
				cell = row.createCell(0);
			else
				cell = row.createCell(row.getLastCellNum());

			cell.setCellValue(colName);
			cell.setCellStyle(style);

			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	// removes a column and all the contents
	public boolean removeColumn(String sheetName, int colNum) {
		try {
			if (!isSheetExist(sheetName))
				return false;
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheet(sheetName);
			XSSFCellStyle style = workbook.createCellStyle();
			style.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
			XSSFCreationHelper createHelper = workbook.getCreationHelper();
			style.setFillPattern(HSSFCellStyle.NO_FILL);

			for (int i = 0; i < getRowCount(sheetName); i++) {
				row = sheet.getRow(i);
				if (row != null) {
					cell = row.getCell(colNum);
					if (cell != null) {
						cell.setCellStyle(style);
						row.removeCell(cell);
					}
				}
			}
			fileOut = new FileOutputStream(path);
			workbook.write(fileOut);
			fileOut.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// find whether sheets exists
	public boolean isSheetExist(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1) {
			index = workbook.getSheetIndex(sheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		} else
			return true;
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);

		if (row == null)
			return -1;

		return row.getLastCellNum();
	}

	// returns number of columns in a sheet
	public int getColumnCount(String sheetName, int rowNumber) {
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;

		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(rowNumber);

		if (row == null)
			return -1;

		return row.getLastCellNum();
	}

	//String sheetName, String testCaseName,String keyword ,String URL,String message
	public boolean addHyperLink(String sheetName, String screenShotColName, String testCaseName, int index, String url, String message) {
		//LOG.info("ADDING addHyperLink******************");

		url = url.replace('\\', '/');
		if (!isSheetExist(sheetName))
			return false;

		sheet = workbook.getSheet(sheetName);

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, 0, i).equalsIgnoreCase(testCaseName)) {
				//LOG.info("**caught "+(i+index));
				setCellData(sheetName, screenShotColName, i + index, message, url);
				break;
			}
		}

		return true;
	}

	public int getCellRowNum(String sheetName, String colName, String cellValue) {

		for (int i = 2; i <= getRowCount(sheetName); i++) {
			if (getCellData(sheetName, colName, i).equalsIgnoreCase(cellValue)) {
				return i;
			}
		}
		return -1;
	}

	public String getOrientation(String sheetName) throws Exception {
		String orientationStatus="";
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

		if(sheet.getPrintSetup().getOrientation().compareTo(PrintOrientation.LANDSCAPE)==0)
			orientationStatus="LANDSCAPE";
		else if(sheet.getPrintSetup().getOrientation().compareTo(PrintOrientation.PORTRAIT)==0)
			orientationStatus="PORTRAIT";

		return orientationStatus;

	}

	public boolean comparePageSize(String sheetName,String pageformat) throws Exception {
		boolean comparePageSizeFlag=false;
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);

		if(pageformat.equalsIgnoreCase("A4")) {
			if(sheet.getPrintSetup().getPaperSize() == XSSFPrintSetup.A4_PAPERSIZE)
				comparePageSizeFlag=true;
		}else if(pageformat.equalsIgnoreCase("Letter")) {
			if(sheet.getPrintSetup().getPaperSize() == XSSFPrintSetup.LETTER_PAPERSIZE)
				comparePageSizeFlag=true;
		}else if(pageformat.equalsIgnoreCase("Legal")) {
			if(sheet.getPrintSetup().getPaperSize() == XSSFPrintSetup.LEGAL_PAPERSIZE)
				comparePageSizeFlag=true;
		}
			return comparePageSizeFlag;

	}

	protected   void csvToXLSX(String csvFileAddress,String xlsxFileAddress) throws Exception {

			//String csvFileAddress = "test.csv"; //csv file address
			//String xlsxFileAddress = "test.xlsx"; //xlsx file address
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet("sheet1");
			String currentLine=null;
			int RowNum=0;
			BufferedReader br = new BufferedReader(new FileReader(csvFileAddress));
			while ((currentLine = br.readLine()) != null) {
				String str[] = currentLine.split(",");
				RowNum++;
				XSSFRow currentRow=sheet.createRow(RowNum);
				for(int i=0;i<str.length;i++){
					currentRow.createCell(i).setCellValue(str[i]);
				}
			}

			FileOutputStream fileOutputStream =  new FileOutputStream(xlsxFileAddress);
			workBook.write(fileOutputStream);
			fileOutputStream.close();


	}

	// to run this on stand alone
	public static void main(String arg[]) throws Exception {

		//LOG.info(filename);
		Xls_Reader datatable = null;

		//datatable = new Xls_Reader("H:\\Student_Selenium_Workspaces\\Framework_Weekend\\src\\Framework_XL_Files\\Controller.xlsx");
		datatable= new Xls_Reader("C:\\Users\\E003578\\Downloads\\showRoom.csv");//showRoom_A4_Portrait
		//datatable.csvToXLSX("C:\\Users\\E003578\\Downloads\\showRoom.csv","C:\\Users\\E003578\\Downloads\\showRoom_Converted.xlsx");
		datatable.getOrientation("showRoom");
		datatable.comparePageSize("Grid With Copy","Legal");
//		for (int col = 0; col < datatable.getColumnCount("TC5"); col++) {
//		}
	}
}
