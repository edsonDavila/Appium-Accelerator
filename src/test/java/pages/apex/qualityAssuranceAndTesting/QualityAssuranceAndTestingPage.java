package pages.apex.qualityAssuranceAndTesting;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageManageActions;
import pages.apex.qualityAssuranceAndTesting.locators.QualityAssuranceAndTestingLocators;

public class QualityAssuranceAndTestingPage extends PageManageActions {

    @FindBy(xpath = QualityAssuranceAndTestingLocators.connectWithUsLink)
    private WebElement connectWithUsLink;

    public QualityAssuranceAndTestingPage(AppiumDriver driver, ExtentTest test) {
        super(driver, test);
        PageFactory.initElements(driver,this);
    }

    public void scrollToConnectWithUsLink(){
        scrollToElement(connectWithUsLink,"Connect With Us Link");
    }

    public void clickOnConnectWithUsLink(){
        waitUntilElementIsClickableAndClick(connectWithUsLink,"Connect With Us Link");
    }
}
