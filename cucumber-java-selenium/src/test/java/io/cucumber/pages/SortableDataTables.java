package io.cucumber.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SortableDataTables extends Page {

  public SortableDataTables(ChromeDriver driver) {
    super(driver);
  }

  public List<Map<String, String>> getTableRows(int tableNumber) {
    String tableSelector = "#table" + tableNumber + " tbody tr";
    return driver.findElements(By.cssSelector(tableSelector))
        .stream()
        .map(this::toRowData)
        .collect(Collectors.toList());
  }

  public void clickHeader(int tableNumber, String columnName) {
    driver.findElement(By.xpath("//table[@id='table" + tableNumber + "']//span[normalize-space()='" + columnName + "']"))
        .click();
  }

  public List<String> getColumnValues(int tableNumber, String columnName) {
    int columnIndex = getColumnIndex(columnName);
    String tableSelector = "#table" + tableNumber + " tbody tr";
    return driver.findElements(By.cssSelector(tableSelector))
        .stream()
        .map(row -> row.findElements(By.tagName("td")).get(columnIndex).getText())
        .collect(Collectors.toList());
  }

  private Map<String, String> toRowData(WebElement row) {
    List<WebElement> cells = row.findElements(By.tagName("td"));
    Map<String, String> rowData = new LinkedHashMap<>();
    rowData.put("Last Name", cells.get(0).getText());
    rowData.put("First Name", cells.get(1).getText());
    rowData.put("Email", cells.get(2).getText());
    rowData.put("Due", cells.get(3).getText());
    rowData.put("Web Site", cells.get(4).getText());
    return rowData;
  }

  private int getColumnIndex(String columnName) {
    return switch (columnName) {
      case "Last Name" -> 0;
      case "First Name" -> 1;
      case "Email" -> 2;
      case "Due" -> 3;
      case "Web Site" -> 4;
      default -> throw new IllegalArgumentException("Unknown column: " + columnName);
    };
  }
}
