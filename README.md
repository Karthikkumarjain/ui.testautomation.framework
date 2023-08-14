
# Test Automation with Selenium

This is a sample test automation framework using POM in Selenium. The framework is built on BDD approach with cucumber.




## Prerequisite

Since this is based on Selenium with Java, so the pre-requisite is to have Java installed on our system. Once Java is installed.

* Clone the repo
* Build the project with maven
* See the Run Tests section on how to run the tests
## Structure

The primary directories are as follows


* .github : Contains the .yaml files for Github Actions CI.
* driverfactory : Contains initialization of driver with thread local class to run threads safely.
* constants : Contains constants class that has constant paths used in the framework.
* utils : Contains different utils classes that serves different purposes within the framework.
* testdata : Contains the different data providers.
* pages : Contains page classes along with the functions to be performed.
* features : Contains feature files that has the scenarios and test steps in Gherkin language.
* stepdefns : Contains  ApplicationHooks and other step definitions class for each of the steps in feature file.
* testrunner : Contains the testrunner class file which is used to run the tests. 
## System Under Test

The system under test is : https://marsair.recruiting.thoughtworks.net/candidate-karthik9629846380@gmail.com
## Libraries Used

* Selenium : For driving the UI tests
* Cucumber : To write scenarios and tests in Gherkin language .
* lombok : To avoid boiler plate code.
* assertj : Used for assertions within the tests.
## Config

* config.properties - Used to maintain global level properties.
## Run Tests

To run the tests in local use

```bash
  mvn clean test -Psystem-tests
```

## Reports

* Reports are hosted on the Github pages.