package outdoortoyssearch;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

// Import Apache POI classes for working with Excel files
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

// Import Selenium WebDriver classes
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

// Import Google Guava classes (though Cell is not used in this code)
import com.google.common.collect.Table.Cell;

public class SetFormValues {

    WebDriver driver;

    // Locate the element using linkText
    @FindBy(linkText = "Advanced")
    WebElement advancedSearch_button;
    // Locate the keywords input field using its ID
    @FindBy(id="_nkw")
    WebElement txt_keywords;

    // Locate the keyword options dropdown using its ID
    @FindBy(id="s0-1-17-4[0]-7[1]-_in_kw")
    WebElement select_keyword_options;

    // Locate the category dropdown using its ID
    @FindBy(id="s0-1-17-4[0]-7[3]-_sacat")
    WebElement select_category;

    // Locate the 'new' checkbox using its ID
    @FindBy(id="s0-1-17-5[1]-[0]-LH_TitleDesc")
    WebElement checkbox_new;

    // Locate the 'new' input field using its ID
    @FindBy(id="s0-1-17-6[4]-[0]-LH_ItemCondition")
    WebElement input_new;

    // Locate the 'free returns' checkbox using its ID
    @FindBy(id="s0-1-17-5[5]-[0]-LH_FR")
    WebElement checkbox_Free_returns;

    // Locate the 'returns accepted' checkbox using its ID
    @FindBy(id="s0-1-17-5[5]-[1]-LH_RPA")
    WebElement checkbox_returns_accepted;

    // Locate the location input field using its ID
    @FindBy(id="s0-1-17-6[7]-[3]-LH_PrefLoc")
    WebElement input_location;

    // Locate the search button using XPath
    @FindBy(xpath="/html/body/div[2]/div/main/form/div[2]/button")
    WebElement button_search;

    // Constructor to initialize the WebDriver and PageFactory elements
    public SetFormValues(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to set the keywords in the search form
    public void setKeywords(String keywords) {
        txt_keywords.sendKeys(keywords);
    }

    // Method to select an option from the keyword options dropdown
    public void setKeywordOptions(String keywordOptions) {
        Select dropdown = new Select(select_keyword_options);
        dropdown.selectByVisibleText(keywordOptions);
    }

    // Method to select a category from the category dropdown
    public void setCategory(String category) {
        Select dropdown = new Select(select_category);
        dropdown.selectByVisibleText(category);
    }

    // Method to click the 'new' checkbox
    public void setSearchIncluding() {
        checkbox_new.click();
    }

    // Method to click the 'new' input field
    public void setCondition() {
        input_new.click();
    }

    // Method to click the 'free returns' checkbox
    public void setFreeReturns() {
        checkbox_Free_returns.click();
    }

    // Method to click the 'returns accepted' checkbox
    public void setReturnsAccepted() {
        checkbox_returns_accepted.click();
    }

    // Method to click the location input field
    public void setLocation() {
        input_location.click();
    }
}