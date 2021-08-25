# Survey Web Application

## Description
<p>&nbsp; This is a survey web application.</p>
<p>&nbsp; The main purpose is for users to browse the list of available topics and submit their opinions. Different opinions are always important to improve somethings.</p>
<p>&nbsp; There are many options but they are not allowed for all users. There are two user roles which are ADMIN_USER and END_USER.
If an user register to the application, he/she is an END_USER. There is only one ADMIN_USER and it is created on the start of the application.</p>

##### ADMIN_USER is allowed to
- Create new users 
- Create new survey topics
- Update existing survey topics
- Delete survey topics
- View the results for survey topics

##### END_USER is allowed to
- Register to the application
- Login to the application
- Browse the list of available topics and submit their opinions. 
- See the survey results for the topics

## How to Setup Application
<p>&nbsp; First you need to install postgresql for database. Also, you need to add bin and lib files of postgresql to path from environment variables.</p>
<p>&nbsp; You can follow below link. But, you should set the postgres password as '12345'. If not, you should update the datasource username and password according to your postgresql. You can do it from src/main/resources/application.properties.</p>

[How to Install PostgreSQL & pgAdmin 4 on Windows 10](https://www.youtube.com/watch?v=e1MwsT5FJRQ&ab_channel=ProgrammingKnowledge)

<p>&nbsp; After the install, open command promt and write the following</p>
- psql -U postgres -h localhost
- 12345 (write password for default user postgres)
- create database survey_app; (Then you should see CREATE DATABASE)

<img src = "/images/cmd.jpg">


---

by [Hakan Kahraman](https://github.com/hakankhrmn)
