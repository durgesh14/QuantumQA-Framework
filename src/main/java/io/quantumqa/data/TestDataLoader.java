package io.quantumqa.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
USAGE:
Iterator<Object[]> testData = TestDataLoader.loadTestDataFromExcel("src/test/resources/testdata/testdata.xlsx", "Sheet1");

 */
public class TestDataLoader {

    public static Iterator<Object[]> loadTestDataFromExcel(String filePath, String sheetName) {
        List<Object[]> testCases = new ArrayList<>();

        try (FileInputStream file = new FileInputStream(new File(filePath))) {
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet(sheetName);
            Iterator<Row> rowIterator = sheet.iterator();

            // Skip header row
            if (rowIterator.hasNext()) {
                rowIterator.next();
            }

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<Object> rowData = new ArrayList<>();
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            rowData.add(cell.getBooleanCellValue());
                            break;
                        default:
                            rowData.add(null);
                    }
                }
                testCases.add(rowData.toArray());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load test data from Excel file: " + filePath, e);
        }

        return testCases.iterator();
    }
}