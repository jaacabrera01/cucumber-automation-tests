# Automated Test Suite — The Internet
## Overview
This project contains automated tests for [the-internet.herokuapp.com](https://the-internet.herokuapp.com), 
a practice web application commonly used for QA automation testing.

The tests are written using **Behaviour Driven Development (BDD)** with Cucumber.

## What is Being Tested

| Test ID | Scenario | Status |
|---|---|---|
| TEST_TI_0001 | Homepage displays the expected list of example links | Passed |
| TEST_TI_0002 - TEST_TI_003 | Basic Auth validates access with valid and invalid credentials | Passed |
| TEST_TI_0004 | Verification of data table | Passed |
| TEST_TI_0005 - TEST_TI_008| Sortable Data Tables can be sorted by column | Passed |

| Tool | Purpose |
|---|---|
| Java 21 | Programming language | (check version java -version)
| Selenium WebDriver | Browser automation | (check version mvn -version)
| Cucumber | BDD test framework |
| Maven | Build and dependency management |
| JUnit 5 | Test runner |
| Page Object Model | Design pattern for maintainable test code |

## How to Run the Tests
### Requirements
- Java 21 or higher
- Maven
- Google Chrome (latest version)

### Steps

1. Clone this repository
2. Open a terminal and navigate to the project folder:
cd cucumber-java-selenium
3. Run all tests using mvn test

## Test Design Notes: 
Scenario 1 — The homepage requirement lists 40 examples. The live page renders
44 links. The test verifies all 40 required items are present and flags the
discrepancy in the failure message.
Scenario 2 — Basic Auth is tested with valid credentials (expect success),
invalid credentials (expect rejection), and mismatched credentials (expect rejection).
Scenario 3 — Sortable Data Tables are tested by clicking column headers and
verifying the data re-orders correctly in ascending order.

## Project Structure
src/test/java/io/cucumber/
├── core/     Driver setup, hooks, and shared utilities
├── glue/     Step definitions — connects feature file steps to Java code
├── pages/    Page Objects — models each web page under test
└── RunCucumberTest.java

src/test/resources/io/cucumber/
└── features/ Feature files — plain English test scenarios (Gherkin)
