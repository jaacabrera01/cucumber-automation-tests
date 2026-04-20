package com.connectos.qa.pages;

import com.connectos.qa.core.TestConfig;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class SortableDataTablesPage extends BasePage {
    private final By dueHeader = By.xpath("//table[@id='table1']//span[text()='Due']");
    private final By dueValues = By.cssSelector("#table1 tbody tr td:nth-child(4)");

    public void open() {
        String path = TestConfig.isFileBased() ? "/tables.html" : "/tables";
        driver.get(TestConfig.baseUrl() + path);
    }

    public void sortDueColumn() {
        click(dueHeader);
    }

    public List<Double> dueValuesAsNumbers() {
        return driver.findElements(dueValues).stream()
                .map(cell -> cell.getText().replace("$", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }
}
