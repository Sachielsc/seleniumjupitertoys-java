package com.planittesting.jupitertoys.model.ui;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Table {

	private WebElement rootElement;

	public Table(WebElement element) {
		this.rootElement = element;
	}

	private int getColumnIndex(String column) {
		List<WebElement> headers = rootElement.findElements(By.tagName("th"));
		return IntStream.range(0, headers.size())
						.filter(i->headers.get(i).getText().equalsIgnoreCase(column))
						.findFirst()
						.orElseThrow();
	}

	private Optional<WebElement> getElement(int searchColumnIndex, String searchValue, int returnColumnIndex) {
		List<WebElement> rows = rootElement.findElements(By.cssSelector("tbody tr"));
		return rows.stream()
				   .map(row->row.findElements(By.tagName("td")))
				   .filter(values->values.get(searchColumnIndex).getText().equalsIgnoreCase(searchValue))
				   .map(value->value.get(returnColumnIndex))
				   .findFirst();
	}
	
	public boolean isPresent(String searchColumn, String searchValue, String returnColumn) {
		int searchColumnIndex = getColumnIndex(searchColumn);
		int returnColumnIndex = getColumnIndex(returnColumn);
		return getElement(searchColumnIndex, searchValue, returnColumnIndex).isPresent();
	}
	
	public WebElement getValue(int searchColumnIndex, String searchValue, int returnColumnIndex) {
		return getElement(searchColumnIndex, searchValue, returnColumnIndex).orElseThrow();
	}
	
	public WebElement getValue(String searchColumn, String searchValue, String returnColumn)  {
		int searchColumnIndex = getColumnIndex(searchColumn);
		int returnColumnIndex = getColumnIndex(returnColumn);
		return getValue(searchColumnIndex, searchValue, returnColumnIndex);
	}
	
	public WebElement getValue(int searchColumnIndex, String searchValue, String returnColumn)  {
		var returnColumnIndex = getColumnIndex(returnColumn);
		return getValue(searchColumnIndex, searchValue, returnColumnIndex);
	}
	
	public WebElement getValue(String searchColumn, String searchValue, int returnColumnIndex)  {
		var searchColumnIndex = getColumnIndex(searchColumn);
		return getValue(searchColumnIndex, searchValue, returnColumnIndex);
	}

	public WebElement getHeader(String headerValue) {
		return rootElement.findElements(By.tagName("th"))
					  .stream()
					  .filter(e -> e.getText().equalsIgnoreCase(headerValue))
					  .findFirst()
					  .orElseThrow();
	}

	public WebElement getHeader(int index) {
		return rootElement.findElements(By.tagName("th")).get(index);
	}

}
