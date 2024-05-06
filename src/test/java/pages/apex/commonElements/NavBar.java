package pages.apex.commonElements;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageManageActions;
import pages.apex.commonElements.locators.NavBarLocators;

public class NavBar extends PageManageActions {
    @FindBy(xpath = NavBarLocators.whatWeDoExpandButton)
    WebElement whatWeDoExpandButton;
    @FindBy(xpath = NavBarLocators.qualityAssuranceAndTestingLink)
    WebElement qualityAssuranceAndTestingLink;
    @FindBy(xpath = NavBarLocators.careersExpandButton)
    WebElement careersExpandButton;
    @FindBy(xpath = NavBarLocators.searchJobsLink)
    WebElement searchJobsLink;


    public NavBar(AppiumDriver driver, ExtentTest test) {
        super(driver, test);
        PageFactory.initElements(driver,this);
    }

    public void clickOnWhatWeDoExpandButton() {
        waitUntilElementIsClickableAndClick(whatWeDoExpandButton, "What we do Expand Button");
    }

    public void clickOnQualityAssuranceAndTestingLink() {
        waitUntilElementIsClickableAndClick(qualityAssuranceAndTestingLink, "Quality Assurance And Testing Link");
    }

    public void clickOnCareersExpandButton() {
        waitUntilElementIsClickableAndClick(careersExpandButton, "Careers Expand Button");
    }

    public void clickOnSearchJobsLink() {
        waitUntilElementIsClickableAndClick(searchJobsLink, "Search Jobs Link");
    }

}
