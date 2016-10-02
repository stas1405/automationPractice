package main.java.com.pageObjects.APpages;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import main.java.com.framework.AppManager;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class CartPage extends AbstarctAutoPracticeBase{
	
	public CartPage(AppManager app) {
		super(app);
		PageFactory.initElements(new HtmlElementDecorator((new HtmlElementLocatorFactory(driver))), this);
		//data = getPageDefaultData(this.getPageName());
	}

	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")
	private WebElement popUpCheckOut;
	
	@FindBy(xpath="//*[@id='center_column']/p[2]/a[1]/span")
	private WebElement centerCheckOut;
	
	@FindBy(xpath="//*[@id='center_column']/form/p/button")
	private WebElement addressCheckOut;
	
	
	@FindBy(xpath="//*[@id='layer_cart']/div[1]/div[2]/div[3]/span")
	private WebElement totalPrice;
	
	@FindBy(xpath="//*[@id='layer_cart']")
	private WebElement cart;
	
	@FindBy(xpath="//*[@id='form']/div/div[2]/div[1]/div/div/table/tbody/tr/td[3]")
	private WebElement deliveryType;
	
	@FindBy(id="layer_cart_product_title")
	private WebElement productTitle;
	
	@FindBy(id="layer_cart_product_attributes")
	private WebElement colorAndSizeType;
	
	public CartPage layerCartCheckOut(){
		waits().waitForVisibilityOfElement(popUpCheckOut);
		clickOn(popUpCheckOut);
		return this;
	}
	
	public CartPage summaryCheckOut(){
		waits().waitForVisibilityOfElement(centerCheckOut);
		clickOn(centerCheckOut);
		return this;
	}
	
	public CartPage adddressCheckOut(){
		waits().waitForVisibilityOfElement(addressCheckOut);
		clickOn(addressCheckOut);
		return this;
	}
	
	public CartPage verifyTotalPrice(String string){	
		try {
			assertThat(totalPrice.getText(), containsString(string));
		} catch (NoSuchElementException e) {
			assertThat("Price is different", is(string));
		}	
		return this;
	}

	public CartPage verifyDelivery(String string) {
		try {
			assertThat(deliveryType.getText(), containsString(string));
		} catch (NoSuchElementException e) {
			assertThat("Delivery is different", is(string));
		}	
		return this;
	}

	public CartPage verifyProductType(String string){
		waits().waitForVisibilityOfElement(popUpCheckOut);
		try {
			assertThat(productTitle.getText(), containsString(string));
		} catch (NoSuchElementException e) {
			assertThat("Type is different", is(string));
		}	
		return this;
	}
	
	public CartPage verifyProductColor(String string) {
		waits().waitForVisibilityOfElement(popUpCheckOut);
		String[] sizeNcolor = colorAndSizeType.getText().split(", ");
		for (String elem : sizeNcolor) {
			try {		
				assertThat(colorAndSizeType.getText(), containsString(string));
			} catch (NoSuchElementException e) {
				assertThat("Size or Color is different", is(string));
			}	
		}
		return this;
	}
	
	public CartPage verifyProductSize(String string) {
		waits().waitForVisibilityOfElement(popUpCheckOut);
		String[] sizeNcolor = colorAndSizeType.getText().split(", ");
		for (String elem : sizeNcolor) {
			try {		
				assertThat(colorAndSizeType.getText(), containsString(string));
			} catch (NoSuchElementException e) {
				assertThat("Size or Color is different", is(string));
			}	
		}
		return this;
	}
}
