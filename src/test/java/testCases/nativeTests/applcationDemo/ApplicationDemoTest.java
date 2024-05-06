package testCases.nativeTests.applcationDemo;

import com.aventstack.extentreports.ExtentTest;
import common.cmdSetup.FileParameters;
import driver.MobileDriverSetUp;
import extentTestNGSetup.ExtentSetup;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.nativePages.ApplicationDemo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

public class ApplicationDemoTest extends ExtentSetup {
    private AppiumDriver appiumDriver;
    private ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public void setUpNativeUploaded() throws MalformedURLException {
        try {
            appiumDriver =  new MobileDriverSetUp("android_native_uploaded_apk_12.txt").getDriver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test(groups = {"native","uploaded_apk"})
    public void apiDemosSearch(){
        test = extentReports.createTest("Application demo");
        ApplicationDemo applicationDemo= new ApplicationDemo(appiumDriver,test);
        applicationDemo.clickOnAcceptButton();
        applicationDemo.clickOnAddButton();
        applicationDemo.wait(2);
        applicationDemo.clickOnFlowBeginningButton();
        applicationDemo.typeOnTitleTextBox("Test");
        applicationDemo.clickOnSaveButton();
        applicationDemo.wait(2);
        applicationDemo.clickOnReturnButton();
        applicationDemo.wait(3);
        applicationDemo.clickOnReturnButton();
        applicationDemo.wait(1);
        applicationDemo.clickOnUntitledOption();
        applicationDemo.clickOnTitleLabel();
        Random num = new Random(100);
        applicationDemo.typeOnTitleTextBoxAlert("TestRandom" + num.nextInt());
        applicationDemo.clickOnSaveButtonAlert();
        test.pass("Test pass");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) throws IOException {
        getResult(appiumDriver,test,iTestResult);
    }
}
