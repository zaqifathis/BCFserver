# Opensource BCFserver

An open-source server implementation of the BIM Collaboration Format (BCF)[REST API](https://github.com/buildingSMART/BCF-API), built with Java and Spring Boot.

## Features

- Full BCF REST API implementation (schema version 3.0, foundation version 1.1)
- BCF XML file import
- OAuth2/JWT security via Keycloak integration

## Technology Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5.7 |
| Security | Spring Security, OAuth2 Resource Server, Keycloak |
| Persistence | Spring Data JPA, SQLite (dev), H2 (test) |
| Build | Maven |
| Container | Docker |

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose (for running with Keycloak)
- A Keycloak instance (for production/dev mode with auth)

## Configuration

The application reads configuration from a `.env` file in the project root (imported automatically). Create a `.env` file with the following variables:
```properties
AUTH_SERVER_URL=http://localhost:8180
AUTH_REALM=your-realm
AUTH_CLIENT_ID=your-client-id
AUTH_CLIENT_SECRET=your-client-secret
AUTH_ADMIN_USER=admin
AUTH_ADMIN_PASSWORD=admin
AUTH_PROVIDER=keycloak
```

## Getting Started

### 1. Start Keycloak

Use the provided Docker Compose file to spin up a local Keycloak instance on port 8180:
```bash
docker-compose up -d
```

After startup, configure a realm and client in Keycloak, then populate your `.env` file accordingly.

### 2. Run the application (development mode)

Development mode uses SQLite for persistence:
```bash
./mvnw spring-boot:run -Pdev
```

The server will start on `http://localhost:8080`.

### 3. Build and run with Docker
```bash
docker build -t bcfserver .
docker run -p 8080:8080 --env-file .env bcfserver
```

## API Overview

The BCF REST API is documented at [BCF REST API](https://github.com/buildingSMART/BCF-API)


## Project Structure
```
src/main/java/de/openfabtwin/
├── auth/              # Authentication utilities
├── configs/           # Spring security and app configuration
├── controllers/       # REST API controllers
├── entities/          # JPA entities
├── exceptions/        # Exception handling
├── generated/         # Auto-generated API interfaces (from OpenAPI spec)
├── mappers/           # Entity ↔ DTO mappers
├── repositories/      # Spring Data JPA repositories
├── services/          # Business logic
├── utils/             # Utility classes
├── BcfServerApplication.java
└── DataLoader.java    # Initial data seeding (only for test profile)
```

## License

This project is licensed under the [MIT License](LICENCE).

## Acknowledgement

This work is funded by the German Federal Ministry for Economic Affairs and Climate Action
(BMWK) through the central innovation programme for small and medium-sized enterprises
(ZIM-program), with funding provided under grant number 16KN106902.
