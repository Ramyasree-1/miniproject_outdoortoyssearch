package outdoortoyssearch;
import java.io.File;
import java.io.IOException;

// Import Apache Commons IO classes for file operations
import org.apache.commons.io.FileUtils;

// Import Selenium WebDriver classes for taking screenshots
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TakeScreenshots {
    WebDriver driver;

    // Constructor to initialize the WebDriver
    public TakeScreenshots(WebDriver driver) {
        this.driver = driver;
    }

    // Method to take a screenshot and save it with the given file name
    public void takeScreenshot(String fileName) {
        try {
            // Capture the screenshot and store it as a file
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            
            // Specify the destination file path
            File destinationFile = new File("./screenshots/" + fileName + ".png");
            
            // Copy the screenshot to the destination file
            FileUtils.copyFile(screenshot, destinationFile);
            
            // Print the absolute path of the saved screenshot
            System.out.println("Screenshot saved at: " + destinationFile.getAbsolutePath());
        } catch (IOException e) {
            // Print an error message if an exception occurs
            System.out.println("Error while taking screenshot: " + e.getMessage());
        }
    }
}


