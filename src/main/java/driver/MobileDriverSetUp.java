package driver;

import common.cmdSetup.CmdParameters;
import common.cmdSetup.FileParameters;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.HashMap;

public class MobileDriverSetUp {

    public String appiumUrl;
    public String chromeVersion;
    public String deviceName;
    public String deviceUdId;
    public String platformVersion;
    public String browserName;
    public String device;
    public String appPackage;
    public String app;
    public String appActivity;

    public AppiumDriver appiumDriver;
    public String apkLocation;
    public CmdParameters cmdParameters;
    private HashMap<String, String> params;

    public MobileDriverSetUp(String file) throws IOException {

        params = FileParameters.loadParameter(file);
        this.appiumUrl = params.get("appiumUrl");
        this.chromeVersion = params.getOrDefault("chromeVersion", "");
        this.deviceName = params.get("deviceName");
        this.deviceUdId = params.get("deviceUdId");
        this.platformVersion = params.get("platformVersion");
        this.browserName = params.getOrDefault("browserName", "");
        this.appPackage = params.getOrDefault("appPackage", "");
        this.app = params.getOrDefault("app", "");
        this.appActivity = params.getOrDefault("appActivity", "");
        this.apkLocation = params.getOrDefault("apkLocation", "");
        device = params.getOrDefault("device", "");
    }


    public MobileDriverSetUp(String appiumUrl, String chromeVersion, String deviceName, String deviceUdId, String platformVersion, String browserName, String device) {
        this.appiumUrl = appiumUrl;
        this.chromeVersion = chromeVersion;
        this.deviceName = deviceName;
        this.deviceUdId = deviceUdId;
        this.platformVersion = platformVersion;
        this.browserName = browserName;
        this.device = device;
    }

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
        }
        return null;
    }

    public DesiredCapabilities desiredCapabilities;

    public void setCapability(String key, String value) {
        if (desiredCapabilitiesIsNull())
            desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(key, value);
    }

    public void setCapability(String key, boolean value) {
        if (desiredCapabilitiesIsNull())
            desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(key, value);
    }

    protected boolean desiredCapabilitiesIsNull() {
        return desiredCapabilities == null;
    }

    public AndroidDriver AndroidNativeSetUp(String appiumUrlInstance, String deviceName, String deviceUdId, String platformVersion, String appUrl) throws MalformedURLException {
        String filePath = Paths.get(System.getProperty("user.dir"), appUrl).toFile().getPath();

        setCapability("app", filePath);
        setCapability("noReset ", true);
        setCapability("deviceName", deviceName);
        setCapability("udid", deviceUdId);
        setCapability("platformName", "Android");
        setCapability("platformVersion", platformVersion);
        setCapability("autoGrantPermissions", true);
        return new AndroidDriver(new URL(appiumUrlInstance), desiredCapabilities);
    }

    public AndroidDriver AndroidNativeSetUp(String appiumUrlInstance, String deviceName, String deviceUdId, String platformVersion, String appPackage, String appActivity) throws MalformedURLException {
        setCapability("appPackage", appPackage);
        setCapability("appActivity", appActivity);
        setCapability("noReset ", true);
        setCapability("deviceName", deviceName);
        setCapability("udid", deviceUdId);
        setCapability("platformName", "Android");
        setCapability("platformVersion", platformVersion);
        return new AndroidDriver(new URL(appiumUrlInstance), desiredCapabilities);
    }

    public AndroidDriver AndroidWebDriverSetUp(String appiumUrlInstance, String chromeBrowserVersion, String deviceName, String deviceUdId, String platformVersion) throws MalformedURLException {
        WebDriverManager wdm;
        wdm = WebDriverManager.chromedriver().browserVersion(chromeBrowserVersion);
        wdm.setup();
        String chromedriverPath = wdm.getDownloadedDriverPath();

        setCapability("platformName", "Android");
        setCapability("platformVersion", platformVersion);
        setCapability("version", chromeBrowserVersion);
        setCapability("deviceName", deviceName);
        setCapability("udid", deviceUdId);
        setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
        setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        setCapability("chromedriverExecutable", chromedriverPath);

        return new AndroidDriver(new URL(appiumUrlInstance), desiredCapabilities);
    }

    public IOSDriver iOSAppDriverSetUp(String appiumUrlInstance, String app, String deviceName, String deviceUdId, String platformVersion) throws MalformedURLException {
        setCapability("platformName", "iOS");
        setCapability("app", app);
        setCapability("platformVersion", platformVersion);
        setCapability("deviceName", deviceName);
        setCapability("udid", deviceUdId);
        setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        setCapability("appPackage", appPackage);
        setCapability("setForceSimulatorSoftwareKeyboardPresence", true);
        setCapability("setConnectHardwareKeyboard", true);

        return new IOSDriver(new URL(appiumUrlInstance), desiredCapabilities);
    }

    public IOSDriver iOSWebDriverSetUp(String appiumUrlInstance, String browser, String deviceName, String deviceUdId, String platformVersion) throws MalformedURLException {
        setCapability("platformName", "iOS");
        setCapability("platformVersion", platformVersion);
        setCapability("deviceName", deviceName);
        setCapability("udid", deviceUdId);
        setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        setCapability(MobileCapabilityType.BROWSER_NAME, browser);
        setCapability("setForceSimulatorSoftwareKeyboardPresence", true);
        setCapability("setConnectHardwareKeyboard", false);

        //perform a full phone reset deleting all the installed apps
        //setCapability(MobileCapabilityType.FULL_RESET, true);


        /*
        XCUITestOptions options = new XCUITestOptions();

        options.setCapability("platformName", "iOS");
        options.setCapability("platformVersion", platformVersion);
        options.setCapability("deviceName", deviceName);
        options.setCapability("udid", deviceUdId);
        options.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        options.setCapability(MobileCapabilityType.BROWSER_NAME, browser);
        options.safariAllowPopups();
        options.forceSimulatorSoftwareKeyboardPresence();
        */

        //options.setConnectHardwareKeyboard(true);


        //return new IOSDriver(new URL(appiumUrlInstance), desiredCapabilities);
        return new IOSDriver(new URL(appiumUrlInstance), desiredCapabilities);
    }

    public static DesiredCapabilities setDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        return desiredCapabilities;
    }
}
