package pages;

import com.aventstack.extentreports.ExtentTest;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageManageActions {

    protected AppiumDriver driver;
    protected ExtentTest test;
    private WebDriverWait webDriverWait;
    private JavascriptExecutor js;
    private Actions actions;

    public PageManageActions(AppiumDriver driver, ExtentTest test) {
        this.driver = driver;
        this.test = test;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(60));
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);

    }

    public void waitUntilElementIsClickableAndClick(WebElement element, String elementDescription) {
        try {
            //test.info("waiting until " + elementDescription + "is visible");
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            //test.pass("clicked on Menu button");
        } catch (NoSuchElementException ex) {
            test.fail(elementDescription + " Element not Located");
            throw ex;
        } catch (TimeoutException ex) {
            test.fail("Time to locate " + elementDescription + " finished. " + elementDescription + " Element not Located");
            throw ex;
        } catch (StaleElementReferenceException ex) {
            test.fail(elementDescription + " Element not found on DOM");
            throw ex;
        } catch (ElementClickInterceptedException ex) {
            test.fail(elementDescription + " Element was intercepted by another element in the page");
            throw ex;
        }
    }

    public void scrollToElement(WebElement element, String elementDescription) {
        try {
            boolean exit = true;
            WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            while (exit) {
                try {
                    driverWait.until(ExpectedConditions.visibilityOf(element));
                    exit = false;
                } catch (Exception ex) {
                    js.executeScript("window.scrollBy(0,250)");
                }

            }
            //test.pass("scroll to "+ elementDescription + " element");
        } catch (NoSuchElementException ex) {
            test.fail(elementDescription + " Element not Located");
            throw ex;
        } catch (TimeoutException ex) {
            test.fail("Time to locate " + elementDescription + " finished. Search bar Element not Located");
            throw ex;
        } catch (StaleElementReferenceException ex) {
            test.fail(elementDescription + " Element not found on DOM");
            throw ex;
        }
    }

    public void typeIntoElement(WebElement element, String elementDescription, String text) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            element.sendKeys(text);
            //test.pass("type into "+ elementDescription + " element");
        } catch (NoSuchElementException ex) {
            test.fail(elementDescription + " Element not Located");
            throw ex;
        } catch (TimeoutException ex) {
            test.fail(" Time to locate" + elementDescription + " finished. Search bar Element not Located");
            throw ex;
        } catch (StaleElementReferenceException ex) {
            test.fail(elementDescription + " Element not found on DOM");
            throw ex;
        }
    }

    public void selectOptionByText(WebElement element, String elementDescription, String text) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            Select select = new Select(element);
            select.selectByVisibleText(text);
            //test.pass("Selected "+text+" option into "+ elementDescription + " element");
        } catch (NoSuchElementException ex) {
            test.fail(elementDescription + "Element not Located");
            throw ex;
        } catch (TimeoutException ex) {
            test.fail("Time to locate " + elementDescription + " finished. Search bar Element not Located");
            throw ex;
        } catch (StaleElementReferenceException ex) {
            test.fail(elementDescription + " Element not found on DOM");
            throw ex;
        }
    }

    public void selectOptionByValue(WebElement element, String elementDescription, String value) {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
            Select select = new Select(element);
            select.selectByValue(value);
            //test.pass("Selected "+value+" option into "+ elementDescription + " element");
        } catch (NoSuchElementException ex) {
            test.fail(elementDescription + " Element not Located");
            throw ex;
        } catch (TimeoutException ex) {
            test.fail("Time to locate " + elementDescription + " finished. Search bar Element not Located");
            throw ex;
        } catch (StaleElementReferenceException ex) {
            test.fail(elementDescription + " Element not found on DOM");
            throw ex;
        }
    }

    public String getElementText(WebElement element, String elementDescription) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOf(element));
            String elementText = element.getText();
            ;
            System.out.println("create element submit: " + elementText);
            //test.pass("Obtained "+ elementText +" text from "+ elementDescription + " element");
            return elementText;
        } catch (NoSuchElementException ex) {
            test.fail(elementDescription + " Element not Located");
            throw ex;
        } catch (TimeoutException ex) {
            test.fail("Time to locate " + elementDescription + " finished. Search bar Element not Located");
            throw ex;
        } catch (StaleElementReferenceException ex) {
            test.fail(elementDescription + " Element not found on DOM");
            throw ex;
        }
    }

    public String getElementTextJS(WebElement element, String elementDescription) {
        try {
            Thread.sleep(Duration.ofSeconds(5).toMillis());
            String elementText =  (String) js.executeScript("return arguments[0].innerText;", element);
            //test.pass("Obtained "+ elementText +" text from "+ elementDescription + " element");
            return elementText;
        } catch (NoSuchElementException ex) {
            test.fail(elementDescription + " Element not Located");
            throw ex;
        } catch (TimeoutException ex) {
            test.fail("Time to locate " + elementDescription + " finished. Search bar Element not Located");
            throw ex;
        } catch (StaleElementReferenceException ex) {
            test.fail(elementDescription + " Element not found on DOM");
            throw ex;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void wait(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
        //test.pass("waiting for "+ seconds +" seconds");
    }

    public void waitThread(int seconds) {
        try {
            Thread.sleep(Duration.ofSeconds(5).toMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //test.pass("waiting for "+ seconds +" seconds");
    }

    public void executeScript(String script) {
        js.executeScript(script);
    }

    public String executeScriptAndReturnText(String script) {
        return (String) js.executeScript("return " + script);
    }

    public void implicitWait(int secondsToWait) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secondsToWait));
        test.info("waiting for " + secondsToWait + " second/s");
    }

    public boolean elementByLocatorIsVisible(By locator){
        return elementByLocatorIsVisible(locator, 10);
    }

    private boolean elementByLocatorIsVisible(By locator, int secondsToWait) {
        try {
            WebDriverWait webDriverWait = new WebDriverWait(driver,Duration.ofSeconds(secondsToWait));
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public void scrollToCurrentElement(WebElement element) {
        actions.moveToElement(element);
        actions.build().perform();
    }

    public void dragAndDrop(WebElement currentElement,WebElement toElement) {
        actions.dragAndDrop(currentElement,toElement);
        actions.build().perform();
    }


}
