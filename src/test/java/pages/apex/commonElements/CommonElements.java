package pages.apex.commonElements;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageManageActions;
import pages.apex.commonElements.locators.CommonLocators;

public class CommonElements extends PageManageActions {
    @FindBy(xpath = CommonLocators.navBarButton)
    private WebElement navBarButton;
    @FindBy(xpath = CommonLocators.searchButton)
    private WebElement searchButton;
    @FindBy(id = CommonLocators.searchInput)
    private WebElement searchTextInput;
    @FindBy(id = CommonLocators.cookiesAlert)
    private WebElement cookiesAlert;

    public CommonElements(AppiumDriver appiumDriver, ExtentTest test) {
        super(appiumDriver, test);
        PageFactory.initElements(driver, this);
    }

    public void clickOnNavBarMenuButton() {
        waitUntilElementIsClickableAndClick(navBarButton, "Menu button");
    }

    public void clickOnAcceptCookiesAlert() {
        waitUntilElementIsClickableAndClick(cookiesAlert, "Cookies alert button");
    }
}
