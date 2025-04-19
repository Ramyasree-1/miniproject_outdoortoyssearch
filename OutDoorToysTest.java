package outdoortoyssearch;
//Import classes for handling file input/output and Excel files
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

//Import Apache POI classes for working with Excel files
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//Import Selenium WebDriver classes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//Import TestNG annotations for setting up and tearing down tests
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class OutDoorToysTest {
    WebDriver driver;
    TakeScreenshots obj;
    SetFormValues setValues;
    static String baseUrl="http://www.ebay.com";
    @Parameters("browser")
    @BeforeClass
    public void driverSetup(String browser) {
    	if(browser.equals("chrome"))
    	{
          // Initialize the ChromeDriver
          driver = new ChromeDriver();
    	}
    	else if(browser.equals("firefox"))
    	{
    		// Initialize the FirefoxDriver
            driver = new FirefoxDriver();
    	}
    	else if(browser.equals("edge"))
    	{
    		// Initialize the EdgeDriver
            driver = new EdgeDriver();
    	}
    	else
    	{
    		return;
    	}
        // Set an implicit wait of 10 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Maximize the browser window
        driver.manage().window().maximize();
        // Minimize the browser window
        driver.manage().window().minimize();
        // Initialize the TakeScreenshots and SetFormValues objects
        obj = new TakeScreenshots(driver);
        setValues = new SetFormValues(driver);
    }

    @Test(priority = 1)
    public void openEbayWebsite() {
        // Open the eBay website
        driver.get(baseUrl);
        
        // Take a screenshot of the homepage
        obj.takeScreenshot("Homepage.png");
    }

    @Test(priority = 2, dependsOnMethods = "openEbayWebsite")
    public void openAdvancedSearch() {
        // Click on the advanced search button
        setValues.advancedSearch_button.click();
    }

    @Test(priority = 3, dependsOnMethods = "openAdvancedSearch")
    public void testFormData() throws IOException {
        // Read search details from an Excel file
        ExcelUtils r = new ExcelUtils();
        String[] searchDetails = r.readData();
        
        // Set the search form values
        setValues.setKeywords(searchDetails[0]);
        setValues.setKeywordOptions(searchDetails[1]);
        setValues.setCategory(searchDetails[2]);
        
        // Take a screenshot of the filled form
        obj.takeScreenshot("details.png");
        
        // Set additional search options
        setValues.setSearchIncluding();
        setValues.setCondition();
        setValues.setFreeReturns();
        setValues.setReturnsAccepted();
        setValues.setLocation();
    }

    @Test(priority = 4, dependsOnMethods = "testFormData")
    public void performSearch() throws InterruptedException {
        // Click the search button
        setValues.button_search.click();
        
        // Wait for 5 seconds to allow search results to load
        Thread.sleep(5000);
        
        // Take a screenshot of the search results
        obj.takeScreenshot("result.png");
    }

    @Test(priority = 5, dependsOnMethods = "performSearch")
    public void testSearchResults() throws Exception {
        // Create an Excel file to store the search results
        FileOutputStream file = new FileOutputStream(System.getProperty("user.dir") + "\\testdata\\output.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data");
        
        // Find all product links on the search results page
        List<WebElement> productLinks = driver.findElements(By.cssSelector(".s-item__link"));
        
        // Create header row in the Excel sheet
        XSSFRow headerRow = sheet.createRow(0);
        XSSFCell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Toy Name");
        XSSFCell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Toy URL");
        
        int rowNum = 1;
        for (WebElement link : productLinks) {
            String toyName = link.getText();
            String toyURL = link.getAttribute("href");
            
            // Verify if the name contains 'toys'
            if (toyName.toLowerCase().contains("toys")) {
                System.out.println(toyName + " -> " + toyURL);
                
                // Write the toy name and URL to the Excel sheet
                XSSFRow row = sheet.createRow(rowNum++);
                XSSFCell cell1 = row.createCell(0);
                cell1.setCellValue(toyName);
                XSSFCell cell2 = row.createCell(1);
                cell2.setCellValue(toyURL);
            }
        }
        
        // Save and close the Excel file
        workbook.write(file);
        workbook.close();
    }

    @AfterClass
    public void driverClose() {
        // Close the browser
        driver.quit();
    }
}