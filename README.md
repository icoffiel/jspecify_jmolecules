# JSpecify Platform

A Spring Boot application for managing video game platforms and manufacturers.

## Technologies Used

- **Java 17** - Primary programming language
- **Spring Boot** - Application framework
- **Spring Data JPA** - Data access layer
- **H2 Database** - In-memory database for development
- **Gradle** - Build automation and dependency management
- **JUnit 5** - Testing framework
- **Mockito** - Mocking framework for unit tests

## Prerequisites

- Java 17 or higher
- Gradle 7.0 or higher
- Docker for containerization and dependencies

## Getting Started

### Running Locally

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/jspecify.git
   cd jspecify
   ```

2. **Build the application**
   ```bash
   ./gradlew build
   ```

3. **Run the application**
   ```bash
   ./gradlew bootRun
   ```
   The application will start on `http://localhost:8080`

### Running with Docker

1. **Run the application**
   ```bash
   ./gradlew bootTestRun
   ```

This will use Test containers to bring up a database and run the application.

## Testing

To run the test suite:

```bash
./gradlew test
```
