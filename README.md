# Opensource BCF Server 

An open-source server implementation of the BIM Collaboration Format (BCF) [REST API](https://github.com/buildingSMART/BCF-API), built with Java and Spring Boot.

## Features

- Full BCF REST API implementation (schema version 3.0, foundation version 1.1)
- Provider-agnostic OAuth2/JWT security (any standard OIDC provider, or disabled)
- BCF XML file import

## API Overview

The BCF REST API is documented at [BCF REST API](https://github.com/buildingSMART/BCF-API)
### Additional Endpoint
1. ``POST /import``  
   Import one or more BCF files. The request body must be `form-data` with the field:

   | Key    | Type | Value                  |
   |--------|------|------------------------|
   | `file` | File | The `.bcf` file to import. Repeat the field to import several files in one request |

   If the project in the file already exists (same `ProjectId` in `project.bcfp`), its topics are merged into it. Otherwise a new project is created.

   - **Single file:** returns `201 Created` on success, or `400`/`422` with the error message.
   - **Multiple files:** files are imported in order, each in its own transaction, so a failing file does not affect the others. Returns `201 Created` if all succeeded, otherwise `207 Multi-Status`. The body lists the result per file:

     ```json
     [
       { "filename": "a.bcf", "status": 201, "error": null },
       { "filename": "b.txt", "status": 400, "error": "Invalid file type: 'b.txt'. Only .bcf file is supported." }
     ]
     ```

   Upload limits default to 50MB per file and 500MB per request (override with `IMPORT_MAX_FILE_SIZE` / `IMPORT_MAX_REQUEST_SIZE`).

The official BCF-API spec has no endpoint to create a project from data — support for it was dropped after v1.0, since project creation is expected to happen in each server's own native system rather than via the generic API ([here](https://github.com/buildingSMART/BCF-API/issues/176#issuecomment-389455354)). `/import` is this server's own extension to fill that gap.


## Technology Stack

| Layer | Technology |
|---|---|
| Language | Java 21 |
| Framework | Spring Boot 3.5.7 |
| Security | Spring Security, OAuth2 Resource Server (optional, provider-agnostic) |
| Persistence | Spring Data JPA, PostgreSQL 17 |
| Build | Maven |
| Container | Docker |

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose
- PostgreSQL 17+ (only if running the database outside Docker Compose)

## Configuration

The application reads configuration from a `.env` file in the project root. Docker Compose uses it for variable substitution and passes it into the `bcf-app` container (`env_file`). Create a `.env` file:

```properties
BCF_IMAGE_VERSION=bcf-image-version
DB_NAME=bcf
DB_USER=bcf
DB_PASSWORD=bcf

# Auth is disabled by default: all requests are accepted as anonymous with WRITE access.
AUTH_ENABLED=false
```

## Development

Start PostgreSQL and the BCF server (no identity provider needed):

```bash
docker-compose up -d
```

The server will be available at `http://localhost:8181` (maps to container port 8080).

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

Any standard OIDC/OAuth2 provider works. [Keycloak](https://www.keycloak.org/) is a popular open-source option; `docker-compose.yml` contains a `keycloak` service behind the `keycloak` profile, so it only starts when requested. Steps below use Keycloak as example.

1. In `.env`, set `AUTH_ENABLED=true` and add:

```properties
AUTH_SERVER_URL=http://localhost:8180
AUTH_REALM=your-realm
AUTH_CLIENT_ID=your-client-id
AUTH_CLIENT_SECRET=your-secret
AUTH_ROLES_CLAIM=realm_access.roles
AUTH_ADMIN_USER=admin
AUTH_ADMIN_PASSWORD=admin

# Server-to-server URLs use the compose service name (keycloak:8180), because
# localhost inside the bcf-app container is the container itself.
AUTH_DISCOVERY_URI=http://keycloak:8180/realms/${AUTH_REALM}/.well-known/openid-configuration

spring.security.oauth2.resourceserver.jwt.issuer-uri=${AUTH_SERVER_URL}/realms/${AUTH_REALM}
spring.security.oauth2.resourceserver.jwt.jwk-set-uri=http://keycloak:8180/realms/${AUTH_REALM}/protocol/openid-connect/certs
spring.security.oauth2.client.registration.oidc.client-id=${AUTH_CLIENT_ID}
spring.security.oauth2.client.registration.oidc.client-secret=${AUTH_CLIENT_SECRET}
spring.security.oauth2.client.registration.oidc.scope=openid,profile,email
spring.security.oauth2.client.registration.oidc.authorization-grant-type=authorization_code
spring.security.oauth2.client.registration.oidc.redirect-uri={baseUrl}/login/oauth2/code/{registrationId}
spring.security.oauth2.client.provider.oidc.authorization-uri=${AUTH_SERVER_URL}/realms/${AUTH_REALM}/protocol/openid-connect/auth
spring.security.oauth2.client.provider.oidc.token-uri=http://keycloak:8180/realms/${AUTH_REALM}/protocol/openid-connect/token
spring.security.oauth2.client.provider.oidc.user-info-uri=http://keycloak:8180/realms/${AUTH_REALM}/protocol/openid-connect/userinfo
spring.security.oauth2.client.provider.oidc.jwk-set-uri=http://keycloak:8180/realms/${AUTH_REALM}/protocol/openid-connect/certs
spring.security.oauth2.client.provider.oidc.user-name-attribute=sub
```

2. Start Postgres and Keycloak:
   ```bash
   docker-compose --profile keycloak up -d postgres keycloak
   ```
3. Set up Keycloak (admin console at `http://localhost:8180`):
   - Log in with `AUTH_ADMIN_USER` / `AUTH_ADMIN_PASSWORD`
   - Create a realm matching `AUTH_REALM`. It must exist: `GET /foundation/1.1/auth` reads the realm's OIDC discovery document
   - Create a client matching `AUTH_CLIENT_ID`, enable client authentication, copy the generated client secret into `AUTH_CLIENT_SECRET`
   - Add roles/claim mapper matching `AUTH_ROLES_CLAIM` if using role-based access
4. Start the app:
   ```bash
   docker-compose up -d bcf-app
   ```

To go back to no authentication, set `AUTH_ENABLED=false` and stop Keycloak with `docker-compose --profile keycloak stop keycloak`.

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

