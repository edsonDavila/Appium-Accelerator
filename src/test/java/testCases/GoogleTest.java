package testCases;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import common.cmdSetup.FileParameters;
import driver.MobileDriverSetUp;
import extentTestNGSetup.ExtentSetup;
import common.csvUtils.CsvUtils;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.google.HomePage;
import pages.google.SearchResultsPage;
import org.testng.Assert;
import testCases.google.data.Data;

import java.io.IOException;
import java.util.*;

public class GoogleTest extends ExtentSetup {
    private AppiumDriver appiumDriver;
    private ExtentTest test;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;
    private List<Map<String, String>> csvData;
    private CsvUtils csvUtils;


    @BeforeClass(alwaysRun = true)
    public void setup() throws IOException {
        csvUtils = new CsvUtils();
        csvData = csvUtils.getCsvData(csvUtils.getDir(Data.wordSearchPath, Data.wordSearchFile));
        System.out.println("Application Started");
    }

    @BeforeMethod(alwaysRun = true)
    public void setupTest() {

        try {
            appiumDriver = new MobileDriverSetUp("android_web_12.txt").getDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //df5d8e7a id emulator-5554
        //edson name sdk_gphone64_x86_64
        System.out.println("Application Started");
    }

    @DataProvider
    public Iterator<Object[]> getTestData() {
        ArrayList<Object[]> dataIteration = csvUtils.fillDataProviderIterations();
        return dataIteration.iterator();
    }

    @Test(dataProvider = "getTestData", groups = {"google"})
    public void testWebAppium(int iteration) {

        Map<String, String> currentRow = csvData.get(iteration);
        String textToSearch = currentRow.get("SearchText");
        String linkText = currentRow.get("LinkTextToValidate");
        test = extentReports.createTest("Test for web appium Iteration: " + (iteration + 1), "Input values: \n" +
                MarkupHelper.createOrderedList(Arrays.asList(new String[]{"searchText= " + textToSearch, "LinkTextToValidate= " + linkText})).getMarkup());
        homePage = new HomePage(appiumDriver, test);
        searchResultsPage = new SearchResultsPage(appiumDriver, test);

        appiumDriver.navigate().to("https://www.google.com");
        homePage.waitForPage(3);
        homePage.search(textToSearch);
        homePage.waitForPage(3);
        Assert.assertTrue(searchResultsPage.pageByLinkTextIsDisplayed(linkText), "Link with " + linkText + " link text not visible ");
        test.pass("Link with text: " + linkText + " was found");


    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) throws IOException {
        getResult(appiumDriver,test,iTestResult);
    }
}
