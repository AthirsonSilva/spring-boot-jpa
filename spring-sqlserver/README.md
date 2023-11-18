# Spring Boot CRUD API with JPA, Hibernate, Lombok and MS SQL Server

This project is a simple Spring Boot RESTful API that performs CRUD (Create, Read, Update, Delete) operations on a database of students. It uses JPA and Hibernate for database persistence, Lombok for simplified Java code, and MS SQL Server as the database management system.

## Features

- Create, read, update, and delete student records
- Store student information in a MS SQL Server database
- RESTful API for easy integration with other systems

## Architecture

The application is built using Spring Boot and consists of several layers:

- `Controller`: Handles incoming HTTP requests and maps them to the appropriate service methods
- `Service`: Contains the business logic for managing student records and interacts with the repository layer
- `Repository`: Provides an abstraction for interacting with the database

The `UserService` class is a Spring service that provides methods for managing user records in the database. It uses a `UserRepository` to interact with the database and provides methods for managing the records on the database. Consequently, the client request are going through the `UserController` before being redirected to `UserService` and back to `UserController` as a JSON response to the client.

![mssql-spring](https://user-images.githubusercontent.com/84593887/231892972-79b453ca-eccb-47f3-8d0b-4fb28adf63b1.png)

## Database

Follow these steps to setup a SQL Server database locally on your machine to run the project.

```yaml
version: "3.7"

services:
  mssql:
    image: mcr.microsoft.com/mssql/server:2022-latest
    container_name: mssql
    environment:
      - ACCEPT_EULA=Y
      - SA_PASSWORD=@Potter77
    ports:
      - "1433:1433"
    volumes:
      - mssql-data:/var/opt/mssql
      - mssql-log:/var/opt/mssql/log
```

Note that the environment variables ACCEPT_EULA and SA_PASSWORD are required by the MS SQL Server image. The SA_PASSWORD should be a strong password of your choice.

Once the container is running, you can connect to the database using any SQL client software and the following connection string:

```spring
jdbc:sqlserver://localhost:1433;databaseName=<database_name>;user=sa;password=<sa_password>
```

Replace <database_name> with the name of your database and <sa_password> with the password you set for the SA user.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- MS SQL Server

### Installation

1. Clone the repository
```sh
git clone https://github.com/yourusername/spring-jpa-hibernate.git
```

3. Build the project

```sh
Copy code
cd spring-jpa-hibernate
mvn clean install
```

4. Run the application

```sh
Copy code
java -jar target/spring-jpa-hibernate-0.0.1-SNAPSHOT.jar
```

The application will start and be available at http://localhost:8080.

## Usage

![Screenshot_20230413_184933](https://user-images.githubusercontent.com/84593887/231892948-4bf21817-739e-41e2-823b-fdf612c2de46.png)

## Contributing
Contributions are welcome! Just fork the project and make a pull request that i will review them.

## License
This project is licensed under the MIT License. See the LICENSE file for details.
