package test.java.com.autoPractice;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import main.java.com.framework.AppManager;
import main.java.com.framework.TestListener;
import main.java.com.framework.TestSteps;
import main.java.com.libraries.Navigation;
import main.java.com.resources.ExtentReporterNG;


@Listeners({TestListener.class, ExtentReporterNG.class})
public class TestBase{

	protected AppManager app;
	protected Navigation goTo;

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		app = new AppManager();
		app.logInfo("^^^^^^^^^^^^^^^^ Test setUp ^^^^^^^^^^^^^^^^");
		app.getBrowser().navigateTo();
		goTo = app.BasePage().goTo();
		
		new TestSteps().setTestSteps(this.getClass().getName());
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		app.logToExtentReport();
	//	app.getBrowser().closeBrowser();
		app.logInfo("^^^^^^^^^^^^^^^^ Test tearDown Complete ^^^^^^^^^^^^^^^^");
	}
	
	//Driver Instance for test listener
	public WebDriver getDriver(){
		return app.getBrowser().getDriver();
	}
	
	public Logger getLogger(){
		return app.getLog();
	}
	}