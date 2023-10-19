## About
Automation Exercise

Automation Exercise WebPage Automation
Here we are executing all the 26 test cases of Automation Exercise web page for which to overcome the third-party pop-ups we used adblocker and the respective file is downloaded under Extensions. All the possibilities of Add, edit, delete functions of both immigrations and attachments are done.

## Concepts Included

* Sequential test runs
* Dependency injection
* Page Object pattern
* Common web page interaction methods
* Commonly used test utility classes

## Tools

* Maven
* JRE- 1.7
* TestNG
* Eclipse
* WebDriverManager
* Apache POI
* Selenium

## Requirements

In order to utilise this project you need to have the following installed locally:

* Eclipse
* Chrome Browser - V114 and above
* Java SE-17
* FilesExcelR Project-2.xlsx should be dowloaded to your local drive.


## Usage

The project is broken into separate modules for UI.
Use Eclipse to run all modules

## Reporting

Reports for each module are written into their respective /target directories after a successful run.
file:///C:/Users/smita/eclipse-workspace/ExcelR_Project_2/test-output/passed.png
UI acceptance tests result in a HTML report.
In the case of test failures, a screen-shot of the UI at the point of failure is embedded into the report.

## AutomationExercise_Data File: 
This file has the test data being used to execute the script validations.
## Folder Structure: 

## implimentationOfTestCases : 
This is the test class implemented using TestNG tests to validate all the testcases of the project, this class calls the page object class methods using the corresponding objects.

## pageObject: 
This folder has all the reusable base files as follows: 
SignupLogin.java, HomePageOfAutomation.java, ProductsPageOfAutomation.java, CategoriesOfAutomation.java, PaymentGateway.java.
