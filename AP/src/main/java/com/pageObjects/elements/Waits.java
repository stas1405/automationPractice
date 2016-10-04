package main.java.com.pageObjects.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import main.java.com.framework.AppManager;
import main.java.com.framework.BasePage;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@Timeout(0)
public class Waits extends BasePage {

	@FindBy(className = "infobox")
	private WebElement infobox;

	public Waits(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		app.logClass("Custom waits class");
	}

	public void waitForElementToBeClickable(WebElement element){
		new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForVisibilityOfElement(WebElement element){
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
	}
	
	public void waitUntilProccessComplete() {
		app.method("waitUntilProccessComplete");
		waitForInvisibilityOfElement(90, By.id("request_processing_layer_content"));
	}
	
	public void waitForOptionsToComplete() {
		app.method("waitUntilProccessComplete");
		waitForInvisibilityOfElement(90, By.id("request_processing_layer_content"));
	}
	
	public void waitForAttributeToBe(WebElement element, String attribute, String value ) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.attributeToBe(element, attribute, value));
	}
	
	private void waitForInvisibilityOfElement(long sec, By locator){
		app.method("waitForInvisibilityOfElement - " + locator );
		new WebDriverWait(driver, sec)
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	private void waitForVisibilityOfElement(long sec, By locator){
		app.method("waitForVisibilityOfElement - " + locator );
		new WebDriverWait(driver, sec)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
		
	public void waitForInfoMessage() {
		app.method("waitForInfoMessage");
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(infobox));
	}
	
	public void waitUntilCartWindowAppear() {
		waitForVisibilityOfElement(20, By.xpath("//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span"));
	}

	public void waitForElementToBeDisabled(WebElement element) {
		waitForAttributeToBe(element, "disabled", "true");
	}
}
