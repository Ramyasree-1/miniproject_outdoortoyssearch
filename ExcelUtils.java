package outdoortoyssearch;
import java.io.FileInputStream;
import java.io.IOException;

// Import Apache POI classes for working with Excel files
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
    // Array to store search details
    String[] searchDetails = new String[3];

    // Method to read data from an Excel file
    public String[] readData() throws IOException {
        // Open the Excel file
        FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\testdata\\Myfile.xlsx");
        
        // Create a workbook instance that refers to the Excel file
        XSSFWorkbook wb = new XSSFWorkbook(file);
        
        // Get the sheet named "Data"
        XSSFSheet sheet = wb.getSheet("Data");
        
        // Get the first row of the sheet
        XSSFRow row = sheet.getRow(1);
        
        // Read data from the first three cells of the row and store them in the array
        searchDetails[0] = row.getCell(0).toString();
        searchDetails[1] = row.getCell(1).toString();
        searchDetails[2] = row.getCell(2).toString();
        
        // Close the workbook
        wb.close();
        
        // Return the array containing the search details
        return searchDetails;
    }
}