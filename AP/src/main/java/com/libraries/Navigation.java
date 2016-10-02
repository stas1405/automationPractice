package main.java.com.libraries;

import main.java.com.framework.AppManager;
import main.java.com.framework.BasePage;
import main.java.com.pageObjects.APpages.CreateAccountPage;
import main.java.com.pageObjects.APpages.LoginPage;
import main.java.com.pageObjects.APpages.RegisterUserPage;
import main.java.com.pageObjects.APpages.TShirtPage;
import main.java.com.pageObjects.APpages.SelectedProductPage;
import main.java.com.pageObjects.APpages.CartPage;

public class Navigation extends BasePage {

	public Navigation(AppManager app) {
		super(app);
	}
	
	public void homePage(){
		app.method("goTo homePage");
		NavigationPages().HomePage();
	}
	
	public void accountTab(){
		app.method("goTo accountTab");
		NavigationPages().HomePage().selectSignInTab();
	}
	
	public void tShirtTab(){
		app.method("goTo T-Shirt tab");
		NavigationPages().HomePage().selectTshirtTab();
	}
	
	public CreateAccountPage CreateAccountPage(){
		app.method("goTo createAccountTab");
		Application().CreateAccountPage();
		return Application().CreateAccountPage();
	}
	
	public void SignOut(){
		app.method("signOut");
		NavigationPages().HomePage().selectSignOutTab();
	}
	
	public TShirtPage TShirtPage(){
		app.method("goTo tshirtPage");
		Application().TShirtPage();
		return Application().TShirtPage();
	}
	
	public SelectedProductPage SelectedProductPage(){
		app.method("goTo selectProduct");
		Application().SelectedProductPage();
		return Application().SelectedProductPage();
	}
	
	public CartPage CartPage(){
		app.method("goTo cartPage");
		Application().CartPage();
		return Application().CartPage();
	}
	
	public LoginPage LoginPage(){
		app.method("goTo LoginTab");
		Application().LoginPage();
		return Application().LoginPage();
	}
	
	public RegisterUserPage RegisterUserPage(){
		app.method("goTo registerUserPage");
		Application().RegisterUserPage();
		return Application().RegisterUserPage();
	}
}
