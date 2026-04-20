package com.connectos.qa.pages;

import com.connectos.qa.core.TestConfig;
import org.openqa.selenium.By;

public class HomePage extends BasePage {
    private final By heading = By.cssSelector("h1.heading");

    public void open() {
        String path = TestConfig.isFileBased() ? "/index.html" : "";
        driver.get(TestConfig.baseUrl() + path);
    }

    public String headingText() {
        return text(heading);
    }

    public boolean hasFeatureLink(String featureName) {
        return !driver.findElements(By.linkText(featureName)).isEmpty();
    }
}
