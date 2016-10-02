package main.java.com.pageObjects.navigationPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.java.com.framework.AppManager;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

@Timeout(0)
public class HomePage extends AbstractNavigation{

	@Timeout(1)	
	@Name("tabSignIn")
	@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[1]/a")
	private Link tabSignIn;
	
	@Timeout(1)	
	@Name("tabSignOut")
	@FindBy(xpath="//*[@id='header']/div[2]/div/div/nav/div[2]/a")
	private Link tabSignOut;
	
	@Name("tabTshirt")
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	private Link tabTshirt; 	

	@Timeout(3)
	@FindBy(name="psmForm")
	private WebElement homePage;
	
	public HomePage(AppManager app) {
		super(app);
		app.logClass("HomePage");
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
	}
	
	public boolean onHomepage() {
		return isPage(homePage);
	}
	
	public HomePage selectSignInTab() {
		app.method("selectSignInTab");
		selectTab(tabSignIn.findElement(By.xpath("./..")));
		return this;
	}
	
	public HomePage selectSignOutTab() {
		app.method("selectSignOutTab");
		selectTab(tabSignOut.findElement(By.xpath("./..")));
		return this;
	}

	public void selectTshirtTab() {
		app.method("selectT-shirtTab");
		clickOn(tabTshirt);
		//selectTab(tabTshirt.findElement(By.xpath("../..")));
		
	}
}
