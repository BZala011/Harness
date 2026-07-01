---
description: Validate, commit, push, open/update a PR, and auto-merge once checks pass
---

Run the full autonomous ship workflow for the current branch's changes. Follow these steps
in order and stop immediately if any step fails — report the failure instead of proceeding.

## 1. Validate

```bash
mvn --batch-mode clean verify
```

This runs compile, unit tests, Checkstyle, and the JaCoCo coverage gate (60% line minimum).
Do not skip this even if you believe the change is trivial. If it fails, fix the root cause
and re-run — do not weaken the gate to make it pass.

`OWASP Dependency-Check` is intentionally excluded from the local run (needs `NVD_API_KEY`
and a multi-minute first-run database sync) — it runs in the Harness pipeline instead.

## 2. AI code review

Invoke `/code-review` (medium effort) against the working diff. Treat any CONFIRMED
correctness finding as a blocker: fix it and re-run step 1. PLAUSIBLE findings should be
judged, not auto-applied blindly. Do not proceed to commit while a confirmed bug is open.

## 3. Commit

Stage only the files relevant to this change (never `git add -A`). Write a Conventional
Commit message (`<type>(<scope>): <subject>`, matching the format in `CLAUDE.md` /
`CONTRIBUTING.md`). Do not include the Claude co-author trailer unless the user's global
git commit conventions elsewhere in this session already call for it.

## 4. Push

Push the current feature branch to `origin` with `-u` if it has no upstream yet. Never
push directly to `main`.

## 5. Create or update the Pull Request

```bash
gh pr view --json number,state 2>/dev/null
```

If no PR exists for this branch, create one:

```bash
gh pr create --fill --base main
```

If a PR already exists, just let the push above update it (GitHub does this automatically)
— no extra command needed unless the title/body needs revising, in which case use
`gh pr edit`.

## 6. Enable checks + auto-merge

```bash
gh pr merge --auto --squash
```

This does **not** merge immediately — it tells GitHub to merge automatically the moment all
required status checks (GitHub Actions `CI`, and the Harness pipeline check if wired to
branch protection) report success. If `gh pr merge --auto` errors because auto-merge isn't
enabled on the repo, run once (idempotent, safe to repeat):

```bash
gh repo edit --enable-auto-merge
```

## 7. Report

Tell the user: what was committed, the PR URL, and that merge is now pending on CI —
do not claim the merge already happened unless `gh pr view --json state` confirms `MERGED`.

## Hard stops — never automate these

- **Force-push, history rewrite, or branch deletion** — always ask first.
- **Production deployment** — there is no prod pipeline in this repo today; if one is ever
  added, it always requires an explicit human approval step, never auto-merge/auto-deploy.
- **Editing `.harness/pipeline.yaml` or `pom.xml`** as a side effect of a routine `/ship`
  run — those stay behind an explicit ask per `CLAUDE.md`, even though this command itself
  was authorized to add supporting automation around them.
