# Testing Strategy

## Philosophy

- Tests live alongside the code they verify in `src/test/`.
- Every public method has at least one test covering the happy path and one covering the edge case.
- Tests are deterministic, stateless, and run in any order.

## Test Layers

| Layer | Location | Tool | When it runs |
|-------|----------|------|-------------|
| Unit | `src/test/java/...` | JUnit 5 | `mvn test` |
| Smoke (integration) | `AppTest.java` | JUnit 5 + stdout capture | `mvn verify` |

## Running Tests

```bash
# All tests
mvn test

# Full verify (tests + Checkstyle)
mvn verify

# Single test class
mvn test -Dtest=HelloWorldTest

# Single test method
mvn test -Dtest=HelloWorldTest#defaultGreeting
```

## Current Test Coverage

| Class | Tests |
|-------|-------|
| `HelloWorld` | default greeting, named greeting, null fallback, blank fallback, trim |
| `App` | main() prints "Hello everyone" to stdout |

## Adding Tests

1. Create `<ClassName>Test.java` in the mirror package under `src/test/`.
2. Annotate the class with `@DisplayName` and each method with `@Test` + `@DisplayName`.
3. Use `assertEquals`, `assertThrows`, `assertTrue` from JUnit 5 (`org.junit.jupiter.api.Assertions`).
4. Run `mvn verify` before committing.

## CI Integration

Tests run automatically in the Harness pipeline on every push. A failing test blocks PR merge. See `.harness/pipeline.yaml` for the exact step configuration.

## Future Additions

- Integration tests with Testcontainers (when external dependencies are added).
- Mutation testing with PIT (`org.pitest:pitest-maven`).
- OWASP Dependency-Check as a verify step.
