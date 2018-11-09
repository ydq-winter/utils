package org.ydq.utils.excel2Sql;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.ydq.utils.tool.Validator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelLoader {

    @Value("${excel.path}")
    private String excelPath;

    @Value("${excel.skipRows}")
    private String skipRows;

    public List<String> loadExcel(String[] definitions) {
        File path = new File(excelPath);
        List<String> sqls = new ArrayList<>();
        if (path.exists()) {
            try {
                if (path.isDirectory()) {
                    File[] files = path.listFiles();
                    for (File file : files) {
                        Workbook workbook = WorkbookFactory.create(file);
                        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                            Sheet sheet = workbook.getSheetAt(i);
                            for (int index = Integer.parseInt(skipRows); index < sheet.getLastRowNum(); index++) {
                                Row row = sheet.getRow(index);
                                StringBuffer buffer = new StringBuffer();
                                for (int j = 0; j < definitions.length; j++) {
                                    if (Validator.isInteger(definitions[j])) {
                                        int cellIndex = Integer.parseInt(definitions[j]);
                                        Cell cell = row.getCell(cellIndex);
                                        CellType type = cell.getCellType();
                                        if (type.equals(CellType.NUMERIC)) {
                                            double value = cell.getNumericCellValue();
                                            buffer.append(value);
                                        }
                                        if (type.equals(CellType.STRING)) {
                                            String value = cell.getStringCellValue();
                                            buffer.append(value);
                                        }
                                    } else {
                                        buffer.append(definitions[j]);
                                    }
                                }
                                sqls.add(buffer.toString());
                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sqls;

    }

}
