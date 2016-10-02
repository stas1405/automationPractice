package main.java.com.pageObjects.elements;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class Calendar extends HtmlElement {

	private String dateFormat = "yyyy/MM/dd";

	@FindBy(xpath = "//*[@id='caleffectiveDate_t']//tbody/tr/td/a")
	private List<WebElement> activeCalendarDates;

	public void selectEffectiveDateAsLastDayOfTheMonth() {
		List<WebElement> calendar = activeCalendarDates;
		calendar.get(calendar.size() - 1).click();
	}

	public LocalDate getDateFromString(String dateString) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
            return LocalDate.parse(dateString, formatter);
		} catch (DateTimeParseException exc) {
			throw exc;
		}
	}

	public String getStringFromDate(LocalDate date) {

		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
			return date.format(formatter);
		} catch (DateTimeException exc) {
			throw exc;
		}
	}

	public String getDateInPastInDays(String dateString, int howManyDaysInPast) {
		LocalDate date = getDateFromString(dateString);
		return getStringFromDate(date.minusDays(howManyDaysInPast));
	}

	public String getDateInFutureInDays(String dateString, int howManyDaysInFuture) {
		LocalDate date = getDateFromString(dateString);
		return getStringFromDate(date.plusDays(howManyDaysInFuture));
	}

	public String getDateBetween2Dates(String startDateString, String endDateString) {
		LocalDate startDate = getDateFromString(startDateString);
		LocalDate endDate = getDateFromString(endDateString);
		long daysBetween = Duration.between(startDate.atTime(0, 0), endDate.atTime(0, 0)).toDays();
		LocalDate newDate = startDate.plusDays(daysBetween / 2);
		return getStringFromDate(newDate);
	}
	
	public String getDateInFutureInMonths(String dateString, int howManyMonthsInFuture) {
		LocalDate date = getDateFromString(dateString);
		return getStringFromDate(date.plusMonths(howManyMonthsInFuture));
	}
	
	public String getCurrentDate() {
		LocalDate date = LocalDate.now();
		return getStringFromDate(date);
	}

}
