package main.java.com.pageObjects.navigationPages;

import org.openqa.selenium.support.PageFactory;

import main.java.com.framework.AppManager;
import main.java.com.framework.BasePage;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class AbstractNavigation extends BasePage{
	
	private HomePage homePage;

	public AbstractNavigation(AppManager app) {
		super(app);
		//app.logClass("ON AbstractNavigation ");
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
	}
	
	
	public HomePage HomePage() {
		if (homePage == null) {
			homePage = new HomePage(app);
		}
		return homePage;
	}
	
	

	

}
