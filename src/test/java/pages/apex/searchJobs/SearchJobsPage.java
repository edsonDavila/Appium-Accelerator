package pages.apex.searchJobs;


import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageManageActions;
import pages.apex.searchJobs.locators.SearchJobsLocators;

import java.time.Duration;

public class SearchJobsPage extends PageManageActions {

    @FindBy(id = SearchJobsLocators.jobResultsTable)
    private WebElement jobResultsTable;
    @FindBy(xpath = SearchJobsLocators.jobOpeningsTitle)
    private WebElement jobOpeningsTitle;
    @FindBy(xpath = SearchJobsLocators.keywordInput)
    private WebElement keywordInput;
    @FindBy(id = SearchJobsLocators.locationInput)
    private WebElement locationInput;
    @FindBy(id = SearchJobsLocators.remoteInput)
    private WebElement remoteInput;
    @FindBy(id = SearchJobsLocators.searchJobsButton)
    private WebElement searchJobsButton;
    @FindBy(xpath = SearchJobsLocators.firstJobElementFromTable)
    private WebElement firstJobElementFromTable;


    public SearchJobsPage(AppiumDriver driver, ExtentTest test) {
        super(driver, test);
        PageFactory.initElements(driver,this);
       }

    public String getSearchJobsTitle() {
        waitThread(5);
        executeScript("el = document.querySelector('section > div > div > h1')");
        return executeScriptAndReturnText("el.innerText");
    }

    public void typeKeyword(String keyword) {
        typeIntoElement(keywordInput, "Keyword input", keyword);
    }

    public void typeLocation(String location) {
        typeIntoElement(locationInput, "Location input", location);
    }

    public void selectRemoteOptionByText(String optionText) {
        typeIntoElement(remoteInput, "Remote Select", optionText);
    }

    public void selectRemoteOptionByValue(String optionValue) {
        typeIntoElement(remoteInput, "Remote Select", optionValue);
    }

    public void clickOnSearchJobsButton() {
        waitUntilElementIsClickableAndClick(searchJobsButton, "Search Jobs Button");
    }

    public void scrollScreenToJobSearchResults() {
        scrollToElement(jobResultsTable, "Jobs Result Table");
    }

    public void clickOnFirstJobResult() {
        scrollToElement(firstJobElementFromTable, "First Job Element Result");
    }


}
