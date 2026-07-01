# Roadmap

## v1.0.0 — Initial Release (Done)

- [x] Java 17 Maven project
- [x] HelloWorld domain class
- [x] AppLogger utility
- [x] JUnit 5 unit tests
- [x] Checkstyle enforcement
- [x] Harness CI pipeline
- [x] GitHub Actions PR validation
- [x] Full documentation suite

## v1.1.0 — Observability

- [ ] Replace `AppLogger` with SLF4J + Logback
- [ ] Add structured JSON log output mode (for log aggregation)
- [ ] Add OWASP Dependency-Check Maven plugin
- [ ] Publish test coverage report (JaCoCo) as Harness artefact

## v1.2.0 — Packaging

- [ ] Add `Dockerfile` for container image
- [ ] Publish Docker image to registry via Harness CD stage
- [ ] Add Jib Maven plugin as an alternative to Docker daemon builds

## v2.0.0 — Service Layer

- [ ] Expose a simple HTTP endpoint (`/hello`) using Spring Boot
- [ ] Add Testcontainers for integration tests
- [ ] Add OpenAPI/Swagger spec (update `API.md`)
- [ ] Add health-check endpoint (`/actuator/health`)
- [ ] Deploy to cloud (AWS/GCP/Azure) via Harness CD

## v2.1.0 — Feature Flags

- [ ] Integrate Harness Feature Flags SDK
- [ ] Gate new greeting variants behind a feature flag

## Backlog (unscheduled)

- Mutation testing with PIT
- Performance benchmarks with JMH
- Multi-language greeting support
- ChatOps notifications (Slack) on pipeline failure

---

Roadmap items are subject to change based on contributor availability and project needs.
