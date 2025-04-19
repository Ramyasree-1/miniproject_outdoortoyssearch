package outdoortoyssearch;

// Import TestNG interfaces for listening to test events
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

// Import ExtentReports classes for generating test reports
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;  // UI of the report
    public ExtentReports extent;  // Populate common info on the report
    public ExtentTest test; // Create test case entries in the report and update status of the test methods

    // This method is invoked before any test starts
    public void onStart(ITestContext context) {
        // Specify the location of the report
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
        
        // Set the title of the report
        sparkReporter.config().setDocumentTitle("Automation Report");
        
        // Set the name of the report
        sparkReporter.config().setReportName("Functional Testing");
        
        // Set the theme of the report
        sparkReporter.config().setTheme(Theme.STANDARD);
        
        // Initialize ExtentReports and attach the reporter
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        
        // Set system information for the report
        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Tester Name", "Pavan");
        extent.setSystemInfo("OS", "Windows10");
        extent.setSystemInfo("Browser Name", "Chrome");
    }

    // This method is invoked when a test case passes
    public void onTestSuccess(ITestResult result) {
        // Create a new entry in the report for the passed test case
        test = extent.createTest(result.getName());
        
        // Update the status of the test case to PASS
        test.log(Status.PASS, "Test case PASSED is: " + result.getName());
    }

    // This method is invoked when a test case fails
    public void onTestFailure(ITestResult result) {
        // Create a new entry in the report for the failed test case
        test = extent.createTest(result.getName());
        
        // Update the status of the test case to FAIL
        test.log(Status.FAIL, "Test case FAILED is: " + result.getName());
        
        // Log the cause of the failure
        test.log(Status.FAIL, "Test Case FAILED cause is: " + result.getThrowable());
    }

    // This method is invoked when a test case is skipped
    public void onTestSkipped(ITestResult result) {
        // Create a new entry in the report for the skipped test case
        test = extent.createTest(result.getName());
        
        // Update the status of the test case to SKIP
        test.log(Status.SKIP, "Test case SKIPPED is: " + result.getName());
    }

    // This method is invoked after all the tests have run
    public void onFinish(ITestContext context) {
        // Write everything to the report
        extent.flush();
        
    }
}