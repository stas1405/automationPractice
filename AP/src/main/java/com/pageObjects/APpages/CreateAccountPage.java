package main.java.com.pageObjects.APpages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import main.java.com.framework.AppManager;
import main.java.com.pageObjects.elements.Messages;
import main.resources.EmailGenerator;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class CreateAccountPage extends AbstarctAutoPracticeBase{
	
	@FindBy(id = "account-creation_form")
	private WebElement formAccCreation;
	
	@FindBy(id = "create-account_form")
	private WebElement formAccCreate;
	
	@FindBy(id = "email_create")
	private WebElement fieldUserEmail;

	@FindBy(id = "SubmitCreate")
	private WebElement btnSignUp;
	
	@FindBy(id = "page")
	@Timeout(0)
	private Messages page;	
	
	
	public CreateAccountPage(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		//data = getPageDefaultData(this.getPageName());
	}

	public void signUp() {
		typeIn(fieldUserEmail, EmailGenerator.GenerateEmeail());
		clickOn(btnSignUp);
		Application().CreateAccountPage().onAuthPage();
	}	
	
		public boolean onAuthPage() {
		if(formAccCreate != null){
			return isPage(formAccCreate);
		}
		return isPage(formAccCreation);	
	}

	public CreateAccountPage createAccount(){
		return this;
	}
	
	public CreateAccountPage withEmail(String string){
		typeIn(fieldUserEmail, string);
		clickOn(btnSignUp);	
		return this;
	}	
}
