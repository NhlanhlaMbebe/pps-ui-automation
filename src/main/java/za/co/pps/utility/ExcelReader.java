package za.co.pps.utility;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ExcelReader {

    private FileOutputStream fileOut = null;
    private String path;
    private FileInputStream fis;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;

    public ExcelReader(String path) {
        this.path = path;

        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({"deprecation"})
    public String[][] getDataFromExcelSheet(String SheetName) {

        String[][] dataSet = null;

        try {
            sheet = workbook.getSheet(SheetName);
            int totalRow = sheet.getLastRowNum() + 1;
            int totalColumn = sheet.getRow(0).getLastCellNum();
            dataSet = new String[totalRow - 1][totalColumn];

            for (int i = 1; i < totalRow; i++) {
                row = sheet.getRow(i);

                for (int j = 0; j < totalColumn; j++) {
                    XSSFCell cell = row.getCell(j);

                    if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
                        dataSet[i - 1][j] = cell.getStringCellValue();

                    else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
                        dataSet[i - 1][j] = String.valueOf(cell.getNumericCellValue());

                    else
                        dataSet[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataSet;
    }

    @SuppressWarnings("deprecation")
    public String getCellData(String sheetName, String colName, int rowNum) {
        try {
            int colNum = 0;
            int index = workbook.getSheetIndex(sheetName);
            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(0);

            for (int i = 0; i < row.getLastCellNum(); i++) {
                if (row.getCell(i).getStringCellValue().equalsIgnoreCase(colName)) {
                    colNum = i;
                    break;
                }
            }
            row = sheet.getRow(rowNum - 1);
            XSSFCell cell = row.getCell(colNum);

            if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
                return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
