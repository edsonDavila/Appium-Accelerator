package pages.google;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.PageManageActions;
import pages.google.locators.Home;


public class HomePage extends PageManageActions {
    @FindBy(name = Home.searchBar)
    private WebElement searchBar;
    @FindBy(xpath = Home.formSearchBar)
    private WebElement formSearchBar;


    public HomePage(AppiumDriver driver, ExtentTest test) {
        super(driver,test);
        PageFactory.initElements(driver,this);
    }

    public void search(String text) {
        try {
            waitUntilElementIsClickableAndClick(searchBar,"Search Bar");
            Thread.sleep(2000);
            searchBar.sendKeys(text);
            Thread.sleep(2000);
            searchBar.sendKeys(Keys.ENTER);
            //Thread.sleep(1000);
            test.pass("type on search bar: " + text);

        } catch (NoSuchElementException ex) {
            test.fail("Search bar Element not Located\n");
            throw ex;
        } catch (TimeoutException ex) {
            test.fail("Time to locate Search bar finished. Search bar Element not Located\n");
            throw ex;
        } catch (StaleElementReferenceException ex) {
            test.fail("Search bar Element not found on DOM\n");
            throw ex;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitForPage(int seconds) {
        implicitWait(seconds);
    }
}
