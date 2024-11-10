**Student CRUD API**

A RESTful API for managing student records, built with Spring Boot. This API allows you to create, read, update, and delete student records stored in a local H2 database.

**Features**

Create a new student record

Retrieve all students or a specific student by ID

Update student information

Delete a student by ID

Basic exception handling with custom error messages

**Prerequisites**

Before setting up the application, ensure you have the following installed:

Java 17 or higher
Maven (for dependency management and building the application)
Git (to clone the repository)

**Setup**

**1. Clone the Repository**

git clone https://github.com/hemanth-0609/studentapi.git
cd studentapi


**2. Configure the Database**

The application is configured to use an embedded H2 database by default. No additional setup is required. You can modify the database settings in src/main/resources/application.properties if you want to connect to a different database.

spring.datasource.url=jdbc:h2:mem:studentdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true

**3. Build the Application**

Once you've cloned the repository, navigate into the project directory and build the application using Maven:

mvn clean install

This command will:

Download the necessary dependencies
Compile the application
Run any tests
Package the application into a runnable JAR file
Running the Application

**4.Start the Application**

After building the project, you can start the Spring Boot application with the following command:

mvn spring-boot:run

The application will start on the default port 8080. You should see output in the terminal indicating that the server has started successfully.


**5.API Endpoints**

Method	Endpoint	Description

GET	/api/students	Retrieve all students

GET	/api/students/{id}	Retrieve a specific student by ID

POST	/api/students	Create a new student

PUT	/api/students/{id}	Update an existing student by ID

DELETE	/api/students/{id}	Delete a student by ID

**Example JSON Request Bodies**

Create a Student (POST /api/students)

{
  "studentId": "S123",
  "firstName": "John",
  "lastName": "Doe",
  "middleName": "M",
  "doeStudentId": "DOE123",
  "source": "School",
  "fileName": "file123",
  "householdMemberId": "HM123",
  "dhsClientId": "DHS123456789012345"
}

Update a Student (PUT /api/students/{id})

{
  "studentId": "S123",
  "firstName": "Jane",
  "lastName": "Smith",
  "middleName": "A",
  "doeStudentId": "DOE456",
  "source": "SchoolUpdated",
  "fileName": "file456",
  "householdMemberId": "HM456",
  "dhsClientId": "DHS654321098765432"
}
**6.Running Tests**

This project includes 11 unit tests and integration tests to verify the functionality of the CRUD operations.

**Run Tests**

To execute all tests, use the following Maven command:

mvn test


Run the unit tests in StudentServiceTest, which test the service layer.
Run the integration tests in StudentControllerTest, which test the API endpoints.

**Test Coverage**

The tests cover the following scenarios:

Create a Student: Verifies that a student can be created successfully.

Retrieve All Students: Verifies that all students can be retrieved.

Retrieve a Student by ID: Verifies that a specific student can be retrieved by ID.

Update a Student: Verifies that an existing student's details can be updated.

Delete a Student: Verifies that a student can be deleted.

Handle Non-Existent Student: Verifies that attempting to retrieve or delete a non-existent student returns a 404 Not Found status.

