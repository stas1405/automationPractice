package main.resources;

import java.util.*;

import com.github.javaparser.ast.stmt.Statement;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;
import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import main.java.com.framework.TestSteps;

import java.io.File;

public class ExtentReporterNG implements IReporter {
	private ExtentReports extent;

	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		extent = new ExtentReports("test-output/Extent_Reports/Report/ExtentTestReport.html", true, DisplayOrder.OLDEST_FIRST);

		// Adding Test Environment Name to the report
		String env;
		if (System.getProperty("environment") == null || System.getProperty("environment").isEmpty())
			env = PropertiesReader.readProperty("default.env");
		else
			env = System.getProperty("environment");
		extent.addSystemInfo("Test Environment",env.toUpperCase());

		// Adding Browser Name to the report
		String browserName;
		if (System.getProperty("browser") == null || System.getProperty("browser").isEmpty())
			browserName=PropertiesReader.readProperty("default.browser");
		else
			browserName=System.getProperty("browser");
		extent.addSystemInfo("Browser",browserName);
		extent.loadConfig((new File("properties/extent-config.xml")));

		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();
			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
			}
		}
		for (String s : Reporter.getOutput()) {
			extent.setTestRunnerOutput(s);
		}
		extent.flush();
		extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status) {
		ExtentTest test;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				String testName = result.getMethod().getMethodName();
				test = extent.startTest(testName);
				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));
			//	test.assignCategory(result.getMethod().getXmlTest().getSuite().getName());
				 for (String group : result.getMethod().getGroups())
	                    test.assignCategory(group);
				test.setDescription(getSteps(testName, result.getMethod().getDescription()));
				if (result.getThrowable() != null) {
					test.log(status, "See screenshot for clarification: "
								+ test.addScreenCapture("../screenshots/" + result.getMethod().getMethodName() + ".png"));
						 test.log(status, result.getThrowable()); //full log
					//	 test.log(status, getLessStackTrace(result.getThrowable(), 1000)); // less trace		
						 test.log(status, "Please see HTML DUMP for clarification: " + "<a href=" + "../HTML_Dumps/"
								+ result.getMethod().getMethodName() + ".html" + "> HTML Dump file </a>");
				} else {
					test.log(status, "Test " + status.toString().toLowerCase() + "ed");
				}
				extent.endTest(test);
			}
		}
	}

	private String getSteps(String name, String desc){
		StringBuilder steps = new StringBuilder();
		if (desc != null){
			steps.append("<b>");
			steps.append("Test Description: ");
			steps.append("</b>");
			steps.append(desc);
			steps.append("</br>");
			steps.append("</br>");
		}
		HashMap<String, List<Statement>> testSteps = new TestSteps().getTestSteps();
		if (testSteps.containsKey(name)) {
			int count = 1;
			steps.append("<b>");
			steps.append("Test Steps: ");
			steps.append("</b><table>");
			for (Statement s : testSteps.get(name)){
				System.out.println(s.toString());
				String statement = s.toStringWithoutComments();
				String  comment ="";
				if (s.getComment() != null)
					 comment = s.getComment().toString();
				if (s.hasComment())
					steps.append("<tr><td>" + count++ + " : "  + statement + "</td><td><i>" + comment + "</i></td></tr>");
				else
					steps.append("<tr><td>" + count++ + " : "  + statement + "</td></tr>");
			}
			steps.append("</table></br>");
		}
		return steps.toString();
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

	protected String getLessStackTrace(Throwable e, int maxChars) {
		StringBuilder sb = new StringBuilder();
		sb.append(e.getClass().getName() + " " + e.getMessage() + "<br />");
		for (StackTraceElement element : e.getStackTrace()) {
			sb.append(element.toString() + "<br />");
		}
		return sb.toString().substring(0, maxChars);
	}
}