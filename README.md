# InterationAutomation_Domain Assignment

# Database -Java--postgresql-TestNG-Maven-Json-JavaX-Mail
An integrated automation framework for interacting with below channels:
1. DB
2. Email
2. JIRA

## Author: Gaurav Purwar

Note: JIRA and Email instances has been commented out due to true authorisation requests

#Introduction
The purpose of this document is to demonstrate what is covered as part of Domain Assignment using:
1. Core Java
3. postgresql
4. javax-mail
5. jersey-client
6. Allure test report tool
7. TestNG Report

## Business Use Cases Automated in this framework

**Use cases

Use case 1: createDataBase 
Creates the database based on credentials given


Use case 2: createRecords 
Test Data: [uses 4 different data based records from dataProvide]
1.	Create entries in DB with different Inputs 'Type', 'Message', Email, CreatedDate, ProcessedDate

Use Case 3:
1. check for the records those are new and not processed based on default column value set for "processeddate"
2. checks for Type=Urgent and CreateDate=Yesterday
3. Creates a JIRA task if Point 2 is true
4. Else send an automated email to the customer with default text
5. Checks if None of the records are to process, no emails/JIRA will be created
6. Prints the table with values


## Framework Requirements
* Java Development Kit
* Maven
* TestNG
* javax-mail
* postgresql DB jdbc Drivers (SquirreLSQL)
* allure-testng 2.14.0






## Automation Framework Structure

```
IntegrationAssign
	src.main.java
		DBTAblePrinter
    JIRA
    Email
    RunnerMethods
	src.test.java
		Runner
	src.test.resources
	allure-results
	test-output
		testNG Reports
	pom.xml
	TestNG.xml
		
```




## Use Case Results
* Automated Report automatically generated on test-output, allure-results folder after finished the test execution
* Allure Report generation
* * <--run through command line: under project path-->
* * allure serve allure-results 


* TestNG Default Reports
* * /test-output/emailable-report.html


## Screenshots:


**Run time Allur report:**
Screen references for localhost and allure report generation
Sample Allure report with pass fail stats:

**Command to generate Allure report**
cd directory
allure serve allure-results

<img width="1307" alt="Screen Shot 2022-08-09 at 7 17 13 pm" src="https://user-images.githubusercontent.com/8833241/183619485-e771a511-7d95-4dd6-8309-f82392be1ac0.png">

<img width="1431" alt="Screen Shot 2022-08-09 at 7 18 08 pm" src="https://user-images.githubusercontent.com/8833241/183619635-77e5ed62-0a25-4472-9891-c157af2cb979.png">



Project Folder Structure Screen:

![Screen Shot 2022-08-09 at 7 52 15 pm](https://user-images.githubusercontent.com/8833241/183619878-f51258c2-4160-426a-82a7-eb6690f465e8.png)


**TestNG Reports:**
http://localhost:63342/ListnrProject/test-output/emailable-report.html?_ijt=galphhcmsf0s9gnga0qhf9cgpm&_ij_reload=RELOAD_ON_SAVE

<img width="1418" alt="Screen Shot 2022-08-09 at 7 27 14 pm" src="https://user-images.githubusercontent.com/8833241/183619706-78898140-1ee0-4d46-af67-023dd35756b5.png">




