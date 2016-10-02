package main.java.com.pageObjects.APpages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.java.com.framework.AppManager;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class TShirtPage extends AbstarctAutoPracticeBase {

	public TShirtPage(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		//data = getPageDefaultData(this.getPageName());
	}
	

	@FindBy(xpath = "//*[@id='center_column']/ul/li/div/div[2]/h5/a")
	private Button sleevTshirt;

	public TShirtPage selectSleevTshirt(){
		clickOn(sleevTshirt);
		return this;
	}
}
