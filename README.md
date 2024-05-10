# Appium Accelerator
This appium project is created to optimize the framework creation and starting working
on the Test cases

# How to create Test cases
 1. **Create a new device config .txt file on TestMobileParameters folder**
    ```
    -TestMobileParameters
     - new test parameters file
    ```

    device options:
    ```
    iphoneWeb - this support Saffari browser
    iphoneApp - this support Iphone app
    androidWeb - this support Chrome browser
    androidInstalledApp - this support a current android installed app
    androidUploadApp - this support upload android app
    ```
    data Example:
    ```
    device=androidUploadApp
    deviceName=Pixel2
    deviceUdId="your device udid"
    platformVersion=12
    apkLocation=apk/application.apk
    appiumUrl=http://localhost:4723/wd/hub
    ```
    For more examples click [here](https://github.com/edsonDavila/Appium-Accelerator/tree/main/TestMobileParameters) 
<br/>
<br/>
<br/> 
 2. **Create new Test case inherit from ExtentSetup.java class**

    Where create Test cases?
    ```
    -src
     - test
      - java
       - testCases 
    ```
    
    Example how to create Test case:
    ```
    public class GoogleTest extends ExtentSetup {
      private AppiumDriver appiumDriver;
      private ExtentTest test;
      @BeforeMethod(alwaysRun = true)
       public void setupTest() {
        try {
         appiumDriver = new MobileDriverSetUp("your_parameters_file_name.txt").getDriver();
        } catch (IOException e) {
         e.printStackTrace();
        }
      }
       
      @AfterMethod(alwaysRun = true)
      public void tearDown(ITestResult iTestResult) throws IOException {
       getResult(appiumDriver,test,iTestResult);
      }
       
      @Test
      public void firstTest(){
       test = extentReports.createTest("Calculator Application");// Create new Reporter test case
       ...
         Add test steps
       ...
       Assert.assertEquals(2, 1 + 1);
       test.pass("Test pass successfully The Actual result is: " + 2 + " and Expected Result is: " + (1 + 1));
      }
    }
    ```
 3. **Run your Test case as a testng test**
 4. **Open the report output from here:**
    ```
    - test-output
     - report.html
    ```
    
# How to add csv file data?
1. **Create a new csv file on CsvFiles folder**
    ```
    - CsvFiles
     - new test csv file
    ```
   For more examples click [here](https://github.com/edsonDavila/Appium-Accelerator/tree/main/CsvFiles)
   <br/>
   <br/>
   <br/>
2. **Create new Test case inherit from ExtentSetup.java class**

   Where create Test cases?
   ```
   -src
    - test
     - java
      - testCases 
   ```
   Example how to create Test case:
   ```
   public class GoogleTest extends ExtentSetup {
     private AppiumDriver appiumDriver;
     private ExtentTest test;
     private List<Map<String, String>> csvData; //create this List of hashmap
     private CsvUtils csvUtils; //create this CsvUtils object
   
     @BeforeClass(alwaysRun = true)// Create the intance of objects like this
     public void setup() throws IOException {
        csvUtils = new CsvUtils();
        csvData = csvUtils.getCsvData(csvUtils.getDir(Data.wordSearchPath, Data.wordSearchFile));
        System.out.println("Application Started");
     }
   
     @BeforeMethod(alwaysRun = true)
      public void setupTest() {
       try {
        appiumDriver = new MobileDriverSetUp("your_parameters_file_name.txt").getDriver();
       } catch (IOException e) {
        e.printStackTrace();
       }
     }
     
     @DataProvider // Add this method to get the iterations
     public Iterator<Object[]> getTestData() {
        ArrayList<Object[]> dataIteration = csvUtils.fillDataProviderIterations();
        return dataIteration.iterator();
     }
      
     @AfterMethod(alwaysRun = true)
     public void tearDown(ITestResult iTestResult) throws IOException {
      getResult(appiumDriver,test,iTestResult);
     }
      
     @Test(dataProvider = "getTestData") // call the method here
     public void firstTest(int iteration){ //add this object
      Map<String, String> currentRow = csvData.get(iteration);// add this new object that will receive the row
      String textToSearch = currentRow.get("SearchText");// add here the cell name and it will return the value
      String linkText = currentRow.get("LinkTextToValidate");// add here the cell name and it will return the value
      
      test = extentReports.createTest("Test case name");// Create new Reporter test case
      ...
        Add test steps
      ...
     }
   }
   ```
    Test example [here](https://github.com/edsonDavila/Appium-Accelerator/blob/main/src/test/java/testCases/GoogleTest.java)

# how to create new Views/Pages?
1. **Create a new page folder**
    ```
    - src
     - test
      - java
       - pages
        - your page name folder
    ```
2. **Create a new class inherit from PageManageActions class**
    ```
    public class HomePage extends PageManageActions {
    ```
3. **Add selenium annotations for elements and Page factory for constructor**
    ```
    public class HomePage extends PageManageActions {
     @FindBy(name = "your locator")
     private WebElement searchBar;
     @FindBy(xpath = "your locator")
     private WebElement formSearchBar;

     public HomePage(AppiumDriver driver, ExtentTest test) {
        super(driver,test);
        PageFactory.initElements(driver,this);
     }
    }
    ```
4. **Add methods using PageManageActions actions**
    ```
   public class HomePage extends PageManageActions {
     @FindBy(name = "your locator")
     private WebElement searchBar;
     @FindBy(xpath = "your locator")
     private WebElement formSearchBar;

     public HomePage(AppiumDriver driver, ExtentTest test) {
        super(driver,test);
        PageFactory.initElements(driver,this);
     }
    
     public void yourMethodName(String text) {
        typeIntoElement(yourElement, "Element name", text);
     }
   }
    ```
   Pages examples [here](https://github.com/edsonDavila/Appium-Accelerator/tree/main/src/test/java/pages)

# Where to add selenium actions?
1. **Open PageManageActions class**
    ```
    - src
     - test
      - java
       - pages
        - PageManageActions.java
    ```
2. **Create a new method and add the exception handler**
    ```
    public void methodName(WebElement element, String elementDescription) {
        try {
            test.info("waiting until " + elementDescription + " is visible");
            ... Element action steps
            test.pass("clicked on Menu button");
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
    ```
   PageManageActions [here](https://github.com/edsonDavila/Appium-Accelerator/blob/main/src/test/java/pages/PageManageActions.java)

# Where to add execution Setup
1. **Go to MobileDriverSetUp class**
    ```
    - src
     - main
      - java
       - driver
        - MobileDriverSetup.java
    ```
   
2. **Create a new method with your configuration**
    ```
     public AndroidDriver/IOSDriver yourConfigName(...add your parameters...) throws MalformedURLException {
        ...
        add your capabilities
        ...
        return new AndroidDriver/IOSDriver(new URL(appiumUrlInstance), desiredCapabilities);// you can change as you want
    }
    ```

3. **Add your execution method into the getDriver method switch**
    ```
      public AppiumDriver getDriver() throws MalformedURLException {
        System.out.println("device: " + device);
        switch (device) {
            case "iphoneWeb":
                System.out.println("Application Started iPhone");
                return iOSWebDriverSetUp(appiumUrl, browserName, deviceName, deviceUdId, platformVersion);
            case "iphoneApp":
                System.out.println("Application Started iPhone App");
                return iOSAppDriverSetUp(appiumUrl, app, deviceName, deviceUdId, platformVersion);
            case "androidWeb":
                System.out.println("Application Started Android");
                return AndroidWebDriverSetUp(appiumUrl, chromeVersion, deviceName, deviceUdId, platformVersion);
            case "androidInstalledApp":
                System.out.println("Application Started Android");
                return AndroidNativeSetUp(appiumUrl, deviceName, deviceUdId, platformVersion, appPackage, appActivity);
            case "androidUploadApp":
                System.out.println("Application Started Android");
                return AndroidNativeSetUp(appiumUrl, deviceName, deviceUdId, platformVersion, apkLocation);
            case "yourDeviceExecutionOption":
                System.out.println("Application Started deviceSO");
                return yourConfigName(...your method parameters);  
      }
        return null;
    }
    ```
   MobileDriverSetup.java [here](https://github.com/edsonDavila/Appium-Accelerator/blob/main/src/main/java/driver/MobileDriverSetUp.java)

# Parallel executions
1. **Create a testng xml file on resources folder**
    ```
    - src
     - test
      - resources
       - yourXmlFile.xml
    ```
2. **Add this format into the file:**
    ```
    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
    <suite name="Parallel Test Suite" parallel="methods" thread-count="2">
        <test name="Parallel Test" >
            <classes>
                <class name="Path from your test suite"/>
                <class name="Path from your test suite"/>
            </classes>
        </test> <!-- Test -->
    </suite> <!-- Suite -->
    ```
   PageManageActions [here](https://github.com/edsonDavila/Appium-Accelerator/blob/main/src/test/resources/testing.xml)

# Testng basic configs
1. **Disable tc**

    Add enable = false in the ***Test*** annotation 
    ```
     @Test(enabled = false)
     public void testWebAppium() {
    ```
2. **Add tags to test cases**
    Add groups={"tag name"} into the ***Test*** annotation
    ```
     @Test(groups = {"tag name 1","tag name 2"})
     public void testWebAppium() {
    ```
3. **Execute by tags**
   1. You must install maven in the local machine
   2. Once installed run the cmd into the project
   3. Execute command
    ```
    mvn test -Dgroups="tag1,tag2,tag3"
    ```
   You can run it on Intellij creating a new testng configuration run
   1. click on Run configurations
      ![testngGroups](/src/test/resources/runConfi.png)
   2. Click edit configurations
   3. Click on ***+*** and select testNG
   
      ![testngGroups](/src/test/resources/testRun.png)
   4. Test Kind chose groups  and add Group
      ![testngGroups](/src/test/resources/testngGroupsConfig.png)