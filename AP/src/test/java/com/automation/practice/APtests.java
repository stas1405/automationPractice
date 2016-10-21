package test.java.com.automation.practice;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import main.java.com.framework.TestListener;
import main.resources.ExtentReporterNG;
import test.java.com.autoPractice.TestBase;


@Listeners({TestListener.class, ExtentReporterNG.class})
public class APtests extends TestBase{
	
	@Test(groups={"regression","ap"}, priority = 1) 	
	public void CreateAccountWithWrongPostalCode(){
		goTo.accountTab();
		goTo.CreateAccountPage().createAccount().withEmail("autopractice100@autopractice.com");
		goTo.RegisterUserPage().registerUser()
		.withMaleGender()
		.withCustomerFirstName("John")
		.withCustomerLastName("Smith")
		.withDateOfBirth("14", "12", "1990")
		.withPasswd("12345")
		.withAddress("1234 w23th Ave")
		.withCity("New York")
		.withState("Indiana")
		.withLPostalCode("V5T1HR")
		.withMobilePhone("7756457009")
		.register()
		.verifyError("The Zip/Postal code you've entered is invalid. It must follow this format: 00000");		
	}
	
	@Test(groups={"regression","ap"}, priority = 3) 
	public void Login(){
		goTo.accountTab();
		goTo.LoginPage().loginToAccount()
		.withEmail("autopractice14@autopractice.com")
		.withPassword("12345")
		.login();
		assertThat(goTo.AccountPage().checkIfLoggedIn(), containsString("My account"));
		goTo.SignOut();
		assertThat(goTo.AccountPage().checkIfLoggedOut(), containsString("Authentication"));
	}
	
	@Test(groups={"regression","ap"}, priority = 2) 
	public void CreateAccount(){
		goTo.accountTab();
		goTo.CreateAccountPage().createAccount().withEmail("autopractice27@autopractice.com");
		goTo.RegisterUserPage().registerUser()
		.withMaleGender()
		.withCustomerFirstName("John")
		.withCustomerLastName("Smith")
		.withDateOfBirth("14", "12", "1990")
		.withPasswd("12345")
		.withAddress("1234 w23th Ave")
		.withCity("New York")
		.withState("Indiana")
		.withLPostalCode("12345")
		.withMobilePhone("7756457009")
		.register();
		assertThat(goTo.AccountPage().checkIfLoggedIn(), containsString("My account"));
		goTo.SignOut();
		assertThat(goTo.AccountPage().checkIfLoggedOut(), containsString("Authentication"));
		
	}
	@Test(groups={"regression","ap"}, priority = 4) 
	public void PurchaseTShirt(){
		goTo.accountTab();
		goTo.LoginPage().loginToAccountAs("autopractice14@autopractice.com", "12345");
		assertThat(goTo.AccountPage().checkIfLoggedIn(), containsString("My account"));
		goTo.TShirtPage().selectSleevTshirt();
		goTo.SelectedProductPage().withBlueColor().withMediumSize().addToCart();
		goTo.CartPage()
		.verifyProductType("Faded Short Sleeve T-shirts")
		.verifyProductColor("Blue")
		.verifyProductSize("M")
		.verifyTotalPrice("$18.51")
		.layerCartCheckOut()
		.summaryCheckOut()
		.adddressCheckOut()
		.verifyDelivery("My carrier Delivery next day!");
	}

}
