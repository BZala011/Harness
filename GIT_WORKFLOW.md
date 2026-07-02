# Git Workflow

This file describes git mechanics. CLAUDE.md's "Automation & Approval Policy" is the source of
truth for what's pre-authorized vs. what always needs a human ask — this file assumes that
policy and doesn't repeat its exceptions in detail.

## Branching

- Never commit or push directly to `main`.
- Branch off an up-to-date `main`: `git checkout main && git pull && git checkout -b <type>/<short-name>`.
- Use a Conventional Commits type prefix for the branch name: `feat/`, `fix/`, `chore/`,
  `refactor/`, `docs/`, `test/`, `style/`.
- Split unrelated concerns into separate branches/PRs rather than bundling them.

## Committing

- Stage only the files intentionally changed — never `git add -A` or `git add .`.
- Message format: `<type>(<scope>): <subject>`, with a body explaining *why* when it's not
  obvious from the diff.
- Create a new commit rather than amending, unless explicitly asked to amend a local, unpushed
  mistake.

## Pushing

- `git push -u origin <branch>` on the first push; plain `git push` after.
- If the push is rejected because the branch diverged, `git fetch` and reconcile before
  retrying — prefer `merge` over `rebase` once a PR already exists for the branch, since
  rewriting pushed/reviewed history is riskier.
- Merge-conflict auto-resolve is limited to literal stale-value mismatches (see
  `.claude/commands/ship.md` step 4 for the exact rule); anything touching logic, imports,
  structure, or config always stops and asks rather than guessing.

## Pull Requests

- One PR per feature branch, targeting `main`.
- Title matches the commit subject; body has a Summary and a Test Plan checklist.
- If `gh` is unavailable or unauthenticated, don't fabricate a PR — hand the user the compare
  URL (`https://github.com/<owner>/<repo>/compare/<branch>`) and the PR body text instead.

## Merging

- Automated merge to `main` is authorized once required CI checks pass — no separate human
  review is required for the merge action itself in this repo.
- Never force-merge past a failing or pending required check.
- After merge, delete the remote feature branch; leave the local branch for the user to clean
  up themselves.

## Always confirm first

- Force-push, history rewrite, or branch deletion beyond the post-merge remote-branch cleanup
  above.
- Any change to `.harness/pipeline.yaml` or `pom.xml` outside an explicitly-approved task.
- Anything that would deploy to production — no such pipeline exists today, and if one is added
  it always needs a human approval step.
