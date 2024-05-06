package pages.nativePages;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageManageActions;
import pages.nativePages.Locators.ApplicationDemoLocators;
import pages.nativePages.Locators.CalculatorLocators;

public class Calculator extends PageManageActions {
    @FindBy(id = CalculatorLocators.digitOne)
    private WebElement oneButton;
    @FindBy(id = CalculatorLocators.add)
    private WebElement addButton;
    @FindBy(id = CalculatorLocators.equals)
    private WebElement equalButton;
    @FindBy(id = CalculatorLocators.result)
    private WebElement resultText;

    public Calculator(AppiumDriver driver, ExtentTest test){
        super(driver,test);
        PageFactory.initElements(driver,this);
    }

    public void clickOnDigitOne(){
        waitUntilElementIsClickableAndClick(oneButton,"Digit One");
    }

    public void clickOnAddButton(){
        waitUntilElementIsClickableAndClick(addButton,"Add button");
    }

    public void clickOnEqualsButton(){
        waitUntilElementIsClickableAndClick(equalButton,"Equals button");
    }

    public String getResult(){
        return getElementText(resultText,"Result text");
    }

}
