# Deployment Guide

## Artefact

The build produces a self-contained executable JAR:

```
target/hello-world-1.0.0.jar
```

## Running the JAR

```bash
java -jar hello-world-1.0.0.jar
```

Requires Java 17+ on the target machine.

## Environment Variables

See `.env.example` for all recognised variables.

```bash
export APP_ENV=production
export APP_LOG_LEVEL=INFO
java -jar hello-world-1.0.0.jar
```

## Containerisation (Future)

A `Dockerfile` is planned for v1.2.0. The expected form:

```dockerfile
FROM eclipse-temurin:17-jre-alpine
COPY target/hello-world-1.0.0.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
```

Build and run:

```bash
docker build -t hello-world:1.0.0 .
docker run --rm hello-world:1.0.0
```

## CI/CD Deployment via Harness

At v1.0.0, the Harness pipeline stops at the **Package** stage — it builds and tests the artefact but does not deploy it. A CD stage delivering the JAR to a server or container registry is planned for v1.2.0.

See [`harness-setup.md`](harness-setup.md) for pipeline configuration.
