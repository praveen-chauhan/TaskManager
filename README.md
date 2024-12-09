# TaskManager Application

## Introduction

This application is a simple task management system built with Spring Boot, JPA, and PostgreSQL. It allows users to create, update, delete, and view tasks. Each task is assigned to a user, and users can manage their own tasks.

## Prerequisites

- Java 11 or later
- Maven
- PostgreSQL

## Setup Instructions

### 1. Clone the Repository

```sh
git clone https://github.com/praveen-chauhan/TaskManager.git
cd TaskManager
```


### 2. Install PostgreSQL

If you don't have PostgreSQL installed, download and install it from the [PostgreSQL Downloads page](https://www.postgresql.org/download/).

### 3. Create Database and User

After installing PostgreSQL, follow these steps:

#### a. Create a Database

Open the PostgreSQL command line interface (psql) or use pgAdmin and execute the following command to create the database:

```sql
CREATE DATABASE taskmanager;
```
#### b. Create a User

Create a new user with a password:

```sql
CREATE USER taskuser WITH PASSWORD 'password';
```
### 3. Configure Application Properties

Update the PostgreSQL connection settings in `src/main/resources/application.properties` with the following values:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=taskuser
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.jdbc.time_zone=UTC
spring.jpa.properties.hibernate.format_sql=true
server.port=8080
```
### 4. Build and Run the Application

#### a. Build the Application

In your project directory, open a terminal and run the following Maven command to build the application:

Note: Lombok have been used in this program, it may cause error
```bash
mvn clean install
```
#### b. Run the Application
After the build is complete, run the application with the following Maven command:

```bash
mvn spring-boot:run
```
## Access the Application

Once the application is running, you can access the following API endpoints:

### Users
- **Create User**: `POST /api/users`
- **Get All Users**: `GET /api/users`
- **Get User by ID**: `GET /api/users/{id}`
- **Update User**: `PUT /api/users/{id}`
- **Delete User**: `DELETE /api/users/{id}`
- **Get Tasks by User ID**: `GET /api/users/{id}/tasks`

### Tasks
- **Create Task**: `POST /api/tasks`
- **Get All Tasks**: `GET /api/tasks`
- **Get Task by ID**: `GET /api/tasks/{id}`
- **Update Task**: `PUT /api/tasks/{id}`
- **Delete Task**: `DELETE /api/tasks/{id}`

## Assumptions and Considerations

### Entities:
- **Users** and **Tasks** are the main entities. Each task is assigned to a user.
- A user can have multiple tasks, but each task can only be assigned to one user.

### Validation:
- Basic validation is performed on the user and task fields (e.g., not blank, valid status).

### Error Handling:
- Basic error handling is implemented to manage invalid requests and server errors.
#### 1. User Not Found
- Scenario: When a user is requested but does not exist in the database.
- HTTP Status Code: 404 Not Found

  #### Response Body:
  ```json
  {
  "error": "User not found with id: {id}"
  }
  ```
#### 2. Task Not Found
- Scenario: When a task is requested but does not exist in the database.
- HTTP Status Code: 404 Not Found

  #### Response Body:

  ```json
  {
  "error": "Task not found with id: {id}"
  }
  ```
#### 3. Validation Errors
- Scenario: When a request contains invalid or missing required fields.
- HTTP Status Code: 400 Bad Request

  #### Response Body:

  ```json
  {
    "errors": {
    "field": "Validation error message"
    }
  }
  ```
#### 4. General Server Errors
- Scenario: When an unexpected server error occurs.
- HTTP Status Code: 500 Internal Server Error

  #### Response Body:
  ```json
  {
  "error": "An unexpected error occurred. Please try again later."
  }
  ```

### Security:
- For simplicity, security features (e.g., authentication, authorization) are not implemented in this version.
- Consider adding Spring Security for authentication and authorization in future iterations.

### Testing:
- Basic unit tests are included in readme file.

### Future Improvements:
- Implement security features.
- Add pagination for API endpoints.
- Enhance validation and error handling.
- Improve documentation.
## Test Cases for API Endpoints

### 1. Create a User
- **Method**: POST
- **URL**: `/api/users`
- **Headers**:
  - Content-Type: application/json

- **Request Body**:
  ```json
  {
    "firstName": "John",
    "lastName": "Doe",
    "timezone": "America/New_York",
    "isActive": true
  }
  ```


### 2. Get All Users
- **Method**: GET
- **URL**: `/api/users`
- **Headers**:
  - Content-Type: application/json

#### Expected Response:
- **Status Code**: 200 OK
- **Response Body**:
  ```json
  [
    {
      "id": 1,
      "firstName": "John",
      "lastName": "Doe",
      "timezone": "America/New_York",
      "isActive": true,
      "taskIds": []
    }
  ]
  ```
### 3. Create a Task
- **Method**: `POST`
- **URL**: `/api/tasks`
- **Headers**:
  - `Content-Type: application/json`

- **Request Body**:
  ```json
  {
    "title": "Complete Assignment",
    "description": "Finish the Spring Boot project",
    "status": "Pending",
    "assignedTo":{
      "id":1
    }
  }
  ```
  #### Expected Response:

- **Status Code**: 201 Created
- **Response Body**
  ```json
  {
      "id": 6,
      "title": "Complete Assignment",
      "description": "Finish the Spring Boot project",
      "status": "Pending",
      "createdAt": "2024-12-09T10:47:49.474273200Z",
      "updatedAt": "2024-12-09T10:47:49.474273200Z",
      "assignedToId": 1,
      "timezone": "America/New_York"
  }
  ```
### 3. Create a Task with missing required field
- **Method**: `POST`
- **URL**: `/api/tasks`
- **Headers**:
  - `Content-Type: application/json`

- **Request Body**: Empty

#### Expected Response:

- **Status Code**: 404 Not Found
- **Response Body**:
  ```json
  {
    "title": "Title is mandatory",
    "assignedTo": "Assigned To is mandatory"
  }
  ```
### 4. Get Tasks by User ID
Retrieve all tasks assigned to a specific user.

- **Method**:`GET`
- **URL**:`/api/users/{id}/tasks`
- **Headers**:
  - `Content-Type": "application/json`

#### Expected Response
- **Status Code**: 200 OK

- **Response Body**:

  ```json
  [
    {
    "id": 1,
    "title": "Complete Assignment",
    "description": "Finish the Spring Boot project",
    "status": "Pending",
    "createdAt": "2024-12-07T09:00:00Z",
    "updatedAt": "2024-12-07T09:00:00Z",
    "assignedToId": 1,
    "timezone": "America/New_York"
    }
  ]
  ```
### 5. Update a Task
- **Method**: `PUT`
- **URL**: `/api/tasks/{id}`
- **Headers**:
  - `Content-Type: application/json`

- **Request Body**:
  ```json
  {
    "title": "Complete Assignment",
    "description": "Finish the Spring Boot project",
    "status": "In Progress",
    "assignedTo":{
      "id":1
    }
  }
  ```
#### Expected Response
- **Status Code**: 200 OK

- **Response Body**:
  ```json
  {
  "id": 6,
  "title": "Complete Assignment",
  "description": "Finish the Spring Boot project",
  "status": "In Progress",
  "createdAt": "2024-12-09T10:47:49.474273Z",
  "updatedAt": "2024-12-09T10:54:42.944968300Z",
  "assignedToId": 1,
  "timezone": "America/New_York"
  }
  ```
### 6. Update user's information
- **Method**: `PUT`
- **URL**: `/api/users/{id}`
- **Headers**:
  - `Content-Type: application/json`

- **Request Body**:
  ```json
  {
  "firstName": "Jane",
  "lastName": "Doe",
  "timezone": "America/Los_Angeles",
  "isActive": true
  }
  ```
### 7. Delete a task
- **Method**: `DELETE`
- **URL**: `/api/tasks/{id}`
- **Headers**:
  - `Content-Type: application/json`
#### Expected Response
- **Status Code**: 204 No Content
### 8. Delete a user
- **Method**: `DELETE`
- **URL**: `/api/users/{id}`
- **Headers**:
  - `Content-Type: application/json`
#### Expected Response
- **Status Code**: 204 No Content

## About the Author
This application was created and maintained by **Praveen Chauhan**.  
Feel free to reach out for collaboration or support.
