# Simple CRM

## What is this?
This project is backend application for Customer Relationship Managment. Main part is REST API which allows to create, read, update and delete information about:
- Customer
- Company- place where customer is working
- Employee- person which will be potential users of Simple CRM and will be in contact with Customer
- Event- contact details between Employee and Customer

Application is secured. Some actions are allowed only by proper user. There are two types of them:
- Manager (allowed to: create, read, update and delete all entities)
- Employee (allowed to: create, read, update all entities)

Project implements JSON Web Token to securily transmit information between parties.

## Technologies
Java17, Maven, Spring: Boot, Data, Security, Swagger, Lombok, JUnit, Mockito, Rest Assured, AssertJ, Hamcrest, Logback
