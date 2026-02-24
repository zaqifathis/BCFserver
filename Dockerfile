# Stage 1: Build and Extract Layers
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /application

# 1. Pre-download dependencies to cache them in a Docker layer
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 2. Build the application
COPY src/ ./src
RUN mvn package -DskipTests && cp target/*.jar application.jar

# 3. Extract layers for optimized container structure
RUN java -Djarmode=layertools -jar application.jar extract

# Stage 2: Runtime Environment
FROM eclipse-temurin:21-jre
WORKDIR /application

# Install essential tools
RUN apt-get update && \
    apt-get -y --no-install-recommends install wait-for-it jq curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy License and extracted layers from builder stage
COPY LICENCE .
COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./

# Create the data directory for SQLite persistence
RUN mkdir /application/data

EXPOSE 8080

# Healthcheck to ensure the service is responsive
HEALTHCHECK --start-period=30s --interval=30s --timeout=3s --retries=3 \
    CMD curl -f http://localhost:8080/foundation/versions || exit 1

# Launch the app using the JarLauncher (enables layered loading)
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]