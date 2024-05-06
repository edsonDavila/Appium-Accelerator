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
import pages.apex.contactUs.ContactUsPage;
import pages.apex.qualityAssuranceAndTesting.QualityAssuranceAndTestingPage;
import testCases.apex.data.Data;

import java.io.IOException;
import java.net.MalformedURLException;

public class QATesting_Tests extends ExtentSetup {
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
    public void openPage_ContactForm_SendMessage() throws InterruptedException {
        test = extentReports.createTest("WhatWeDo QATesting ContactForm");
        test.info("navigating to " + Data.url);
        appiumDriver.navigate().to(Data.url);
        test.pass("Navigated successfully");

        CommonElements apexCommonElements = new CommonElements(appiumDriver,test);
        apexCommonElements.wait(20);
        apexCommonElements.clickOnAcceptCookiesAlert();
        apexCommonElements.clickOnNavBarMenuButton();

        NavBar navBar = new NavBar(appiumDriver,test);
        navBar.clickOnWhatWeDoExpandButton();
        navBar.clickOnQualityAssuranceAndTestingLink();

        apexCommonElements.wait(10);
        QualityAssuranceAndTestingPage qualityAssuranceAndTestingPage = new QualityAssuranceAndTestingPage(appiumDriver,test);
        qualityAssuranceAndTestingPage.scrollToConnectWithUsLink();
        qualityAssuranceAndTestingPage.clickOnConnectWithUsLink();

        ContactUsPage contactUsPage = new ContactUsPage(appiumDriver,test);
        apexCommonElements.wait(10);
        Thread.sleep(2000);
        Assert.assertEquals(contactUsPage.getPageName(),"Let's Connect");
        contactUsPage.typeFullName(Data.fullname);
        contactUsPage.selectContactReasonByText(Data.contactReason);
        contactUsPage.typeEmail(Data.email);
        contactUsPage.selectLocationByText(Data.location);
        contactUsPage.typeSubject(Data.messagesubject);
        contactUsPage.typeMessage(Data.message);

        test.pass("Information typed successfully");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) throws IOException {
        getResult(appiumDriver,test,iTestResult);
    }
}
