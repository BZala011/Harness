# Harness Autonomous Workflow

## Purpose

Defines the step sequence this AI agent follows when implementing a change in this repo, end
to end. This file documents *intent*; it does not by itself grant permission to skip
confirmation — that's CLAUDE.md's "Automation & Approval Policy", which remains the source of
truth for what's pre-authorized vs. what needs to be asked.

## Workflow steps

1. Understand the requirement — read the task and the relevant existing code before changing
   anything.
2. For anything beyond a trivial change, form an implementation plan.
3. Create or check out a feature branch off an up-to-date `main`.
4. Modify production code under `com.harness.helloworld` or a sub-package.
5. Add or update unit tests in `src/test/` alongside the change (happy path + edge cases).
6. Update every doc the change invalidates (`README.md`, `API.md`, `TESTING.md`, and an entry
   under `CHANGELOG.md`'s `[Unreleased]` section).
7. Run `mvn clean verify` — compiles, runs unit tests, Checkstyle, and the JaCoCo coverage gate.
8. Resolve any compilation, style, or coverage failures and re-run until green.
9. Commit with a Conventional Commits message.
10. Push the feature branch.
11. Open or update the Pull Request.
12. Monitor CI (GitHub Actions PR validation + the Harness pipeline: Compile, Unit Tests,
    Checkstyle, Coverage Gate, Secret Detection, Dependency Vulnerability Scan, Package).
13. Root-cause and fix CI-only failures (e.g. Gitleaks, OWASP Dependency-Check) that can't be
    run locally; if a fix isn't safe to infer, stop and report the actual failure.
14. Merge once all required checks pass — no separate human review is required for the merge
    action itself in this repo (see CLAUDE.md).
15. Never trigger a production deploy. No prod pipeline exists today; if one is ever added, it
    always requires an explicit human approval step.

## What this workflow deliberately excludes

This repo is a single-module Java CLI with no API surface, no frontend, no database, and no
deployment target. The following are common in larger SDLC checklists but don't apply here —
wiring them up would be automation theater with no real gate behind it (see CONTEXT.md's
"Constraints"):

- Frontend, DTO, Swagger/OpenAPI updates — there is no API.
- Mutation testing, load/stress/E2E/contract tests — there's no service boundary to exercise
  this way; see TESTING.md's "Future Additions" for when that could change.
- Sonar or any external static-analysis service — Checkstyle plus the JaCoCo coverage gate,
  both wired into `.harness/pipeline.yaml`, are the real gates. See `docs/coding-standards.md`.
- Multi-environment deploys or credential rotation — no environments exist beyond CI.

## Retry / failure policy

- Transient failures (CI runner flakiness, AI provider rate limits) may be retried.
- Genuine failures (a failing test, a style violation, a coverage drop) must be root-caused and
  fixed, not retried blindly or worked around by skipping the gate.
- Cap automatic fix attempts at 3 per gate; beyond that, stop and report the actual error rather
  than continuing to guess.

## Canonical execution

The `/ship` slash command (`.claude/commands/ship.md`) is the concrete implementation of this
workflow — it has the exact commands and control flow. If this file and `ship.md` ever
disagree, `ship.md` wins until HARNESS.md is updated to match.
