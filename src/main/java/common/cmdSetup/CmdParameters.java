package common.cmdSetup;

import common.cmdSetup.FileParameters;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class CmdParameters {
    public String device;
    public String deviceName;
    public String deviceUdId;
    public String platformVersion;
    public String appPackage;
    public String app;
    public String appActivity;
    public String chromeVersion;
    public String appiumUrlInstance;
    public String browserName;
    public String automationName;
    public String apkLocation;
    public String propertyFile;

    public CmdParameters() {
        device = System.getProperty("device");
        deviceName = System.getProperty("deviceName");
        deviceUdId = System.getProperty("deviceUdId");
        platformVersion = System.getProperty("platformVersion");
        appPackage = System.getProperty("appPackage");
        app = System.getProperty("app");
        appActivity = System.getProperty("appActivity");
        chromeVersion = System.getProperty("chromeVersion");
        appiumUrlInstance = System.getProperty("appiumUrl");
        browserName = System.getProperty("browserName");
        automationName = System.getProperty("automationName");
        apkLocation = System.getProperty("apkLocation");
        propertyFile = System.getProperty("propertyFile","android_web_12.txt");
    }
}
