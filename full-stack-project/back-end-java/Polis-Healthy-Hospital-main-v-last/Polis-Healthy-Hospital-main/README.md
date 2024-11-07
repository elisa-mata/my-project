# Hospital app

### What to install first

First java needs to be installed: 
For windows install java 22:

https://docs.oracle.com/en/java/javase/22/install/installation-jdk-microsoft-windows-platforms.html

Also make sure to setup JAVA_HOME env variable in windows ( needed to run maven )

## How to run
`mvnw clean package && java -jar target/com.hospitalapp-2.3.0.jar`

## Routes

### localhost:8080/api

### GET api/department

Returns list of departments ( optional query parameter api/department?search={{ search }})

#### POST api/department 
creates a department and returns it

Insert body:
`{ "name": "test", "code": "1234" }` -- creates a new department

Update body:

`{ "name": "test", "code": "1234", id: 1234 }` - updates the department with id 1234

### PUT api/department/{{ departmentId }}

updates a department with department id

body:
`{ "name": "test", "code": "1234" }`