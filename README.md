# Spring_App
Simple Spring MVC App implementing an API to a MySQL Database 

## Description ##
Application implements an API to access a MySQL Database with the next endpoints:

First Header  | 
------------- |
**GET** http:localhost:8080/v1/api/person -> get all database entries  |
**POST** http:localhost:8080/v1/api/person -> insert database row |
**GET** http:localhost:8080/v1/api/person/{id} -> get entry based on id (UUID format) |
**DELETE** http:localhost:8080/v1/api/person/{id} -> delete entry based on id |
**PUT** http:localhost:8080/v1/api/person/{id} -> update entry based on id |
**PATCH** http:localhost:8080/v1/api/person/{id} -> partially update entry based on id with 
**RequestParam** for PATCH -> "updateMask": field1,field2,field3... | 

The application has two configurations for the DAO:
* **LocalPersonDataAccessService.java** class for using an ArrayList to save data while the application is running
* **MySQLPersonDataAccessService.java** class for using MySQL as a datasource


The Application uses Spring Mapping for ORM, Java 16, lombok, tomcat application server

## Configuration ##

!Under the **resource** folder, the **application.properties** file defines the connection parameters to a database. Config details to a remotely hosted database have been provided to test the API out-of-the-box

Application has a MySQL script attached as well to create the database locally

## Running the application ##
To use the application simply run the project in an IDE or build the JAR, run the app to start Tomcat and make requests to the specific endpoints




