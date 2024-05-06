package testCases.smartHq;

import com.aventstack.extentreports.ExtentTest;
import common.cmdSetup.FileParameters;
import driver.MobileDriverSetUp;
import extentTestNGSetup.ExtentSetup;
import io.appium.java_client.AppiumDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import testCases.apex.data.Data;

import java.io.IOException;
import java.net.MalformedURLException;

public class SmartHq_tests extends ExtentSetup {
    private AppiumDriver appiumDriver;
    private ExtentTest test;
    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() throws MalformedURLException {
        try {
            appiumDriver =  new MobileDriverSetUp("smarthq.txt").getDriver();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"test"},priority = 1)
    public void openPage_Home_Page() throws InterruptedException {
        test = extentReports.createTest("Navigate to Home Page");
        test.info("navigating to " + Data.url);
        Thread.sleep(5000);
    }

    @Test(groups = {"test2"},priority = 2, enabled = false)
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
}
