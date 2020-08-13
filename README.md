# Getting Started

### General Description
This project was implemented as a test task for Uleska project.
The goal was to implement minimal viable prototype for scheduled task managed via REST.

There are present two main enitites in the project: Users and Jobs.
Manipulation for both of them is executed with REST endpoints. 

Technical stack includes 
1. Java 11 
2. Maven 3+
3. Spring Boot
4. Spring IoC
5. Spring MVC 

Project doesn't use any persistance storage for keeping information about entities. 
Only in memory Java structures.    

Scheduler prototype was implemented with keeping in mind efficiency approaches. 
The logic startes a new thread that is responsible for taking and executing tasks.
Task itself are organized as a list, sorted by execution time. 
This allows thread to know when to 'sleep' and when 'to wake up' for starting the job without wasting processor time for irrelevant checks. 

### Start Project
Make sure you have JRE 11+ installed on your instance.
To start an instance run the following command:
> ./mvnw spring-boot:run

### Further Improvements 
The project represent concept and has numerous places for improvement.
Including but not limmitting:
- adding logs, 
- numerous places with possible NPEs, 
- cover logic with unit tests, 
- actual validation for user/job relationship  
- proper HTTP status codes in responses
- consider of improving job scheduler inner structure. 
- etc...
