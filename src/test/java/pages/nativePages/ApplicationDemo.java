package pages.nativePages;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageManageActions;
import pages.nativePages.Locators.ApplicationDemoLocators;

public class ApplicationDemo extends PageManageActions {
    @FindBy(xpath = ApplicationDemoLocators.addButton)
    private WebElement addButton;
    @FindBy(id = ApplicationDemoLocators.flowBeginningButton)
    private WebElement flowBeginningButton;
    @FindBy(id = ApplicationDemoLocators.titleTextBox)
    private WebElement titleTextBox;
    @FindBy(id = ApplicationDemoLocators.saveButton)
    private WebElement saveButton;
    @FindBy(xpath = ApplicationDemoLocators.returnButton)
    private WebElement returnButton;
    @FindBy(xpath = ApplicationDemoLocators.untitledOption)
    private WebElement untitledOption;
    @FindBy(id = ApplicationDemoLocators.titleLabel)
    private WebElement titleLabel;
    @FindBy(xpath = ApplicationDemoLocators.titleTextBoxAlert)
    private WebElement titleTextBoxAlert;
    @FindBy(id = ApplicationDemoLocators.saveButtonAlert)
    private WebElement saveButtonAlert;
    @FindBy(id = ApplicationDemoLocators.acceptButton)
    private WebElement acceptButton;



    public ApplicationDemo(AppiumDriver driver, ExtentTest test) {
        super(driver, test);
        PageFactory.initElements(driver,this);
    }

    public void clickOnAddButton(){
        waitUntilElementIsClickableAndClick(addButton,"Add button");
    }

    public void clickOnFlowBeginningButton(){
        waitUntilElementIsClickableAndClick(flowBeginningButton,"Flow Beginning Button");
    }

    public void typeOnTitleTextBox(String text){
        typeIntoElement(titleTextBox,"Title Text Box",text);
    }

    public void clickOnSaveButton(){
        waitUntilElementIsClickableAndClick(saveButton,"Save Button");
    }

    public void clickOnReturnButton(){
        //returnButton = new WebElement(driver, ApplicationDemoLocators.returnButton.getLocatorBy(),ApplicationDemoLocators.returnButton.getLocatorValue());
        //waitUntilElementIsClickableAndClick(returnButton,"Return Button");
        driver.navigate().back();
    }

    public void clickOnUntitledOption(){
        waitUntilElementIsClickableAndClick(untitledOption,"Untitled Option");
    }

    public void clickOnTitleLabel(){
        waitUntilElementIsClickableAndClick(titleLabel,"title Label");
    }

    public String getTitleLabel(){
        return getElementText(titleLabel,"title Label");
    }

    public void typeOnTitleTextBoxAlert(String text){
        typeIntoElement(titleTextBoxAlert,"Title Text Box Alert",text);
    }

    public void clickOnSaveButtonAlert(){
        waitUntilElementIsClickableAndClick(saveButtonAlert,"Save Button Alert");
    }

    public void clickOnAcceptButton(){
        waitUntilElementIsClickableAndClick(saveButtonAlert,"Accept Button");
    }

}
