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
- May run the `/ship` workflow autonomously: commit, push a feature branch, open/update a
  Pull Request, and enable auto-merge once CI + AI review pass — see CLAUDE.md's
  "Automation & Approval Policy" for the exact pre-authorized scope.
- **Must not** push directly to `main`, force-push, rewrite history, delete branches, or
  modify `.harness/pipeline.yaml` / `pom.xml` outside of an explicitly-approved task, without
  asking first.
- **Must not** auto-deploy to production — no such stage exists today, and if one is added it
  always requires human approval.

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
Developer/Agent ──► feature branch ──► /ship (commit, push, open PR, enable auto-merge)
                                              │
                                   GitHub Actions (PR validation)
                                              │
                                   Harness CI  (full pipeline)
                                              │
                             all required checks pass?
                                     │              │
                                   yes             no ──► PR blocked, fix and re-run /ship
                                     │
                                merge to main (automatic, no human click required)
                                     │
                             Harness CI  (main build)
```

Human-authored PRs are unaffected by this — a maintainer can still review and merge manually
at any point; auto-merge only fires if the PR is still open and unreviewed when checks pass.

## AI Agent Guardrails

- AI agents operate on **feature branches only** — never commit or push directly to `main`.
- Secrets referenced in `.env.example` must never be committed; agents should read from environment variables or Harness secrets.
- Commit, push, PR create/update, and merge-to-`main` are pre-authorized for routine work via
  `/ship` (see `CLAUDE.md`). Pipeline YAML / `pom.xml` modifications outside that command's own
  setup still require an explicit human ask, as does anything auto-deploying to production.
