# Student CRUD Application (Spring Boot)

### Overview
A Spring Boot RESTful CRUD application for managing student records.
The project follows Controller–Service–Repository architecture and uses
Spring Data JPA (Hibernate) for ORM, MySQL as the database, and Postman for API testing.


# API Endpoints & Sample Responses

### Create Student
**POST** `http://localhost:8080/students`

**Request Body**
```json
{
  "name": "Sourabh",
  "email": "sourabh@gmail.com",
  "course": "Spring Boot",
  "age": 22
}
```
Response (201 Created)

```json

{
  "id": 1,
  "name": "Sourabh",
  "email": "sourabh@gmail.com",
  "course": "Spring Boot",
  "age": 22
}
```
### Read Student

Get All Students
| GET `http://localhost:8080/students`

Response (200 OK)

```json

[
  {
    "id": 1,
    "name": "Sourabh",
    "email": "sourabh@gmail.com",
    "course": "Spring Boot",
    "age": 22
  }
]
```
<br>

Get Student By ID
| GET `http://localhost:8080/students/{1}`

Response (200 OK)

```json

{
  "id": 1,
  "name": "Sourabh",
  "email": "sourabh@gmail.com",
  "course": "Spring Boot",
  "age": 22
}
```
### Update Student
PUT `http://localhost:8080/students/{1}`

Request Body

```json

{
  "name": "Sourabh Kumar",
  "email": "sourabh@gmail.com",
  "course": "Spring Boot + MySQL",
  "age": 23
}
```
Response (200 OK)

```json

{
  "id": 1,
  "name": "Sourabh Kumar",
  "email": "sourabh@gmail.com",
  "course": "Spring Boot + MySQL",
  "age": 23
}
```
### Delete Student
DELETE `http://localhost:8080/students/{1}`

Response (200 OK)

```json
Student deleted with id 1
```

## Testing

This project includes both unit testing and integration testing.

### Unit Testing
- Service layer is tested using **JUnit 5** and **Mockito**
- Repository layer is mocked
- CRUD business logic is tested in isolation

### Integration Testing
- Controller endpoints are tested using **Spring Boot Test** and **MockMvc**
- Real HTTP requests are simulated
- End-to-end flow from Controller → Service → Repository → Database is verified

All tests pass successfully.
<br><br>
<img width="1919" height="1079" alt="Screenshot 2026-01-06 172150" src="https://github.com/user-attachments/assets/8aca8afb-ee59-40b6-b3b7-35865805f01b" />

