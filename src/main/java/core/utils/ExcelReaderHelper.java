package core.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelReaderHelper {

    private XSSFWorkbook workbook;
    private XSSFSheet sheet;

    public ExcelReaderHelper(String excelPath) {
        try {
            File src = new File(excelPath);
            FileInputStream fileInputStream = new FileInputStream(src);
            workbook = new XSSFWorkbook(fileInputStream);

        } catch (Exception e) {
            Log.info(e.getMessage());
        }
    }

    public String getData(String sheetName, int row, int column) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }

    public String getDataByContent(String sheetName, String rowContent, int column) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getRow(findRow(rowContent)).getCell(column).getStringCellValue();
    }

    public String getDataByContent(String sheetName, String rowContent, String columnHeader) {
        sheet = workbook.getSheet(sheetName);
        return sheet.getRow(findRow(rowContent)).getCell(findColumn(columnHeader)).getStringCellValue();
    }

    public int getRowCount(String sheetName) {
        return workbook.getSheet(sheetName).getLastRowNum();
    }

    private int findRow(String cellContent) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getRichStringCellValue().getString().trim().equals(cellContent)) {
                    return row.getRowNum();
                }
            }
        }
        throw new RuntimeException("Cannot find Cell at Row that contains " + cellContent);
    }

    private int findColumn(String columnHeader) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getRichStringCellValue().getString().trim().equals(columnHeader)) {
                    return cell.getColumnIndex();
                }
            }
        }
        throw new RuntimeException("Cannot find Column that contains this " + columnHeader + " header");
    }
}
