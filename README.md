# Harness Hello World

> A production-ready Java application demonstrating Harness CI/CD best practices.

[![Harness CI](https://img.shields.io/badge/CI-Harness-00ADE4?logo=harness)](https://app.harness.io)
[![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk)](https://openjdk.org/projects/jdk/17/)
[![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven)](https://maven.apache.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

---

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Build](#build)
- [Run](#run)
- [Test](#test)
- [CI/CD with Harness](#cicd-with-harness)
- [Contributing](#contributing)
- [License](#license)

---

## Overview

This project provides a minimal but **production-quality** skeleton for a Java application managed through **Harness Engineering**. It covers:

- Clean package architecture (`com.harness.helloworld`)
- Unit tests with JUnit 5
- Code-style enforcement via Checkstyle
- A fully configured Harness CI pipeline (`.harness/pipeline.yaml`)
- A companion GitHub Actions workflow for pull-request validation
- Comprehensive documentation for human and AI-agent contributors

---

## Prerequisites

| Tool | Minimum version |
|------|----------------|
| JDK  | 17             |
| Maven | 3.8           |
| Git  | 2.40           |
| Harness account | — |

---

## Project Structure

```
.
├── .github/                    # GitHub templates and Actions
│   ├── ISSUE_TEMPLATE/
│   │   ├── bug_report.md
│   │   └── feature_request.md
│   ├── workflows/
│   │   └── ci.yml              # GitHub Actions (PR validation)
│   └── PULL_REQUEST_TEMPLATE.md
├── .harness/
│   ├── pipeline.yaml           # Harness CI/CD pipeline definition
│   └── inputset.yaml           # Default pipeline input values
├── docs/                       # Extended documentation
│   ├── setup.md
│   ├── deployment.md
│   └── harness-setup.md
├── src/
│   ├── main/java/com/harness/helloworld/
│   │   ├── App.java            # Application entry point
│   │   ├── HelloWorld.java     # Core greeting domain class
│   │   └── util/
│   │       └── AppLogger.java  # Structured logging utility
│   └── test/java/com/harness/helloworld/
│       ├── HelloWorldTest.java # Unit tests
│       └── AppTest.java        # Integration smoke test
├── .editorconfig
├── .env.example
├── .gitattributes
├── .gitignore
├── AGENTS.md
├── ARCHITECTURE.md
├── API.md
├── CHANGELOG.md
├── CLAUDE.md
├── CODE_OF_CONDUCT.md
├── CONTRIBUTING.md
├── CONTEXT.md
├── LICENSE
├── ROADMAP.md
├── SECURITY.md
├── TESTING.md
├── checkstyle.xml
└── pom.xml
```

---

## Getting Started

```bash
git clone https://github.com/BZala011/Harness.git
cd Harness
```

Copy the environment example and adjust as needed:

```bash
cp .env.example .env
```

---

## Build

```bash
mvn clean package
```

The executable JAR is produced at `target/hello-world-1.0.0.jar`.

---

## Run

```bash
java -jar target/hello-world-1.0.0.jar
```

Expected output:

```
[2026-07-01 10:00:00] [INFO ] [com.harness.helloworld.App] Application starting
Hello 11111
[2026-07-01 10:00:00] [INFO ] [com.harness.helloworld.App] Application completed successfully
```

---

## Test

```bash
mvn test                  # unit tests
mvn verify                # tests + Checkstyle
```

---

## CI/CD with Harness

See [`docs/harness-setup.md`](docs/harness-setup.md) for full setup instructions.  
The pipeline definition lives at [`.harness/pipeline.yaml`](.harness/pipeline.yaml).

Quick start:

1. Import `.harness/pipeline.yaml` into your Harness project.
2. Create a GitHub connector named `account.GitHubConnector`.
3. Trigger a run by pushing to `main` or opening a pull request.

---

## Contributing

See [CONTRIBUTING.md](CONTRIBUTING.md).

---

## License

Distributed under the [MIT License](LICENSE).
