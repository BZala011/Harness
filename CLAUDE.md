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

## Automation & Approval Policy

Standing authorization (decided 2026-07-01) — Claude does **not** need to ask for confirmation before:

- Committing code changes on a feature branch.
- Pushing that branch to origin.
- Opening a Pull Request against `main`.
- Merging that PR into `main`, once CI checks (build, tests, Checkstyle) pass.

This applies to routine feature/fix work following the guidelines above. It does **not** cover:

- Force-pushes, history rewrites, or branch deletion — always confirm first.
- Any production deployment — this repo has no prod pipeline today, but if one is added, deploys always
  require an explicit human approval step; never wire automation to auto-deploy to prod.
- Changes to the files listed under "Files Claude Should NOT Modify Without Asking" above.

If the `gh` CLI is not installed/authenticated in the environment, PR creation and merge cannot be done via
automation — fall back to pushing the branch and handing the user a compare URL
(`https://github.com/<owner>/<repo>/compare/<branch>`) to open manually.

## Commit Message Format

```
<type>(<scope>): <subject>

Types: feat | fix | docs | style | refactor | test | chore
Example: feat(greeting): add multi-language support
```

## Automation & Approval Policy

The `/ship` command (`.claude/commands/ship.md`) runs the full autonomous flow: validate
(`mvn clean verify`) → AI code review (`/code-review`) → commit → push → create/update PR →
enable GitHub auto-merge. Once invoked, the following are **pre-authorized** — do not pause
to ask for confirmation on these for routine feature/fix work:

- Committing with a Conventional Commit message.
- Pushing the current feature branch.
- Creating or updating the Pull Request.
- Enabling auto-merge so the PR merges as soon as required CI checks pass.

**Always still confirm before:** force-push, history rewrite, branch deletion, or any change
to `.harness/pipeline.yaml` / `pom.xml` outside the `/ship` command's own setup.

**Production deployment always requires a human approval step.** This repo has no prod
pipeline/environment today, so the constraint is currently moot — but if one is added later,
never wire it to auto-deploy.

## Relationship to Other Agent Docs

- [`AGENTS.md`](AGENTS.md) — describes the human/AI roles in this project
- [`CONTEXT.md`](CONTEXT.md) — architecture decisions and assumptions
- [`TESTING.md`](TESTING.md) — testing strategy details
- [`HARNESS.md`](HARNESS.md) — the autonomous workflow step sequence (intent); `/ship` is the
  concrete implementation
- [`GIT_WORKFLOW.md`](GIT_WORKFLOW.md) — git branching/commit/PR/merge mechanics
- [`docs/coding-standards.md`](docs/coding-standards.md) — `checkstyle.xml` rules explained in
  plain language
