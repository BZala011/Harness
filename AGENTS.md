# Agent Roles and Workflow

This document describes the roles of human contributors and AI agents in this project's development lifecycle.

## Human Roles

| Role | Responsibilities |
|------|-----------------|
| **Project Lead** | Architecture decisions, pipeline configuration, release approvals |
| **Developer** | Feature implementation, code review, test authoring |
| **DevOps Engineer** | Harness connector setup, secret management, runner configuration |

## AI Agent Roles

### Claude (Coding Assistant)

**Scope:** In-editor coding tasks guided by [`CLAUDE.md`](CLAUDE.md).

- Implement feature code and tests on request.
- Refactor within existing package boundaries.
- Generate documentation updates when code changes.
- **Must not** autonomously push to `main` or modify pipeline YAML without human review.

### Harness CI Agent (Automated Pipeline)

**Scope:** Executes `.harness/pipeline.yaml` on every push and pull-request event.

**Steps executed:**

1. Clone repository
2. Compile (`mvn compile`)
3. Run unit tests (`mvn test`)
4. Code-style check (`mvn checkstyle:check`)
5. Package artefact (`mvn package -DskipTests`)
6. (Optional) Upload artefact to registry

**Failure behaviour:** Pipeline fails fast; downstream steps are skipped. Pull requests are blocked until pipeline passes.

## Workflow

```
Developer  ──► feature branch  ──► Pull Request
                                        │
                             GitHub Actions (PR validation)
                                        │
                             Harness CI  (full pipeline)
                                        │
                    ◄── review + approve ──────────────────
                                        │
                              merge to main
                                        │
                             Harness CI  (main build)
```

## AI Agent Guardrails

- AI agents operate on **feature branches only** unless explicitly authorised.
- Secrets referenced in `.env.example` must never be committed; agents should read from environment variables or Harness secrets.
- Pipeline YAML modifications require a human review step before merge.
