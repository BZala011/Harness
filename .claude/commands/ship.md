---
description: Implement a task end-to-end -- branch, code, test, commit, push, PR (and merge once checks pass)
argument-hint: <task description>
---

Task: $ARGUMENTS

Follow this repo's conventions in `CLAUDE.md` throughout (package structure, style, changelog, commit format,
and the list of files that need explicit confirmation before editing: `.harness/pipeline.yaml`, `pom.xml`,
`LICENSE`).

## 1. Understand and branch

- Read the task and inspect the relevant existing code before changing anything.
- Create a feature branch off an up-to-date `main` (`git checkout main && git pull && git checkout -b <type>/<short-name>`),
  using a Conventional Commits type prefix (`feat`, `fix`, `chore`, `refactor`, `docs`, `test`, `style`).
- If the task is clearly two unrelated concerns, split it into separate branches/PRs rather than bundling them.

## 2. Implement

- Make the minimal, purposeful change the task calls for -- no speculative abstractions, no unrelated cleanup.
- Add or update unit tests in `src/test/` alongside the change (happy path + edge cases).
- Update every doc whose claims the change invalidates: `README.md`, `API.md`, `TESTING.md`. Grep for the old
  value/behavior across `*.md` before assuming you found every reference.
- Add an entry under `## [Unreleased]` in `CHANGELOG.md` (`### Added` / `### Changed` / `### Fixed` as appropriate).

## 3. Validate locally -- fix and retry on failure

Run, in order, fixing root causes and re-running only what's needed until everything is green (cap at 3 fix
attempts per gate; if still failing, stop and report the actual error rather than working around it):

```bash
mvn -q clean verify        # compiles, runs unit tests, Checkstyle, JaCoCo coverage gate (60% line minimum)
```

Do not skip tests (`-DskipTests`) or lower the coverage/style gate to make it pass -- fix the actual issue.
Do not run the OWASP Dependency-Check or Gitleaks scans locally (they're slow / need cached NVD data); those
run in the Harness pipeline post-push.

## 4. Commit and push

- Stage only the files you intentionally changed (never `git add -A`).
- Commit with a Conventional Commits message: `<type>(<scope>): <subject>`, body explaining *why*.
- Push with `git push -u origin <branch>`.

## 5. Open the PR

- If `gh` is installed and authenticated (`gh auth status`), run `gh pr create` with:
  - A title matching the commit subject.
  - A body with: Summary (bullets of what/why), files changed, local test results (`mvn verify` outcome),
    and a Test Plan checklist. Note that Dependency-Check/Gitleaks/coverage results come from the CI run,
    not local execution -- link to the pipeline run once it's live rather than fabricating results.
- If `gh` is unavailable or unauthenticated, do not fabricate a PR: report the pushed branch name, the
  compare URL (`https://github.com/<org>/<repo>/compare/main...<branch>`), and the PR body text so the user
  can paste it manually.

## 6. Merge -- only when it's real

Per the standing project policy: automated merge to `main` is authorized once required CI checks pass (no
separate human approval needed for merge itself). Production deployment always requires a human approval step
-- never wire this command to trigger a production deploy.

- Only attempt `gh pr merge --squash --auto` if `gh` succeeded in step 5 and the PR exists.
- Never force-merge past a failing or pending required check.
- After merge, delete the remote feature branch (`git push origin --delete <branch>`) but leave the local
  branch for the user to clean up.
- If `gh` isn't available, stop after step 5 and tell the user the branch/PR is ready for manual merge --
  do not claim a merge happened.

## Reporting

End with a short summary: branch name, what changed, local validation result, and PR link (or the manual
fallback info from step 5) -- not a restatement of every command you ran.
