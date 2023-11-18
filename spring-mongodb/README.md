# Spring MongoDB Student Registration API

This project is a Spring Boot RESTful API for managing student registrations with a connection to a MongoDB database.

## Features

- Create, read, update, and delete student records
- Store student information in a MongoDB database
- RESTful API for easy integration with other systems

## Architecture

The application is built using Spring Boot and consists of several layers:

- `Controller`: Handles incoming HTTP requests and maps them to the appropriate service methods
- `Service`: Contains the business logic for managing student records and interacts with the repository layer
- `Repository`: Provides an abstraction for interacting with the MongoDB database

The `StudentService` class is a Spring service that provides methods for managing student records in the database. It uses a `StudentRepository` to interact with the database and provides methods for retrieving all students, finding a student by ID or email, creating a new student record, and updating an existing student record.

![spring-mongodb](https://user-images.githubusercontent.com/84593887/231888662-fc8c288e-b160-4b04-a89e-5275b1c2db8c.png)

The application uses several model classes to represent the data it manages. The `Address` class represents a student's address and has fields for the country, city, street, and zip code.

## Database

The MongoDB database contains a collection of student records with various fields such as `firstName`, `lastName`, `email`, `password`, `gender`, `address`, `favoriteSubjects`, `totalSpentInBooks`, and `createdAt`. The `address` field is an embedded document that contains fields for the student's country, city, street, and zip code.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven
- MongoDB

### Installation

1. Clone the repository
git clone https://github.com/yourusername/spring-mongodb.git


2. Build the project
cd spring-mongodb mvn clean install


3. Run the application
java -jar target/spring-mongodb-0.0.1-SNAPSHOT.jar


The application will start and be available at `http://localhost:8080`.

## Usage

The API provides the following endpoints for managing student registrations:

- `GET /students`: Retrieve a list of all students
- `POST /students`: Create a new student record
- `GET /students/{id}`: Retrieve a specific student record by ID
- `PUT /students/{id}`: Update an existing student record by ID
- `DELETE /students/{id}`: Delete an existing student record by ID

![Screenshot_20230413_181428](https://user-images.githubusercontent.com/84593887/231887429-893ee029-b97a-4f79-baf3-4ae0bdbddaac.png)

## Contributing

Contributions are welcome! Please see the [CONTRIBUTING.md](CONTRIBUTING.md) file for details on how to contribute to this project.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
