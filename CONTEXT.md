# Project Context

## Purpose

This repository demonstrates how to scaffold a **production-ready Java application** integrated with **Harness CI/CD** from day one. It is intentionally minimal in application logic so that the CI/CD, tooling, and documentation layers are the focus.

## Background

Harness Engineering is a developer-experience platform that provides CI (Continuous Integration), CD (Continuous Delivery), Feature Flags, Cloud Cost Management, and more under a single control plane. This project uses Harness CI to automate build, test, and package steps on every push.

## Architecture Decisions

| Decision | Rationale |
|----------|-----------|
| Java 17 (LTS) | Long-term support, modern language features, stable for production |
| Maven over Gradle | Wider Harness plugin support; deterministic builds via enforcer |
| JUnit 5 | Current standard; good IDE integration and parameterised-test support |
| No external logging framework | Avoids dependency bloat for a demo; `AppLogger` is a drop-in point for SLF4J/Logback |
| Checkstyle (Google-adjacent) | Enforces consistent style without a full Spotless setup |
| Executable JAR | Simplest deployment artefact; no container image needed for this demo |

## Assumptions

- The reader has access to a Harness account (free tier is sufficient).
- The GitHub repository is public or the Harness connector has appropriate credentials.
- Java 17+ is available in the CI runner image.
- No external services (databases, queues) are required by this application.

## Constraints

- Application logic is intentionally trivial — this is a template/demo, not a business application.
- The pipeline does not include a deployment stage (CD) to keep scope focused on CI.

## Future Scope

See [`ROADMAP.md`](ROADMAP.md).
