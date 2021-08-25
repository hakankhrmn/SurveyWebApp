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
- psql -U postgres -h localhost <br/>
- 12345 (write password for default user postgres) <br/>
- create database survey_app; (Then you should see CREATE DATABASE)
<br/><br/>
<img src = "/images/cmd.jpg">
<br/>

<p>&nbsp; Then you can clone the project.</p>
<p>&nbsp; After the clone, git bash to the project folder and write the following</p>
- mvn clean install <br/>
- cd target <br/>
- java -jar surveyapp-0.0.1-SNAPSHOT.jar <br/><br/>

That's it. Now you can [click this link](http://localhost:8080/swagger-ui/) and test the project.
<br/><br/>
<img src = "/images/1.jpg">

## How to Test Application
<br/><br/>
<p>&nbsp; First you need to login to use methods because they are authorized. ADMIN_USER can use all methods. So, it is better to test with AdMIN_USER</p>
- Login with username : admin@gmail.com and password: admin from auth-controller.
<img src = "/images/2.jpg">
- Copy the token.
<img src = "/images/3.jpg">
- Click Authorize button and write 'Bearer ' and paste token. Then Authorize.. (Bearer + space + token)
<img src = "/images/4.jpg">

Now you can test all the methods. If you want to test END_USER, you can register and apply the same processes with ADMIN_USER.

---

by [Hakan Kahraman](https://github.com/hakankhrmn)
