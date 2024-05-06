package testCases;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import common.cmdSetup.EnvMap;
import common.cmdSetup.FileParameters;
import common.csvUtils.CsvUtils;
import driver.MobileDriverSetUp;
import extentTestNGSetup.ExtentSetup;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.google.HomePage;
import pages.google.SearchResultsPage;
import testCases.google.data.Data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

public class AppTestIOSApp extends ExtentSetup {
    private AppiumDriver appiumDriver;
    private ExtentTest test;
    @BeforeMethod(alwaysRun = true)
    public void setUpDriver() throws MalformedURLException {
        try {
            appiumDriver =  new MobileDriverSetUp(EnvMap.env.get("propertyFile")).getDriver();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"ContactFrom"},priority = 1)
    public void testAppAppium() throws InterruptedException {
        appiumDriver.findElement(By.id("Buttons3")).click();
        appiumDriver.findElement(By.id("systemTextButton")).click();
        Thread.sleep(20000);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) throws IOException {
        getResult(appiumDriver,test,iTestResult);

    }
}
