package main.java.com.pageObjects.elements;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.com.framework.AppManager;
import main.java.com.framework.BasePage;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class Alerts extends BasePage {

	public Alerts(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		app.logClass("Custom Alerts Class");
	}

	@FindBy(id = "yui-gen13-button")
	private Button btnDiscart;

	@Timeout(1)
	@FindBy(id = "yui-gen0-button")
	private Button btnSubmit;

	@FindBy(id = "btnIDInformatinOk")
	private Button btnOk;

	public void dialogWindow(WebElement element) {
		if (isElementPresent(element)) {
			element.click();
		}
	}

	public void submitAppOnPopup() {
		dialogWindow(btnSubmit);
	}

	public void discartPopup() {
		dialogWindow(btnDiscart);
	}

	public void handleAlert() {
		app.method("handleAlert");
		if (isAlertPresent()) {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		}
	}

	public boolean isAlertPresent() {
		app.method("isAlertPresent");
		try {
			new WebDriverWait(driver, 2).until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (TimeoutException e) {
			app.logExceptions("Alert not present");
			return false;
		}
	}

	public boolean isPopUpPresent(List<WebElement> element) {
		if (element.size() == 0) {
			return false;
		} else {
			return element.get(0).isDisplayed();
		}
	}

	// Not in use yet, perhaps needs to be removed
	public void waitForIframe(WebElement element) {
		new WebDriverWait(driver, 0).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	public void switchToDefaulContent() {
		driver.switchTo().defaultContent();
	}

	public void switchToFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
}
