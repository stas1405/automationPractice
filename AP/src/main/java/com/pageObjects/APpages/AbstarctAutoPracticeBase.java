package main.java.com.pageObjects.APpages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import main.java.com.framework.AppManager;
import main.java.com.framework.BasePage;
import main.java.com.pageObjects.elements.Messages;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class AbstarctAutoPracticeBase extends BasePage{
	
	@FindBy(id = "page")
	@Timeout(0)
	private Messages page;	
	
	private AccountPage accountPage;
	private CreateAccountPage createAccountPage;
	private RegisterUserPage registerUserPage;
	private LoginPage loginPage;
	private TShirtPage tShirtPage;
	private SelectedProductPage selectedProduct;
	private CartPage cartPage;
	
	public AbstarctAutoPracticeBase(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
	}
	public SelectedProductPage SelectedProductPage() {
		if (selectedProduct == null) {
			selectedProduct = new SelectedProductPage(app);
		}
		return selectedProduct;
	}
	
	public CartPage CartPage() {
		if (cartPage == null) {
			cartPage = new CartPage(app);
		}
		return cartPage;
	}
	
	
	public TShirtPage TShirtPage() {
		if (tShirtPage == null) {
			tShirtPage = new TShirtPage(app);
		}
		return tShirtPage;
	}
	
	public CreateAccountPage CreateAccountPage() {
		if (createAccountPage == null) {
			createAccountPage = new CreateAccountPage(app);
		}
		return createAccountPage;
	}
	
	public RegisterUserPage RegisterUserPage() {
		if (registerUserPage == null) {
			registerUserPage = new RegisterUserPage(app);
		}
		return registerUserPage;
	}
	
	public AccountPage AccountPage() {
		if (accountPage == null) {
			accountPage = new AccountPage(app);
		}
		return accountPage;
	}
	
	public LoginPage LoginPage() {
		if (loginPage == null) {
			loginPage = new LoginPage(app);
		}
		return loginPage;
	}

}
