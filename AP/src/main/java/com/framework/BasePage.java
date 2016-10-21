package main.java.com.framework;

import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import main.java.com.libraries.Navigation;
import main.java.com.pageObjects.APpages.AbstarctAutoPracticeBase;
import main.java.com.pageObjects.elements.Alerts;
import main.java.com.pageObjects.elements.Messages;
import main.java.com.pageObjects.elements.Waits;
import main.java.com.pageObjects.navigationPages.AbstractNavigation;


public class BasePage {
	
	protected WebDriver driver;
	protected AppManager app;
	private AbstarctAutoPracticeBase abstractApplicationPage;
	private AbstractNavigation navigation;
	private Navigation navigate;
	private Alerts alerts;
	private Waits waits;
		
	@FindBy(id="page")
	private Messages errors;
	
	@FindBy(className="alert")
	private WebElement error;

	public BasePage(AppManager manager) {
		this.driver = manager.getBrowser().getDriver();
		this.app = manager;
	}

	public Navigation goTo() {
		if (navigate == null) {
			navigate = new Navigation(app);
		}
		return navigate;
	}

	public Alerts alerts() {
		if (alerts == null) {
			alerts = new Alerts(app);
		}
		return alerts;
	}

	public Waits waits() {
		if (waits == null) {
			waits = new Waits(app);
		}
		return waits;
	}

	public AbstractNavigation NavigationPages() {
		if (navigation == null) {
			navigation = new AbstractNavigation(app);
		}
		return navigation;
	}
	
	public AbstarctAutoPracticeBase Application(){
		if (abstractApplicationPage == null) {
			abstractApplicationPage = new AbstarctAutoPracticeBase(app);
		}
		return abstractApplicationPage;
	}

	private org.openqa.selenium.support.ui.Select getSelect(WebElement element) {
		return new org.openqa.selenium.support.ui.Select(element);
	}

	public void selectByVisibleText(WebElement element, String data) {
		app.method("SELECTING BY VISIBLE TEXT --- " + element + " = " + data);
		getSelect(element).selectByVisibleText(data);
	}

	public void selectByIndex(WebElement element, int index) {
		app.method("SELECTING BY INDEX --- " + element + " = " + index);
		getSelect(element).selectByIndex(index);
	}
	
	public void selectRandomly(WebElement element) {
		app.method("SELECTING BY Random index --- " + element);
		List<WebElement> list = element.findElements(By.xpath("..//option"));
		getSelect(element).selectByIndex(new Random().nextInt(list.size()));
	}

	public void selectByValue(WebElement element, String data) {
		app.method("SELECTING BY VALUE --- " + element + " = " + data);
		getSelect(element).selectByValue(data);
	}

	public String getPageName() {
		return getClass().getSimpleName().toUpperCase();
	}

	public void typeIn(WebElement element, String data) {
		app.method("SENDING KEYS TO ---" + element + " = " + data);
		element.clear();
		element.sendKeys(data);
	}

	public String getValueInTextBox(WebElement element) {
		app.method("GET VALUE IN TEXTBOX ---" + element);
		return element.getAttribute("value");
	}

	public void clickOn(WebElement element) {
		app.method("CLICKING ON --- " + element);
		try {
			element.click();
		} catch (StaleElementReferenceException e) {
			app.logExceptions("Stale element exception");
		}
	}

	public boolean isPage(WebElement element) {
		app.logClass("Waiting for page --- " + getClassName());
		return isElementPresent(element);
	}

	private String getClassName() {
		return getClass().getSimpleName().toUpperCase();
	}
	
	public BasePage verifyError(String errorMessage){
		waits().isElementPresent(error);
		errors.validateErrorMessage(errorMessage);
		return this;
	}

	public boolean isElementPresent(WebElement element) {
		app.method("isElementPresent " + element);
		try {
			element.getTagName();
			return true;
		} catch (NoSuchElementException e) {
			app.logExceptions("No such element exception");
			return false;
		}
	}

	public void selectTab(WebElement element) {
		app.method("selecting Tab " + element);
		try {
			int i = 0;
			while (!element.getAttribute("class").equals("current") && i < 15) {
				clickOn(element);
				i++;
			}
		} catch (StaleElementReferenceException e) {
			app.logExceptions("Stale element exception");
		}
	}

	public boolean isElementPresent(By locator) {
		app.method("isElementPresent " + locator);
		List<WebElement> list = driver.findElements(locator);
		if (list.size() == 0) {
			return false;
		} else {
			return list.get(0).isDisplayed();
		}
	}
}
