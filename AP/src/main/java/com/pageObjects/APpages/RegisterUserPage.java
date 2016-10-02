package main.java.com.pageObjects.APpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.java.com.framework.AppManager;
import main.java.com.pageObjects.elements.Messages;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class RegisterUserPage extends AbstarctAutoPracticeBase{
	
	public RegisterUserPage(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		//data = getPageDefaultData(this.getPageName());
	}
	@FindBy(id = "page")
	@Timeout(0)
	private Messages page;	
	
	@FindBy(id = "id_gender1")
	private WebElement radioGenderMale;
	
	@FindBy(id = "id_gender2")
	private WebElement radioGenderFemale;
	
	@FindBy(id = "customer_firstname")
	private WebElement fieldCustomerFirstName;
	
	@FindBy(id = "customer_lastname")
	private WebElement fieldCustomerLastName;
	
	@FindBy(id = "passwd")
	private WebElement fieldPasswd;
	
	@FindBy(id = "days")
	private WebElement dropDownDays;

	@FindBy(id = "months")
	private WebElement dropDownMonths;
	
	@FindBy(id = "years")
	private WebElement dropDownYears;
	
	@FindBy(id = "address1")
	private WebElement fieldAddress;
	
	@FindBy(id = "city")
	private WebElement fieldCity;
	
	@FindBy(id = "id_state")
	private WebElement dropDownState;
	
	@FindBy(id = "postcode")
	private WebElement fieldPostCode;
	
	@FindBy(id = "phone_mobile")
	private WebElement fieldPhoneMobile;
	
	@FindBy(id = "submitAccount")
	private WebElement btnSubmitAcc;
	
	public RegisterUserPage registerUser(){
		return this;
	}
	
	public RegisterUserPage withMaleGender(){
		clickOn(radioGenderMale);
		return this;
	}
	
	public RegisterUserPage withFemaleGender(){
		clickOn(radioGenderFemale);
		return this;
	}
	
	public RegisterUserPage withCustomerFirstName(String string){
		typeIn(fieldCustomerFirstName, string);
		return this;
	}
	
	public RegisterUserPage withCustomerLastName(String string){
		typeIn(fieldCustomerLastName, string);
		return this;
	}
	
	public RegisterUserPage withPasswd(String string){
		typeIn(fieldPasswd, string);
		return this;
	}
	
	public RegisterUserPage withDateOfBirth(String day, String month, String year){
		selectByValue(dropDownDays, day);
		selectByValue(dropDownMonths, month);
		selectByValue(dropDownYears, year);
		return this;
	}
	
	public RegisterUserPage withAddress(String string){
		typeIn(fieldAddress, string);
		return this;
	}
	
	public RegisterUserPage withCity(String string){
		typeIn(fieldCity, string);
		return this;
	}
	
	public RegisterUserPage withState(String string){
		selectByVisibleText(dropDownState, string);
		return this;
	}
	
	public RegisterUserPage withLPostalCode(String string){
		typeIn(fieldPostCode, string);
		return this;
	}

	public RegisterUserPage withMobilePhone(String string){
		typeIn(fieldPhoneMobile, string);
		return this;
	}
	
	public RegisterUserPage register(){
		clickOn(btnSubmitAcc);
		return this;
	}

}
