package testCases.apex;

import com.aventstack.extentreports.ExtentTest;
import common.cmdSetup.EnvMap;
import common.cmdSetup.FileParameters;
import common.csvUtils.CsvUtils;
import driver.MobileDriverSetUp;
import extentTestNGSetup.ExtentSetup;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.apex.commonElements.CommonElements;
import pages.apex.commonElements.NavBar;
import pages.apex.searchJobs.SearchJobsPage;
import testCases.apex.data.Data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchJob_Tests extends ExtentSetup {
    private AppiumDriver appiumDriver;
    private ExtentTest test;
    private CsvUtils csvUtils;
    private List<Map<String, String>> csvData;


    @BeforeClass(alwaysRun = true)
    public void setUp() throws IOException {
        csvUtils = new CsvUtils();
        csvData = csvUtils.getCsvData(csvUtils.getDir(Data.jobSearchPath, Data.jobSearchFile));

    }

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() throws MalformedURLException {
        try {
            //FileParameters.loadParameter(EnvMap.env.get("propertyFile"));
            appiumDriver = new MobileDriverSetUp("android_web_12.txt").getDriver();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DataProvider
    public Iterator<Object[]> getTestData() {
        ArrayList<Object[]> dataIteration = csvUtils.fillDataProviderIterations();
        return dataIteration.iterator();
    }

    @Test(groups = {"SearchJobWithDataset"},priority = 1)
    public void openPage_SearchJobs_Verify_Page_Title(){
        test = extentReports.createTest("T001 Job Search Test");
        String title = "";
        String titleExpected = "Job Openings";
        test.info("navigating to " + Data.url);
        appiumDriver.navigate().to(Data.url);
        test.pass("Navigated successfully");
        CommonElements commonElements = new CommonElements(appiumDriver,test);
        //commonElements.wait(20);
        commonElements.clickOnAcceptCookiesAlert();
        commonElements.clickOnNavBarMenuButton();
        NavBar navBar = new NavBar(appiumDriver,test);
        navBar.clickOnCareersExpandButton();
        navBar.clickOnSearchJobsLink();
        SearchJobsPage searchJobsPage = new SearchJobsPage(appiumDriver,test);
        title = searchJobsPage.getSearchJobsTitle();
        Assert.assertEquals(title,titleExpected,"Titles are not equals");
        test.pass("Test pass. Title is correct");

    }

    @Test(groups = {"SearchJobWithDataset"},priority = 2,dataProvider = "getTestData", enabled = true)
    public void openPage_SearchJob_SearchByJobTitle_Usign_CSVData(int iteration) throws InterruptedException {
        Map<String, String> currentRow = csvData.get(iteration);
        String jobTitle = currentRow.get("JobTitle");
        test = extentReports.createTest("T002."+ (iteration+1) +" Job Search Test using CSV data");
        test.info("Search Job Title: "+ jobTitle);
        test.info("navigating to " + Data.url);
        appiumDriver.navigate().to(Data.url);
        test.pass("Navigated successfully");

        CommonElements commonElements = new CommonElements(appiumDriver,test);
        //commonElements.wait(20);
        commonElements.clickOnAcceptCookiesAlert();
        commonElements.clickOnNavBarMenuButton();
        NavBar navBar = new NavBar(appiumDriver,test);
        navBar.clickOnCareersExpandButton();
        navBar.clickOnSearchJobsLink();

        SearchJobsPage searchJobsPage = new SearchJobsPage(appiumDriver,test);
        searchJobsPage.typeKeyword(jobTitle);
        searchJobsPage.selectRemoteOptionByText("Yes");
        searchJobsPage.clickOnSearchJobsButton();
        searchJobsPage.scrollScreenToJobSearchResults();
        searchJobsPage.clickOnFirstJobResult();
        test.pass("Test pass");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) throws IOException {
        getResult(appiumDriver,test,iTestResult);
    }

}
