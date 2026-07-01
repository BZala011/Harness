# Contributing

Thank you for considering a contribution! This document explains how to submit changes effectively.

## Code of Conduct

All participants must follow the [Code of Conduct](CODE_OF_CONDUCT.md).

## Getting Started

1. **Fork** the repository and clone your fork locally.
2. Create a feature branch:
   ```bash
   git checkout -b feat/my-feature
   ```
3. Make your changes, ensuring all tests pass:
   ```bash
   mvn clean verify
   ```
4. Commit using the Conventional Commits format (see below).
5. Push your branch and open a **Pull Request** against `main`.

## Branch Naming

| Prefix | Use |
|--------|-----|
| `feat/` | New features |
| `fix/`  | Bug fixes |
| `docs/` | Documentation only |
| `chore/` | Maintenance, tooling |
| `test/`  | Test additions or fixes |

## Commit Message Format

```
<type>(<scope>): <short description>

[optional body]

[optional footer: Closes #123]
```

Types: `feat`, `fix`, `docs`, `style`, `refactor`, `test`, `chore`

Example:

```
feat(greeting): add support for custom greeting names

Allows callers to pass an optional name parameter.
Falls back to 'World' when null or blank.

Closes #12
```

## Pull Request Checklist

Before requesting review, ensure:

- [ ] `mvn clean verify` passes locally (tests + Checkstyle)
- [ ] New public methods have Javadoc
- [ ] `CHANGELOG.md` updated under `[Unreleased]`
- [ ] PR description explains the **why**, not just the **what**

## Reporting Bugs

Use the [bug report template](.github/ISSUE_TEMPLATE/bug_report.md).

## Suggesting Features

Use the [feature request template](.github/ISSUE_TEMPLATE/feature_request.md).

## Style Guide

- 4-space indentation, no tabs.
- Line length ≤ 120 characters.
- Follow the Checkstyle rules in `checkstyle.xml`.
- No commented-out code.

## Review Process

A maintainer will review your PR within 3 business days. Feedback may be requested before merging.
