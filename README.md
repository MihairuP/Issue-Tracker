# Issue-Tracker-
back-end part of basic Issue-tracker using 
PostgreSQL, liquibase, tomcat, Gradle, Spring, 

Its using REST-style http requests to consume/produse JSON to connect to database 
Database would be created via liquibase
Tested with PostgreSQL



http://localhost:8080/Issue-tracker/issue/   - GET request to view all Issues 
http://localhost:8080/Issue-tracker/issue/   - POST request with JSON to create new Issue 
http://localhost:8080/Issue-tracker/issue/{id} - GET request to get specific issue by id
http://localhost:8080/Issue-tracker/delissue/{id} - DELETE request to delete issue by id
http://localhost:8080/Issue-tracker/updateissue/{id} - GET request to update issue by id
Request controller is setting by - /src/main/java/com/mihairu/issueTracker/web/IssueController

Database setting via JDBC - /src/main/java/com/mihairu/issueTracker/config/SpringJdbcConfig
Connect to base by editing build.gradle liquibase settings
DAO/DTO settings src/main/java/com/mihairu/issueTracker/dao/*

Liquibase script to create tables - /src/main/resources/scripts/CREATE_TABLES.sql

Run with tomcatRun
Json with GET requests can be viewed with browser, to post issues use extensions (ex. RESTED/firefox) 
