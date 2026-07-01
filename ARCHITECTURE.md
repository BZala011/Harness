# Architecture

## High-Level Overview

```
┌─────────────────────────────────────────────────┐
│                  JVM Process                    │
│                                                 │
│  ┌────────────┐       ┌────────────────────┐   │
│  │  App.java  │──────▶│  HelloWorld.java   │   │
│  │ (entry pt) │       │  (domain logic)    │   │
│  └─────┬──────┘       └────────────────────┘   │
│        │                                        │
│        ▼                                        │
│  ┌──────────────┐                               │
│  │ AppLogger    │──▶  stdout / stderr           │
│  │ (util)       │                               │
│  └──────────────┘                               │
└─────────────────────────────────────────────────┘
```

## Package Structure

```
com.harness.helloworld
├── App.java            Entry point; owns the process lifecycle
├── HelloWorld.java     Domain class; pure function, no I/O
└── util/
    └── AppLogger.java  Cross-cutting concern; lightweight structured logging
```

## Design Principles

### Separation of Concerns

`App` is responsible for orchestration (start, delegate, handle errors, exit). `HelloWorld` is a pure domain class — it has no logging, no I/O, and no knowledge of how it is invoked.

### Single Responsibility

Each class has one reason to change:

| Class | Reason to change |
|-------|-----------------|
| `App` | Entry point logic, error handling strategy |
| `HelloWorld` | Greeting content or personalisation rules |
| `AppLogger` | Log format, output destination, severity rules |

### Dependency Direction

`App` → `HelloWorld` (depends on domain)  
`App` → `AppLogger` (depends on utility)  
`HelloWorld` has **no** dependencies (pure)

### Extensibility Points

| Concern | Current approach | Future upgrade |
|---------|-----------------|---------------|
| Logging | `AppLogger` (custom) | SLF4J + Logback |
| Config | Hardcoded constants | `dotenv-java` or Spring Boot config |
| Testing | JUnit 5 unit tests | Testcontainers for integration |
| Packaging | Executable JAR | Container image (Jib or Dockerfile) |

## CI/CD Architecture

```
Developer/Agent Push
      │
      ▼
GitHub (source of truth)
      │
      ├──▶ GitHub Actions (PR validation — fast feedback)
      │
      └──▶ Harness CI (full pipeline — authoritative)
                │
                ├── Compile
                ├── Test
                ├── Checkstyle
                ├── Code Coverage Gate (JaCoCo, 60% line minimum)
                ├── Secret Detection (Gitleaks)
                ├── Dependency Vulnerability Scan (OWASP Dependency-Check)
                └── Package → Artefact
                        │
                        ▼
        all required checks pass → GitHub auto-merge fires (see CLAUDE.md)
```
