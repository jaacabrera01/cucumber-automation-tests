Feature: Validate The Internet core flows
  As a QA automation engineer
  I want stable automated checks for core examples in The Internet app
  So that I can quickly detect regressions

  Scenario: Homepage displays expected links
    Given I open The Internet homepage
    Then I should see the homepage title "Welcome to the-internet"
    And I should see a feature link for "Basic Auth"
    And I should see a feature link for "Sortable Data Tables"

  Scenario: Basic authentication succeeds with valid credentials
    When I authenticate with username "admin" and password "admin"
    Then I should see the basic auth success message

  Scenario: Due column can be sorted ascending
    Given I open the sortable data tables page
    When I sort the Due column
    Then the Due values should be sorted in ascending order
