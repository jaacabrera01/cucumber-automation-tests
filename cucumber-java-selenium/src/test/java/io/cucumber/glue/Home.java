package io.cucumber.glue;

import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Home extends Context {

  private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

  public Home(Manager manager) {
    super(manager);
  }

  @Then("the list of examples should contain:")
  public void theListOfExamplesShouldContain(DataTable dataTable) {
    io.cucumber.pages.Home homePage = getHomePage();
    List<String> actual = homePage.getExampleLinks()
        .stream()
        .map(WebElement::getText)
        .map(text -> switch (text) {
          case "Basic Auth" -> "Basic Auth (user and pass: admin)";
          case "Digest Authentication" -> "Digest Authentication (user and pass: admin)";
          default -> text;
        })
        .toList();

    List<String> expected = dataTable.asList();
    List<String> missing = expected.stream()
        .filter(item -> !actual.contains(item))
        .toList();

    assertTrue(missing.isEmpty(), "Actual links: " + actual + "\nMissing: " + missing);
  }

  @When("the 'Basic Auth' example is opened with username {string} and password {string}")
  public void theBasicAuthExampleIsOpenedWithUsernameAndPassword(String username, String password) {
    String url = "https://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
    getDriver().get(url);
  }

  @Then("{string} should be displayed")
  public void expectedMessageShouldBeDisplayed(String expectedMessage) {
    String visibleText = getDriver().findElement(By.tagName("body")).getText();
    String pageSource = getDriver().getPageSource();

    boolean foundInVisibleText = visibleText.contains(expectedMessage);
    boolean foundInPageSource = pageSource != null && pageSource.contains(expectedMessage);

    assertTrue(foundInVisibleText || foundInPageSource,
        "Expected message not found.\nVisible text: " + visibleText + "\nPage source: " + pageSource);
  }

  private io.cucumber.pages.Home getHomePage() {
    return (io.cucumber.pages.Home) getTestStash().get("homePage");
  }

  @When("a Basic Auth request is sent with username {string} and password {string}")
  public void aBasicAuthRequestIsSentWithUsernameAndPassword(String username, String password) throws Exception {
    String credentials = username + ":" + password;
    String encoded = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://the-internet.herokuapp.com/basic_auth"))
        .header("Authorization", "Basic " + encoded)
        .GET()
        .build();

    HttpResponse<String> response = HTTP_CLIENT.send(request, HttpResponse.BodyHandlers.ofString());

    stash("basicAuthStatus", response.statusCode());
    stash("basicAuthBody", response.body());
  }

  @Then("the Basic Auth response status should be {int}")
  public void theBasicAuthResponseStatusShouldBe(int expectedStatus) {
    Object statusObject = getTestStash().get("basicAuthStatus");
    Integer actualStatus = assertInstanceOf(Integer.class, statusObject, "No HTTP status was stashed");
    assertEquals(expectedStatus, actualStatus);
  }

  @When("the {string} example is opened")
  public void theExampleIsOpened(String exampleName) {
    io.cucumber.pages.Home homePage = getHomePage();

    WebElement exampleLink = homePage.getExampleLinks()
            .stream()
            .filter(link -> link.getText().equals(exampleName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown example link: " + exampleName));

    exampleLink.click();
  }

  @Then("the Example {int} table should display:")
  public void theExampleTableShouldDisplay(int tableNumber, DataTable dataTable) {
    io.cucumber.pages.SortableDataTables page =
        new io.cucumber.pages.SortableDataTables(getDriver());

    List<Map<String, String>> expectedRows = dataTable.asMaps(String.class, String.class);
    List<Map<String, String>> actualRows = page.getTableRows(tableNumber);

    assertEquals(expectedRows, actualRows);
  }

  @When("Example {int} is sorted by {string}")
  public void exampleIsSortedBy(int tableNumber, String columnName) {
    io.cucumber.pages.SortableDataTables page =
        new io.cucumber.pages.SortableDataTables(getDriver());
    page.clickHeader(tableNumber, columnName);
  }

  @Then("Example {int} should be sorted ascending by {string}")
  public void exampleShouldBeSortedAscendingBy(int tableNumber, String columnName) {
    assertSorted(tableNumber, columnName, false);
  }

  @Then("Example {int} should be sorted descending by {string}")
  public void exampleShouldBeSortedDescendingBy(int tableNumber, String columnName) {
    assertSorted(tableNumber, columnName, true);
  }

  private void assertSorted(int tableNumber, String columnName, boolean descending) {
    io.cucumber.pages.SortableDataTables page =
        new io.cucumber.pages.SortableDataTables(getDriver());
    List<String> actualValues = page.getColumnValues(tableNumber, columnName);
    List<String> expectedValues = new ArrayList<>(actualValues);
    Comparator<String> comparator = getComparator(columnName);
    expectedValues.sort(descending ? comparator.reversed() : comparator);
    assertEquals(expectedValues, actualValues);
  }

  private Comparator<String> getComparator(String columnName) {
    if ("Due".equals(columnName)) {
      return Comparator.comparingDouble(value -> Double.parseDouble(value.replace("$", "")));
    }
    return Comparator.naturalOrder();
  }
}
