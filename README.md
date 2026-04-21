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

## Test Design Notes
1. **Scenario 1** — The homepage requirement lists 40 examples.  
   - The live page renders 44 links.  
   - The test verifies all 40 required items are present.  
   - Flags the discrepancy in the failure message.

2. **Scenario 2** — Basic Auth is tested with:  
   - Valid credentials (expect success)  
   - Invalid credentials (expect rejection)  
   - Mismatched credentials (expect rejection)

3. **Scenario 3** — Sortable Data Tables are tested by:  
   - Clicking column headers  
   - Verifying the data re-orders correctly in ascending/descending order

# Project Structure
- **glue/** — *Step definitions: connects feature file steps to Java code*
- **pages/** — *Page Objects: models each web page under test*
- **core/** — *Driver setup, hooks, and shared utilities*
- **features/** — *Feature files: plain English test scenarios (Gherkin)*
