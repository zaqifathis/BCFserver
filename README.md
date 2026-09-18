# Opensource BCF-API server

An open-source server implementation of the BIM Collaboration Format (BCF) [REST API](https://github.com/buildingSMART/BCF-API), built with Java and Spring Boot.

## Features

- Full BCF REST API implementation (schema version 3.0, foundation version 1.1)
- Provider-agnostic OAuth2/JWT security (any standard OIDC provider, or disabled)
- BCF XML file import

## API Overview

The BCF REST API is documented at [BCF REST API](https://github.com/buildingSMART/BCF-API)
### Additional Endpoint
1. ``/import``  
   Import BCF file

The official BCF-API spec has no endpoint to create a project from data — support for it was dropped after v1.0, since project creation is expected to happen in each server's own native system rather than via the generic API ([here](https://github.com/buildingSMART/BCF-API/issues/176#issuecomment-389455354)). `/import` is this server's own extension to fill that gap.


## Technology Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5.7 |
| Security | Spring Security, OAuth2 Resource Server (optional, provider-agnostic) |
| Persistence | Spring Data JPA, PostgreSQL |
| Build | Maven |
| Container | Docker |

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose

## Configuration

The application reads configuration from a `.env` file in the project root (imported automatically). Create a `.env` file with the following variables:

```properties
# --- Required ---
BCF_IMAGE_VERSION=bcf-image-version
DB_NAME=bcf
DB_USER=bcf
DB_PASSWORD=bcf

# --- Auth (disabled by default) ---
# When AUTH_ENABLED=false (default), all requests are accepted as anonymous with WRITE access.
AUTH_ENABLED=false
```

## Development

Start PostgreSQL and the BCF server:

```bash
docker-compose up -d
```

The server will be available at `http://localhost:8181` (maps to container port 8080). `docker-compose.yml` also has a commented-out Keycloak service — uncomment it to run OIDC locally (see [Running with an OIDC provider](#running-with-an-oidc-provider-optional) below).

To rebuild after code changes:
```bash
docker-compose up -d --build
```

To tear everything down including volumes:
```bash
docker-compose down -v
```

### Running locally

With Docker Compose running (for PostgreSQL), start the application directly:

```bash
./mvnw spring-boot:run
```

Or with explicit database parameters:

```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments="--DB_HOST=localhost --DB_NAME=bcf --DB_USER=bcf --DB_PASSWORD=bcf"
```

### Running with an OIDC provider (optional)

Any standard OIDC/OAuth2 provider works. [Keycloak](https://www.keycloak.org/) is a popular open-source option — `docker-compose.yml` includes a commented-out Keycloak service block usable as local identity provider. Steps below use Keycloak as example.


- In `.env`, add additional properties below:

```properties
AUTH_ENABLED=true

AUTH_SERVER_URL=http://localhost:8180
AUTH_REALM=your-realm
AUTH_ADMIN_USER=admin
AUTH_ADMIN_PASSWORD=admin
AUTH_CLIENT_ID=your-client-id
AUTH_CLIENT_SECRET=your-secret
AUTH_ROLES_CLAIM=realm_access.roles

spring.security.oauth2.resourceserver.jwt.issuer-uri=${AUTH_SERVER_URL}/realms/${AUTH_REALM}
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=${AUTH_SERVER_URL}/realms/${AUTH_REALM}/protocol/openid-connect/certs
spring.security.oauth2.client.registration.oidc.client-id=${AUTH_CLIENT_ID}
spring.security.oauth2.client.registration.oidc.client-secret=${AUTH_CLIENT_SECRET}
spring.security.oauth2.client.registration.oidc.scope=openid,profile,email
spring.security.oauth2.client.registration.oidc.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.oidc.redirect-uri={baseUrl}/login/oauth2/code/{registrationId}
spring.security.oauth2.client.provider.oidc.authorization-uri=${AUTH_SERVER_URL}/realms/${AUTH_REALM}/protocol/openid-connect/auth
spring.security.oauth2.client.provider.oidc.token-uri=${AUTH_SERVER_URL}/realms/${AUTH_REALM}/protocol/openid-connect/token
spring.security.oauth2.client.provider.oidc.user-info-uri=${AUTH_SERVER_URL}/realms/${AUTH_REALM}/protocol/openid-connect/userinfo
spring.security.oauth2.client.provider.oidc.jwk-set-uri=${AUTH_SERVER_URL}/realms/${AUTH_REALM}/protocol/openid-connect/certs
spring.security.oauth2.client.provider.oidc.user-name-attribute=sub
```

- In `docker-compose.yml`, uncomment the `keycloak` service block, `keycloak_data` volume, and the `depends_on: keycloak` lines under `bcf-app`
- Start Postgres and Keycloak first:
  ```bash
  docker-compose up -d postgres keycloak
  ```
- Set up Keycloak (via admin console at `http://localhost:8180`):
  - Log in with `AUTH_ADMIN_USER` / `AUTH_ADMIN_PASSWORD` (set in `.env`)
  - Create a realm matching `AUTH_REALM`
  - Create a client in that realm matching `AUTH_CLIENT_ID`, enable client authentication, copy generated client secret into `AUTH_CLIENT_SECRET`
  - Add roles/claim mapper matching `AUTH_ROLES_CLAIM` if using role-based access
- Start the app:
  ```bash
  docker-compose up -d bcf-app
  ```

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
├── services/
│   ├── security/      # IdentityProviderService + provider implementations
│   └── ...            # Business logic
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
