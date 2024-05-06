package testCases;


import com.aventstack.extentreports.ExtentTest;
import common.cmdSetup.FileParameters;
import driver.MobileDriverSetUp;
import extentTestNGSetup.ExtentSetup;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.PageManageActions;
import pages.nativePages.Calculator;

import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;

public class CalculatorTest extends ExtentSetup {
    private AppiumDriver appiumDriver;
    private ExtentTest test;

    private WebDriverWait webDriverWait;

    @BeforeMethod(alwaysRun = true)
    public void setUpNative() throws MalformedURLException {

        try {
            appiumDriver = new MobileDriverSetUp("android_12.txt").getDriver();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //df5d8e7a id emulator-5554
        //edson name sdk_gphone64_x86_64
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult iTestResult) throws IOException {
        getResult(appiumDriver,test,iTestResult);
    }

    @Test(groups = {"calculator"})
    public void calculatorTest() {
        test = extentReports.createTest("Calculator Application");
        Calculator calculator = new Calculator(appiumDriver,test);
        test.info("Application Started");
        calculator.clickOnDigitOne();
        calculator.clickOnAddButton();
        calculator.clickOnDigitOne();
        calculator.clickOnEqualsButton();
        //com.google.android.calculator:id/result_final
        calculator.waitThread(2);
        String result = calculator.getResult();

        Assert.assertEquals(Integer.parseInt(result), 1 + 1);
        test.pass("Test pass successfully The Actual result is: " + result + " and Expected Result is: " + (1 + 1));
    }
}
