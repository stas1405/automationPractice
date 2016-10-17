package main.java.com.framework;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import main.resources.PropertiesReader;

public class Browser {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }
    public String url;



	/**
     * Opens a particular browser based on the data in application.properties
     *
     * @return WebDriver driver reference
     */
    public void startBrowser() {

        String browserName;
        if (System.getProperty("browser") == null || System.getProperty("browser").isEmpty())
            browserName = PropertiesReader.readProperty("default.browser");
        else
            browserName = System.getProperty("browser");

/*        if (browserName.equalsIgnoreCase(
                "Firefox")) {
        	FirefoxProfile profile = new FirefoxProfile();
        	profile.setEnableNativeEvents(true);
            driver = new FirefoxDriver(profile);*/
        if (browserName
                .equalsIgnoreCase("Firefox")) {
            System.setProperty("webdriver.firefox.driver",
                    System.getProperty("user.dir")
                            + "\\browserDrivers\\firefoxdriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName
                .equalsIgnoreCase("Chrome")) {
            System.setProperty("webdriver.chrome.driver",
                    System.getProperty("user.dir")
                            + "\\browserDrivers\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName
                .equalsIgnoreCase("IE")) {
            System.setProperty("webdriver.ie.driver",
                    System.getProperty("user.dir")
                            + "\\browserDrivers\\IEDriverServer.exe");
            DesiredCapabilities capabilities = DesiredCapabilities
                    .internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
            capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
            capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
            capabilities.setCapability("ie.ensureCleanSession", true);
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            driver = new InternetExplorerDriver(capabilities);
        }
        //driver.manage().window().maximize();
        driver.manage().window().setSize(new Dimension(1280,780));
        driver.manage().window().setSize(new Dimension(1280,780));
    }

    /**
     * Navigating to the particular URL based on the data in application.properties
     */
    public void navigateTo() {
        String env;
        if (System.getProperty("environment") == null || System.getProperty("environment").isEmpty())
            env = PropertiesReader.readProperty("default.env");
        else
            env = System.getProperty("environment");
        url = PropertiesReader.readProperty(env.toLowerCase() + ".url");
        driver.get(url);
    }

    public void closeBrowser() {
        driver.quit();
    }

    public String getUrl() {
		return url;
	}
}
