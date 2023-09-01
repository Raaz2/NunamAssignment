# NunamAssignment
This project aims to handle data from electric taxis efficiently. It involves designing a database for real-time info, using Java to calculate daily stats like travel time and distance, and automating nightly processing. The GitHub repo includes code, diagrams, and clear instructions for setup and use.

# Vehicle Tracking System

The Vehicle Tracking System is a Java Spring Boot application that manages and monitors vehicle data. It provides RESTful APIs for creating, updating, retrieving, and analyzing vehicle information, data points, and statistics.

## Technology Stack

- **Java**: Programming language for backend development.
- **Spring Boot**: Framework for building robust and scalable applications.
- **Spring Data JPA**: Simplifies data access using JPA (Java Persistence API).
- **MySQL**: Relational database for storing vehicle data.
- **Swagger**: API documentation tool for easily testing and exploring endpoints.
- **Lombok**: Reduces boilerplate code by generating getters, setters, and constructors.
- **Jakarta Persistence (JPA)**: API for working with databases using Java EE.
- **Maven**: Dependency management and build tool.

## Prerequisites

Before running the application, ensure that you have the following prerequisites installed:

- Java Development Kit (JDK)
- MySQL Database
- Maven

## Getting Started

1. Clone the repository:

   ```shell
   git clone <repository-url>
   ```

2. Configure the MySQL database settings in `application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_database_username
   spring.datasource.password=your_database_password
   ```

3. Build the project:

   ```shell
   mvn clean install
   ```

4. Run the application:

   ```shell
   mvn spring-boot:run
   ```

The application should now be up and running on `http://localhost:8080`.

## Usage

### Endpoints

- **Create a Vehicle**

  ```http
  POST /api/vehicles/add
  ```

  Request Body:

  ```json
  {
    "vehicleNumber": 10,
    "load": 40.0,
    "dataPoints": [
      {
        "timestamp": "2023-09-01T17:04:20.721000",
        "latitude": 10.0,
        "longitude": 10.0,
        "speed": 190.0,
        "status": "RUNNING"
      }
    ]
  }
  ```

- **Get Vehicle by Number**

  ```http
  GET /api/vehicles/{vehicleNumber}
  ```

- **Update a Vehicle**

  ```http
  PUT /api/vehicles/{vehicleNumber}
  ```

  Request Body:

  ```json
  {
    "vehicleNumber": 10,
    "load": 50.0,
    "dataPoints": [
      {
        "timestamp": "2023-09-01T17:04:20.721000",
        "latitude": 20.0,
        "longitude": 30.0,
        "speed": 150.0,
        "status": "RUNNING"
      }
    ]
  }
  ```

- **Delete a Vehicle by Number**

  ```http
  DELETE /api/vehicles/{vehicleNumber}
  ```

- **Get Vehicle Statistics by Date**

  ```http
  GET /api/vehicles/statistics?date=2023-09-01
  ```

  Returns statistics for all vehicles for the specified date.

### Exception Handling

The application handles exceptions gracefully and provides detailed error messages when errors occur. Custom exception classes, such as `VehicleException`, are used for better error handling.

## Swagger Documentation

Swagger documentation is available to explore and test the API endpoints interactively. Access the Swagger UI at `http://localhost:8080/swagger-ui.html` when the application is running.

## Contributing

Feel free to contribute to this project by opening issues or submitting pull requests. Your contributions are highly appreciated!

