# cucumber-automation-tests
Automated test suite for The Internet using Cucumber-Java-Selenium framework. Tests homepage validation, basic auth, and data table sorting.

## Run tests
```bash
mvn test
```

To run against the live Heroku app instead of local fixtures:
```bash
mvn test -DbaseUrl=https://the-internet.herokuapp.com
```

## What is included
- Practical BDD scenarios in `src/test/resources/features/the_internet.feature`
- Step definitions in `src/test/java/com/connectos/qa/steps/TheInternetSteps.java`
- Page Object Model classes under `src/test/java/com/connectos/qa/pages`
- Headless Chrome setup and Cucumber hooks under `src/test/java/com/connectos/qa/core`
