package io.cucumber.glue;

import io.cucumber.java.en.Given;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;

public class Navigation extends Context {

  public Navigation(Manager manager) {
    super(manager);
  }

  @Given("the page under test is {string}")
  public void thePageUnderTestIs(String url) {
    getDriver().get(url);
    getTestStash().remove("homePage");

    if ("https://the-internet.herokuapp.com".equals(url)) {
      stash("homePage", new io.cucumber.pages.Home(getDriver()));
    }
  }
}
