# Claude AI Coding Guidelines

This file guides Claude (and other AI coding assistants) when working in this repository.

## Project Overview

- **Language:** Java 17
- **Build:** Maven 3.8+
- **Test:** JUnit 5
- **CI/CD:** Harness Engineering (pipeline at `.harness/pipeline.yaml`)
- **Style:** Checkstyle (`checkstyle.xml`) — 120-char line limit, 4-space indent

## Core Principles

1. **Don't over-engineer.** This is a demo skeleton. Keep changes minimal and purposeful.
2. **Tests first.** Add or update tests in `src/test/` before or alongside production code.
3. **No silent failures.** Surface errors with `AppLogger.error()` and rethrow or exit cleanly.
4. **Stay in-package.** New classes belong in `com.harness.helloworld` or a sub-package.
5. **Document the why, not the what.** Well-named identifiers speak for themselves.

## Package Structure

```
com.harness.helloworld          # domain + entry point
com.harness.helloworld.util     # cross-cutting utilities (logging, formatting)
```

## Commands to Know

```bash
mvn clean verify          # full build + style check
mvn test                  # tests only
java -jar target/hello-world-1.0.0.jar   # run the app
```

## Files Claude Should NOT Modify Without Asking

- `.harness/pipeline.yaml` — pipeline changes affect CI for all contributors
- `pom.xml` — dependency changes need review
- `LICENSE` — never change the license

## Adding a New Feature

1. Create the class under the correct sub-package.
2. Add unit tests covering happy path + edge cases.
3. Update `CHANGELOG.md` under `[Unreleased]`.
4. Update `README.md` if the public interface changes.

## Style Reminders

- 4-space indent, no tabs.
- Line length ≤ 120 characters.
- `private static final` constants in `UPPER_SNAKE_CASE`.
- No raw `System.out.println` in non-`App` classes — use `AppLogger`.

## Commit Message Format

```
<type>(<scope>): <subject>

Types: feat | fix | docs | style | refactor | test | chore
Example: feat(greeting): add multi-language support
```

## Relationship to Other Agent Docs

- [`AGENTS.md`](AGENTS.md) — describes the human/AI roles in this project
- [`CONTEXT.md`](CONTEXT.md) — architecture decisions and assumptions
- [`TESTING.md`](TESTING.md) — testing strategy details
