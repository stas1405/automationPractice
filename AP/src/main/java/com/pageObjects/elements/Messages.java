package main.java.com.pageObjects.elements;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.text.IsEmptyString.isEmptyOrNullString;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class Messages extends HtmlElement {

	@Timeout(0)
	@FindBy(id = "msgbox")
	private WebElement message;

	@Timeout(2)
	@FindBy(id = "blazeDeclineBox")
	private WebElement blazeDeclineBox;

	@Timeout(0)
	@FindBy(id = "blazeReferralBox")
	private WebElement blazeReferralBox;

	@FindBy(className = "errbox")
	@Timeout(0)
	private WebElement errorClass;

	@FindBy(xpath = "//*[@id='center_column']/div/ol/li")
	@Timeout(0)
	private WebElement errorID;

	@FindBy(xpath = "//*[@id='center_column']/div/ol/li")
	@Timeout(0)
	private WebElement errorMsgBox;

	@Timeout(0)
	@FindBy(id = "warnbox")
	private WebElement warningmessage;

	public void isPolicyErrorMessageAppears() {
		try {
			genericMessageError(errorID);
		} catch (NoSuchElementException e) {
			e.getCause();
		}
	}

	public void isErrorMessageAppears() {
		try {
			genericMessageError(errorClass);
		} catch (NoSuchElementException e) {
			e.getCause();
		}
	}

	public void isErrorBoxMessageAppears() {
		try {
			genericMessageError(errorMsgBox);
		} catch (NoSuchElementException e) {
			e.getCause();
		}
	}

	public void isBlazeErrorMessageAppears() {
		try {
			assertThat(blazeDeclineBox.getText(), isEmptyOrNullString());
		} catch (NoSuchElementException e) {
			e.getCause();
		}
	}

	public void validateMessage(String expectedMessage) {
		try {
			assertThat(message.getText(), containsString(expectedMessage));
		} catch (NoSuchElementException e) {
			assertThat("Message is not displayed", is(expectedMessage));
		}
	}

	public void validateBlazeDeclineMessage(String expectedMessage) {
		try {
			assertThat(blazeDeclineBox.getText(), containsString(expectedMessage));
		} catch (NoSuchElementException e) {
			assertThat("Message is not displayed", is(expectedMessage));
		}
	}

	public void validateBlazeReferralMessage(String expectedMessage) {
		try {
			assertThat(blazeReferralBox.getText(), containsString(expectedMessage));
		} catch (NoSuchElementException e) {
			assertThat("Message is not displayed", is(expectedMessage));
		}
	}

	public void validateErrorMessage(String expectedMessage) {
		try {
			assertThat(errorID.getText(), containsString(expectedMessage));
		} catch (NoSuchElementException e) {
			assertThat("Message is not displayed", is(expectedMessage));
		}
	}

	public void validateWarningMessage(String expectedMessage) {
		try {
			assertThat(warningmessage.getText(), containsString(expectedMessage));
		} catch (NoSuchElementException e) {
			assertThat("Message is not displayed", is(expectedMessage));
		}
	}

	// ToDo
	// needs to throw policy number in assertion message
	private boolean genericMessageError(WebElement element) {
		boolean result = false;
		String errorMessage = null;
		if (element.getTagName() != null) {
			List<WebElement> errBoxRows = element.findElements(By.xpath(".//ul/li"));
			for (WebElement error : errBoxRows) {
				System.out.println(error.getText());
				errorMessage = error.getText();
				result = true;
			}
		}
		Assert.assertFalse("Error message is displayed - " + errorMessage, result);
		return result;
	}

}
