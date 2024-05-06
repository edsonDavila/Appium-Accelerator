package pages.google;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import pages.PageManageActions;

public class SearchResultsPage extends PageManageActions {
    public SearchResultsPage(AppiumDriver driver, ExtentTest test){
        super(driver,test);
    }

    public boolean pageByLinkTextIsDisplayed(String linkText){
        //By locator = By.linkText(linkText);
        By locator = By.xpath("//div[text() = '"+linkText+"' and @role='link']");
        return elementByLocatorIsVisible(locator);
    }
}
