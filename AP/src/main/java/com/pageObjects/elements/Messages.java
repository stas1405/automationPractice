package main.java.com.pageObjects.elements;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class Messages extends HtmlElement {

	@FindBy(xpath = "//*[@id='center_column']/div/ol/li")
	@Timeout(0)
	private WebElement errorID;

	public void validateErrorMessage(String expectedMessage) {
		try {
			assertThat(errorID.getText(), containsString(expectedMessage));
		} catch (NoSuchElementException e) {
			assertThat("Message is not displayed", is(expectedMessage));
		}
	}

}
