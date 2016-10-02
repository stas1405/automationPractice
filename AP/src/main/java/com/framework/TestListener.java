package main.java.com.framework;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import test.java.com.autoPractice.TestBase;

public class TestListener extends TestListenerAdapter {

	/*
	 * Capture Screenshot, HTML Dumps on test failure
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		Object currentClass = result.getInstance();
		WebDriver driver = ((TestBase) currentClass).getDriver();
		captureScreenshotOnFailure(result.getMethod().getMethodName(), driver);
		storeHtmlDumps(result.getMethod().getMethodName(), driver);
	}

	@Override
	public void onTestStart(ITestResult result) {
		getLogger(result).info("********* Starting test case " + result.getName() + "*****");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	//	getLogger(result).info("Test '" + result.getName() + "'SKIPPED" + "*****");
	}

	/**
	 * This method capture the screenshot and save with the method Name
	 * 
	 * @param methodName
	 * @return
	 */
	private void captureScreenshotOnFailure(String methodName, WebDriver driver) {
		String directory = "test-output/Extent_Reports/screenshots/";
		try {
			new File(directory).mkdirs(); // Insure directory is there
			FileOutputStream out = new FileOutputStream(directory + methodName + ".png");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method stores the html dumps file
	 * 
	 * @param methodName
	 */
	public void storeHtmlDumps(String methodName, WebDriver driver) {
		String pagesource = driver.getPageSource();
		String directory = "test-output/Extent_Reports/HTML_Dumps/";
		String HTMLDUMPS = directory + methodName + ".html";
		try {
			FileUtils.writeStringToFile(new File(HTMLDUMPS), pagesource);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Logger getLogger(ITestResult result) {
		Object currentClass = result.getInstance();
		return ((TestBase) currentClass).getLogger();

	}
}