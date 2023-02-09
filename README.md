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

Project implements JSON Web Token to securely transmit information between parties.

## API documentation
After you run this application you can find documentation here:
```
http://localhost:8080/swagger-ui/
```
### To try this API with Swagger you have to:
1. Send post request to the authentication endpoint with data mentioned below. You can login as manager or employee. This will determine actions which you will be able to perform. 

Login:
```
Manager or Employee
```
Password:
```
Password123
```
2. Copy the authorization header which you can find in response. It should contain Json Web Token and looks like: "Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJNYW5hZ2VyIiwicm9sZXMiOiJST0xFX01BTkFHRVIiLCJpYXQiOjE2NzU4NTQzMTEsImV4cCI6MTY3NjY3NDgwMH0.lz2jAhUdmdvohhRY9oa_BoI1Rbqs-MFJKsuJeaa_0xuprdPJ1BXQ5hV36dcm4Xgr"
3. Click "Authorize" and paste token in appropriate field.
4. After above steps you should be able to interact with endpoints and perform CRUD operations (depending on you are logged as manager or employee). You will see similar window to that which you can see below.


![Api-documentation-screen](https://user-images.githubusercontent.com/96115456/217575795-f20eb3c3-47f9-4af1-b9be-066f8829f1b4.jpg)

## Prerequisites
- Docker (version 20 was used during making this project)

## Build
Clone the source code from Github:
```
https://github.com/mat0714/simple-crm
```
Open terminal, and switch to the root folder of the project. Run following command to build the whole project:
```
docker compose up
```
After above steps application and MySQL database should be up and running. You should be able to perform all operations and have access to API documentation.

## Technologies
<img src="https://img.shields.io/badge/-JAVA 17-red" alt="Java 17" /> <img src="https://img.shields.io/badge/-MAVEN-red" alt="MAVEN" /> <img src="https://img.shields.io/badge/-Sring Boot-red" alt="Spring Boot" /> <img src="https://img.shields.io/badge/-Spring Data-red" alt="Spring Data" /> <img src="https://img.shields.io/badge/-Spring Security-red" alt="Spring Security" /> <img src="https://img.shields.io/badge/-Swagger-red" alt="Swagger" /> <img src="https://img.shields.io/badge/-Lombok-red" alt="Lombok" /> <img src="https://img.shields.io/badge/-JUnit-red" alt="JUnit" /> <img src="https://img.shields.io/badge/-Mockito-red" alt="Mockito" /> <img src="https://img.shields.io/badge/-Rest Assured-red" alt="Rest Assured" /> <img src="https://img.shields.io/badge/-AssertJ-red" alt="AssertJ" /> <img src="https://img.shields.io/badge/-Hamcrest-red" alt="Hamcrest" /> <img src="https://img.shields.io/badge/-Logback-red" alt="Logback" />
