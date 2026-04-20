package com.connectos.qa.steps;

import com.connectos.qa.pages.BasicAuthPage;
import com.connectos.qa.pages.HomePage;
import com.connectos.qa.pages.SortableDataTablesPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TheInternetSteps {
    private final HomePage homePage = new HomePage();
    private final BasicAuthPage basicAuthPage = new BasicAuthPage();
    private final SortableDataTablesPage tablesPage = new SortableDataTablesPage();

    private List<Double> capturedDueValues;

    @Given("I open The Internet homepage")
    public void iOpenTheInternetHomepage() {
        homePage.open();
    }

    @Then("I should see the homepage title {string}")
    public void iShouldSeeTheHomepageTitle(String expectedTitle) {
        Assert.assertEquals("Unexpected homepage heading", expectedTitle, homePage.headingText());
    }

    @Then("I should see a feature link for {string}")
    public void iShouldSeeAFeatureLinkFor(String featureName) {
        Assert.assertTrue("Missing feature link: " + featureName, homePage.hasFeatureLink(featureName));
    }

    @When("I authenticate with username {string} and password {string}")
    public void iAuthenticateWithUsernameAndPassword(String username, String password) {
        basicAuthPage.openWithCredentials(username, password);
    }

    @Then("I should see the basic auth success message")
    public void iShouldSeeTheBasicAuthSuccessMessage() {
        Assert.assertEquals(
                "Basic Auth page message mismatch",
                "Congratulations! You must have the proper credentials.",
                basicAuthPage.successMessage()
        );
    }

    @Given("I open the sortable data tables page")
    public void iOpenTheSortableDataTablesPage() {
        tablesPage.open();
    }

    @When("I sort the Due column")
    public void iSortTheDueColumn() {
        tablesPage.sortDueColumn();
        capturedDueValues = tablesPage.dueValuesAsNumbers();
    }

    @Then("the Due values should be sorted in ascending order")
    public void theDueValuesShouldBeSortedInAscendingOrder() {
        List<Double> expected = new ArrayList<>(capturedDueValues);
        Collections.sort(expected);
        Assert.assertEquals("Due values are not sorted ascending", expected, capturedDueValues);
    }
}
