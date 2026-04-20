package com.connectos.qa.pages;

import com.connectos.qa.core.TestConfig;
import org.openqa.selenium.By;

public class BasicAuthPage extends BasePage {
    private final By successMessage = By.cssSelector("div.example p");

    public void openWithCredentials(String username, String password) {
        if (TestConfig.isFileBased()) {
            driver.get(TestConfig.baseUrl() + "/basic_auth.html");
            return;
        }

        String baseUrl = TestConfig.baseUrl();
        String protocol = baseUrl.startsWith("https://") ? "https://" : "http://";
        String host = baseUrl.replaceFirst("^https?://", "");
        driver.get(protocol + username + ":" + password + "@" + host + "/basic_auth");
    }

    public String successMessage() {
        return text(successMessage);
    }
}
