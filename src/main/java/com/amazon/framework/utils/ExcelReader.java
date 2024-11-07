package com.amazon.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelReader {

    private Workbook workbook;

    public ExcelReader(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getRowData(String sheetName, String testCaseName) {
        Map<String, String> data = new HashMap<>();
        Sheet sheet = workbook.getSheet(sheetName);

        for (Row row : sheet) {
            if (row.getCell(0).getStringCellValue().equals(testCaseName)) {
                for (Cell cell : row) {
                    int columnIndex = cell.getColumnIndex();
                    String header = sheet.getRow(0).getCell(columnIndex).getStringCellValue();
                    String value = cell.getStringCellValue();
                    data.put(header, value);
                }
                break;
            }
        }
        return data;
    }

    public void close() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
