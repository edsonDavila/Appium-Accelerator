package pages.apex.contactUs;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageManageActions;
import pages.apex.contactUs.locators.ContactUsLocators;

public class ContactUsPage extends PageManageActions {
    @FindBy(xpath = ContactUsLocators.fullNameInput)
    WebElement fullNameInput;
    @FindBy(xpath = ContactUsLocators.contactReasonSelect)
    WebElement contactReasonSelect;
    @FindBy(xpath = ContactUsLocators.emailInput)
    WebElement emailInput;
    @FindBy(xpath = ContactUsLocators.locationAreaSelect)
    WebElement locationAreaSelect;
    @FindBy(xpath = ContactUsLocators.subjectInput)
    WebElement subjectInput;
    @FindBy(xpath = ContactUsLocators.messageTextArea)
    WebElement messageTextArea;
    @FindBy(xpath = ContactUsLocators.letsConnectTitle)
    WebElement letsConnectTitle;

    public ContactUsPage(AppiumDriver driver, ExtentTest test) {
        super(driver, test);
        PageFactory.initElements(driver,this);
    }

    public void typeFullName(String fullName) {
        typeIntoElement(fullNameInput, "Full Name Input", fullName);
    }

    public void selectContactReasonByText(String reasonText) {
        selectOptionByText(contactReasonSelect, "Contact Reason Select", reasonText);
    }

    public void selectContactReasonByValue(String reasonValue) {
        selectOptionByText(contactReasonSelect, "Contact Reason Select", reasonValue);
    }

    public void typeEmail(String email) {
        typeIntoElement(emailInput, "Email Input", email);
    }

    public void selectLocationByText(String locationText) {
        selectOptionByText(locationAreaSelect, "Location Area Select", locationText);
    }

    public void selectLocationByValue(String locationValue) {
        selectOptionByValue(locationAreaSelect, "Location Area Select", locationValue);
    }

    public void typeSubject(String subject) {
        typeIntoElement(subjectInput, "Subject Input", subject);
    }

    public void typeMessage(String message) {
        typeIntoElement(subjectInput, "Message Text area", message);
    }

    public String getPageName() {
        return getElementText(letsConnectTitle,"Let's Connect Page Name");
    }
}
