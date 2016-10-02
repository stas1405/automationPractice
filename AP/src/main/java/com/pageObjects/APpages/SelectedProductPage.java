package main.java.com.pageObjects.APpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import main.java.com.framework.AppManager;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class SelectedProductPage extends AbstarctAutoPracticeBase{
	
	public SelectedProductPage(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		//data = getPageDefaultData(this.getPageName());
	}
	
	@FindBy(css="a[title='Blue']")
	private WebElement colorBlue;
	
	@FindBy(id="add_to_cart")
	private Button addToCart;
	
	@FindBy(id="group_1")
	private WebElement productSize;
	
	public SelectedProductPage withBlueColor(){
		//switchToElem(colorBlue);
		clickOn(colorBlue);
		return this;
	}
	
	public SelectedProductPage withMediumSize(){
		selectByVisibleText(productSize, "M");
		return this;
	}
	
	public SelectedProductPage addToCart(){
		clickOn(addToCart);
		return this;
	}


}
