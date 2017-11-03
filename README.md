# Clustered Data Warehouse - Machine Problem

Stack: Spring Boot, Java 8, Hibernate, MySQL


Suppose you are part of a scrum team developing data warehouse for Bloomberg to analyze FX deals. One of customer stories is to import deals details from files into DB.
The requested performance is to be able to import the file containing 100,000 records in less than 5 seconds.


Request logic as following :

* File format is CSV contains the following fields (Deal Unique Id, From Currency ISO Code "Ordering Currency", To Currency ISO Code, Deal timestamp, Deal Amount in ordering currency).
* Validate row structure.(e.g: Missing fields, Type format..etc. We do not expect you to cover all possible cases but we'll look to how you'll implement validations)
* Valid rows should be stored in table/document, with reference to source file name .
* Invalid rows should be stored into another table/document, with reference to source file name.
* The DB contains another table to maintain accumulative count of deals per Ordering Currency "Columns : Currency ISO Code, CountOfDeals ", so upon completion of importing process the system should increase count of deals per currency.
* System should not import same file twice.
* No rollback allowed, what every rows imported should be saved in DB.

Technical Specs :

* Access to DB should be through JPA.
* For DB type, you can select between (MySql or MongoDB)
* Provide a web interface for uploading files and inquire about results "using filename" following web applications 3 tier architecture. Spring Batch is not allowed.
* Provide a web interface for inquire about results "using filename" .·
* We expect you to generate sample data set to use it during development
* We expect the system to display a summary report for each file after process is finished; summary may contain process duration, number of imported deals and number of invalid records.
* We expect you to generate sample data set to use it during development.



# Project Setup
* Setup MySQL on your local environment. Create schema "deals_data".
* to run the project, go to project directory and open terminal. run 'mvn spring-boot-run' cmd
* endpoints
  - http://localhost:8446/upload
  - http://localhost:8446/summary
