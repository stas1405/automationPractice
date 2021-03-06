package main.java.com.pageObjects.APpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.java.com.framework.AppManager;
import main.java.com.pageObjects.elements.Messages;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class AccountPage extends AbstarctAutoPracticeBase{
	
	public AccountPage(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		//data = getPageDefaultData(this.getPageName());
	}
	
	@FindBy(id="page")
	private Messages errors;
	
	@FindBy(className="navigation_page")
	private WebElement navPage;
	
	public String checkIfLoggedIn(){
		waits().waitUntilTextAppear("My account");
		return navPage.getText();
	}
	
	public String checkIfLoggedOut(){
		waits().waitUntilTextAppear("Authentication");
		return navPage.getText();
	}
	

}
