package main.java.com.framework;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import main.resources.PropertiesReader;


public class AppManager {

	private Browser browser;
	private Logger log = LogManager.getLogger();
	private BasePage basepage;	
	
	public AppManager() {
		getBrowser().startBrowser();
		}
		
	public Browser getBrowser() {
		if (browser == null) {
			browser = new Browser();
		}
		return browser;
		}

	public BasePage BasePage() {
		if (basepage == null) {
			basepage = new BasePage(this);
		}
		return basepage;
	}
	
	public void logInfo(String logtext) {
		log.info("-------------------------------------------- " + logtext + " --------------");
	}

	public Logger getLog() {
		return log;
	}

	public void method(String method) {
		log.info("------------- " + method);
	}
	
	public void logClass(String clazz) {
		log.info("--- " + clazz + " ------------------------ ");
	}
	
	public void logFlow(String clazz) {
		log.info("***************** Executing keyword - " + clazz + "  *****************");
	}
	
	public void logExceptions(String error) {
		log.warn("!!!!!!!!!!!!!!!!!! Exception logged - " + error + " !!!!!!!!!!!!!! ");
	}

	public void logToExtentReport() {
			try {
				Reporter.log(PropertiesReader.readFile("test-output/Extent_Reports/Logs/log.log"));
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
}
