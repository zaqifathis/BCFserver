# Opensource BCF-API server

An open-source server implementation of the BIM Collaboration Format (BCF) [REST API](https://github.com/buildingSMART/BCF-API), built with Java and Spring Boot.

## Features

- Full BCF REST API implementation (schema version 3.0, foundation version 1.1)
- OAuth2/JWT security via Keycloak integration
- BCF XML file import

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
BCF_IMAGE_VERSION={bcf-image-version}
AUTH_PROVIDER=keycloak
AUTH_ADMIN_USER={admin}
AUTH_ADMIN_PASSWORD={admin-password}
AUTH_SERVER_URL=http://localhost:8180
AUTH_REALM={your-realm}
AUTH_CLIENT_ID={your-client-id}
AUTH_CLIENT_SECRET=placeholder
```

## Getting Started
Requires PowerShell (dev scripts are `.ps1`).

```bash
make dev-up
```

This starts Keycloak, waits for it to become healthy, runs `keycloak-init` to provision the realm/client, writes the generated `AUTH_CLIENT_SECRET` into `.env`, then starts `bcf-app`. No manual Keycloak setup needed — just create `.env` first (see Configuration above; `AUTH_CLIENT_SECRET` can be left as a placeholder).

The server will be available at `http://localhost:8181`.

To tear everything down (containers, Keycloak data, generated secret):
```bash
make dev-reset
```

(`make dev-up` / `make dev-reset` wrap `dev-up.ps1` / `dev-reset.ps1`; you can also run those scripts directly with `powershell -File dev-up.ps1`.)

## API Overview

The BCF REST API is documented at [BCF REST API](https://github.com/buildingSMART/BCF-API)

### Additional Endpoint
1. ``/import``  
Import BCF file

The official BCF-API spec has no endpoint to create a project from data — support for it was dropped after v1.0, since project creation is expected to happen in each server's own native system rather than via the generic API ([here](https://github.com/buildingSMART/BCF-API/issues/176#issuecomment-389455354)). `/import` is this server's own extension to fill that gap.

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
