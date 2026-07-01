# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

## [1.0.0] – 2026-07-01

### Added

- Initial production-ready project scaffold
- `HelloWorld` domain class with default and named greeting methods
- `AppLogger` structured logging utility
- `App` entry point with error handling and clean exit codes
- JUnit 5 unit tests for `HelloWorld` and `App`
- Checkstyle enforcement (`checkstyle.xml`)
- Maven build with enforcer (Java 17+, Maven 3.8+)
- Executable JAR packaging with manifest `Main-Class`
- Harness CI pipeline (`.harness/pipeline.yaml`)
- GitHub Actions PR validation (`.github/workflows/ci.yml`)
- Full documentation suite: README, CONTEXT, CLAUDE, AGENTS, CONTRIBUTING,
  CODE_OF_CONDUCT, SECURITY, TESTING, ARCHITECTURE, API, ROADMAP
- `.editorconfig`, `.gitattributes`, `.gitignore`, `.env.example`

[Unreleased]: https://github.com/BZala011/Harness/compare/v1.0.0...HEAD
[1.0.0]: https://github.com/BZala011/Harness/releases/tag/v1.0.0
