# Spring_App
Simple Spring MVC App implementing an API to a MySQL Database 

Application implements an API to access a MySQL Database with the next endpoints:

*http:localhost:8080/v1/api/person GET -> get all database entries
*http:localhost:8080/v1/api/person POST -> insert database row
*http:localhost:8080/v1/api/person/{id} GET -> get entry based on id (UUID format)
*http:localhost:8080/v1/api/person/{id} DELETE-> delete entry based on id
*http:localhost:8080/v1/api/person/{id} PUT -> update entry based on id
*http:localhost:8080/v1/api/person/{id} PATCH -> partially update entry based on id with RequestParam -> "updateMask": field1,field2,field3...

!Under the **resource** folder, the **application.properties** file defines the connection parameters to a remotely to the database. Application has a simple MySQL script attached to define the database 

To use the application simply run the project in an IDE or build the JAR and make requests to the specific endpoints




