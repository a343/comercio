# Comercio

## Description

This microservice is designed to retrieve the prices for a given product in an e-commerce system. It allows querying based on the product ID, brand ID, and application date to return the relevant pricing information.

## Objective

The goal of this project is to build a Spring Boot application that provides a REST API for querying product prices. The API should:

- **Accept the following input parameters**:
  - **Application date**: The date for which the price is being queried.
  - **Product identifier**: The unique identifier of the product.
  - **Brand identifier**: The identifier for the chain or brand.

- **Return the following output data**:
  - Product identifier
  - Brand identifier
  - Applicable rate (discount or promotion)
  - Application start and end dates for the price
  - Final price to apply

## Author

**Antonio Diaz**

## Tech Stack

This project uses the following technologies:

- **Spring Boot 3**: For creating the microservice framework.
- **MapStruct**: For object mapping between DTOs and entities.
- **Lombok**: To reduce boilerplate code (getters, setters, etc.).
- **OpenAPI**: For API documentation and code generation.
- **H2 Database**: In-memory database for testing purposes.
- **JUnit**: Unit testing framework.
- **Mockito**: For mocking dependencies in tests.
- **MockMvc**: For testing REST endpoints.

## Prerequisites

Make sure you have the following installed:

- **JDK 17+**: Required to compile and run the project.
- **Maven 3.8+**: For building and managing the project dependencies.

## Installation

Follow these steps to set up and run the application:

1. Clone the repository:

    ```bash
    git clone https://github.com/a343/comercio.git
    cd comercio
    ```

2. Build the project using Maven:

    ```bash
    mvn clean install
    ```

3. Run the application:

    ```bash
    mvn spring-boot:run
    ```

4. Access the API documentation in your browser at: [Swagger UI](http://localhost:8080/comercio/swagger-ui/index.html)

## API Usage

Here’s an example of how to query the API using `curl`:

### Request Example:

```bash
curl -X 'GET' 'http://localhost:8080/comercio/price/35455/1/?applicationDate=2020-12-29T23%3A59%3A59' \
-H 'accept: application/json'
```
This example queries for the price of the product with ID 35455 for the brand with ID 1 at the application date 2020-12-29T23:59:59.

### Testing
To run the unit tests:
```bash
mvn test
```
The project uses JUnit for unit tests, Mockito for mocking dependencies, and MockMvc to test the REST controllers.

### Additional Info

For a complete list of available API endpoints and their details, check the OpenAPI documentation at  [Swagger UI](http://localhost:8080/comercio/swagger-ui/index.html)