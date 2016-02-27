package com.bap.erp.commons.utils;

import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class ImportUtils {

    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImportUtils.class);

    public static void main(String[] args) {
        try {
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int lookForRowWithValue(Sheet sheet, String term) {       //Iterate through each rows one by one
        Iterator<Row> rowIterator = sheet.iterator();
        boolean found = false;
        Cell cell = null;
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                if (cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals(term)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
        }
        if (cell != null) {
            return cell.getRowIndex();
        } else {
            return -1;
        }

    }

}
