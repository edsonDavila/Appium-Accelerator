package testCases.apex;

import com.aventstack.extentreports.ExtentTest;
import common.cmdSetup.EnvMap;
import common.cmdSetup.FileParameters;
import driver.MobileDriverSetUp;
import extentTestNGSetup.ExtentSetup;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import testCases.apex.data.Data;

import java.io.IOException;
import java.net.MalformedURLException;

public class HomePage_Tests extends ExtentSetup {
    private AppiumDriver appiumDriver;
    private ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() throws MalformedURLException {
        try {
            appiumDriver =  new MobileDriverSetUp("android_web_12.txt").getDriver();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"LandingPage"},priority = 1)
    public void openPage_Home_Page(){
        test = extentReports.createTest("Navigate to Home Page");
        test.info("navigating to " + Data.url);
        appiumDriver.navigate().to(Data.url);
        test.pass("Navigated successfully");
    }

    @Test(groups = {"LandingPage"},priority = 2)
    public void Home_Page_Verify_Title(){
        test = extentReports.createTest("Verify Home Page Title");
        test.info("navigating to " + Data.url);
        appiumDriver.navigate().to(Data.url);
        test.pass("Navigated succesfully");
        String expectedTitle= "demo";
        String actualTitle = appiumDriver.getTitle();
        Assert.assertEquals(actualTitle,expectedTitle,"Home page title is different");
        test.pass("The title is the same expected: "+ expectedTitle + "and Actual: "+actualTitle);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) throws IOException {
        getResult(appiumDriver,test,iTestResult);
    }
}
