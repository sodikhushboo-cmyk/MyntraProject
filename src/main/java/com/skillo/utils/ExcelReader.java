package com.skillo.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;


/** * ExcelReader ────────────── Reads data from an Excel file and returns it as a
 * 2D Object array.
 *
 * Usage: Object[][] data = ExcelReader.readExcel(0); // reads the first sheet
 *
 * This class uses Apache POI library to read .xlsx files. It handles several
 * common cell types and avoids resource leaks.
 */

public class ExcelReader {
    public static Object[][] readExcel(int sheetIndex) throws IOException {
        String path = "src/test/resources/TestData.xlsx";

        try (FileInputStream file = new FileInputStream(path);
             XSSFWorkbook book = new XSSFWorkbook(file)) {

            XSSFSheet sheet = book.getSheetAt(sheetIndex);
            if (sheet == null) {
                return new Object[0][0];
            }

            int rows = sheet.getPhysicalNumberOfRows();
            if (rows <= 1) { // only header or empty
                return new Object[0][0];
            }

            XSSFRow headerRow = sheet.getRow(0);
            int cols = headerRow == null ? 0 : headerRow.getPhysicalNumberOfCells();
            if (cols == 0) {
                return new Object[rows - 1][0];
            }

            Object[][] data = new Object[rows - 1][cols];

            for (int i = 1; i < rows; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < cols; j++) {
                    Object cellValue = null;
                    if (row != null) {
                        XSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            CellType type = cell.getCellType();
                            switch (type) {
                                case STRING:
                                    cellValue = cell.getStringCellValue();
                                    if (cellValue != null) cellValue = cellValue.toString().trim();
                                    break;
                                case NUMERIC:
                                    // keep as Double to preserve decimal values; tests can cast if needed
                                    cellValue = cell.getNumericCellValue();
                                    break;
                                case BOOLEAN:
                                    cellValue = cell.getBooleanCellValue();
                                    break;
                                case FORMULA:
                                    // evaluate formula result as string fallback
                                    try {
                                        cellValue = cell.getStringCellValue();
                                        if (cellValue != null) cellValue = cellValue.toString().trim();
                                    } catch (Exception e) {
                                        // fallback to numeric if string not available
                                        cellValue = cell.getNumericCellValue();
                                    }
                                    break;
                                case BLANK:
                                    cellValue = "";
                                    break;
                                default:
                                    cellValue = cell.toString();
                            }
                        } else {
                            cellValue = "";
                        }
                    } else {
                        cellValue = "";
                    }

                    data[i - 1][j] = cellValue;
                }
            }

            return data;
        }
    }

}