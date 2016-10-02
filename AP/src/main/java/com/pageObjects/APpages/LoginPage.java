package main.java.com.pageObjects.APpages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import main.java.com.framework.AppManager;
import main.java.com.pageObjects.elements.Messages;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class LoginPage extends AbstarctAutoPracticeBase {

	@FindBy(id = "email")
	private WebElement fieldUserEmail;

	@FindBy(id = "passwd")
	private WebElement fieldPassword;

	@FindBy(id = "SubmitLogin")
	private WebElement btnSignIn;
	
	@FindBy(id = "body")
	@Timeout(0)
	private Messages page;	

	public LoginPage(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		//data = getPageDefaultData(this.getPageName());
	}

	public boolean onAuthPage() {
		return isPage(fieldUserEmail);
	}

	public void login() {
		clickOn(btnSignIn);
	}

	public void loginToAccountAs(String username, String password) {
		Assert.assertFalse("Unable to connect to server", driver.getTitle().equals("Problem loading page"));
		typeIn(fieldUserEmail, username);
		typeIn(fieldPassword, password);
		clickOn(btnSignIn);
	}
	
	public LoginPage loginToAccount(){
		
		return this;
	}

	public LoginPage withEmail(String string) {
		typeIn(fieldUserEmail, string);
		return this;
	}

	public LoginPage withPassword(String string) {
		typeIn(fieldPassword, string);
		return this;
	}
}
