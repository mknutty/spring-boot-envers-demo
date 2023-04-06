# Hibernate Envers and Spring TransactionalEventListener example#

This Project demonstrates basic usage of [Hibernate Envers] (https://hibernate.org/orm/envers/) with Spring Boot. 
It also Demonstrates the usage of TransactionalEventListener to perform tasks after a transaction has been committed.

## How it works
The application will start and will create, update and delete items. 
As it does that, revisions of the entities will be created and events sent to the event listener will be logged.
The application will continue to run as so that the embedded database can be viewed to see the Entities and related Revision Entities

## Things to Note
1. Spring Data Envers is included for some basic Revisions querying.
2. DemoCustomRevision allows us to add data to the Revision Entity and DemoRevisionListener sets the data.

## Other useful info

The application uses an Embedded H2 database. It is created when the db starts and is dropped when the application stops. 
[Spring boot and embedded databases] (https://docs.spring.io/spring-boot/docs/2.1.13.BUILD-SNAPSHOT/reference/html/boot-features-sql.html#boot-features-embedded-database-support)

The database can be viewed by opening the browser to http://localhost:8080/h2-console 
and using the  JDBC URL logged in the console.



