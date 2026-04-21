package io.cucumber.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class Home extends Page {

  public Home(ChromeDriver driver) {
    super(driver);
    System.out.println("Homepage title is : " + getTitle().getText());
  }

  @FindBy(css = "h1")
  private WebElement title;

  public WebElement getTitle() {
    return title;
  }

  @FindBy(css = "#content ul li a")
  private List<WebElement> exampleLinks;

  public List<WebElement> getExampleLinks() {
    return exampleLinks;
  }

  public void refresh() {
    driver.navigate().refresh();
    System.out.println("Refreshed page");
  }
}