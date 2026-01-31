package restfulecommerce.tutorial.datadriven.exceldataprovider;

import static java.text.MessageFormat.format;
import static java.util.logging.Level.INFO;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDynamicReader {

    public static final Logger LOGGER = Logger.getLogger (ExcelDynamicReader.class.getName ());

    public static List<Map<String, Object>> readExcelAsTable (String filePath, String sheetName) {

        LOGGER.log (INFO, "Getting ready to read excel file....");
        List<Map<String, Object>> rows = new ArrayList<> ();

        InputStream inputStream = ExcelDynamicReader.class.getClassLoader ()
            .getResourceAsStream (filePath);
        if (inputStream == null) {
            throw new RuntimeException ("File not found:");
        }
        try (
            Workbook workbook = new XSSFWorkbook (inputStream)) {

            LOGGER.log (INFO, format ("Reading file {0}", filePath));

            Sheet sheet = workbook.getSheet (sheetName);

            LOGGER.log (INFO, format ("Reading Sheet {0}", sheetName));

            Iterator<Row> rowIterator = sheet.iterator ();
            List<String> headers = new ArrayList<> ();
            if (rowIterator.hasNext ()) {
                Row headerRow = rowIterator.next ();
                for (Cell cell : headerRow) {
                    headers.add (cell.getStringCellValue ());
                }
            }
            LOGGER.log (INFO, format ("Reading rows in the sheet {0}", sheetName));
            while (rowIterator.hasNext ()) {
                Row row = rowIterator.next ();
                Map<String, Object> rowData = new LinkedHashMap<> ();
                for (int i = 0; i < headers.size (); i++) {
                    Cell cell = row.getCell (i);
                    Object value = getCellValue (cell);
                    rowData.put (headers.get (i), value);
                }
                rows.add (rowData);
            }
        } catch (IOException e) {
            LOGGER.log (Level.SEVERE, format ("Error while reading excel file {0}", e));
        }
        return rows;
    }

    private static Object getCellValue (Cell cell) {
        if (cell == null)
            return null;

        return switch (cell.getCellType ()) {
            case STRING -> cell.getStringCellValue ();
            case BOOLEAN -> cell.getBooleanCellValue ();
            case NUMERIC -> cell.getNumericCellValue ();
            case FORMULA -> cell.getCellFormula ();
            case BLANK -> "";
            default -> null;
        };
    }

    public static <T> List<T> getData (List<Map<String, Object>> table, Class<T> pojoClass) {
        ObjectMapper objectMapper = new ObjectMapper ();
        List<T> result = new ArrayList<> ();
        LOGGER.log (INFO, format ("Getting the data from the excel file in table format"));
        for (Map<String, Object> row : table) {
            T obj = objectMapper.convertValue (row, pojoClass);
            result.add (obj);
        }
        return result;
    }
}