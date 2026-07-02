# Coding Standards

This repo's real style gate is `checkstyle.xml` (repo root), run via `mvn checkstyle:check` as
part of `mvn verify`. This document explains those rules in plain language; if it and
`checkstyle.xml` ever disagree, `checkstyle.xml` is authoritative.

## Formatting

- 4-space indentation, no tabs (`Indentation`: basicOffset 4, no brace adjustment).
- Line length ≤ 120 characters, except `package`/`import` lines and bare URLs (`LineLength`).
- One statement per line; one top-level class per file, filename matches the outer type
  (`OneStatementPerLine`, `OneTopLevelClass`, `OuterTypeFilename`).
- Braces required on all control structures (`NeedBraces`, `LeftCurly`, `RightCurly`).
- File ends with a newline (`NewlineAtEndOfFile`).

## Naming

- Types: `PascalCase` (`TypeName`).
- Members and local variables: `camelCase` (`MemberName`, `LocalVariableName`, `ParameterName`).
- Constants: `private static final` fields in `UPPER_SNAKE_CASE` (expected per CLAUDE.md; not
  separately enforced by Checkstyle).
- Packages: lowercase, dot-separated (`PackageName`). New classes stay under
  `com.harness.helloworld` or a sub-package — see CLAUDE.md's "Stay in-package".

## Safety

- No empty catch blocks unless the exception variable is literally named `expected`
  (`EmptyCatchBlock`) — silent failures aren't allowed; see CLAUDE.md's "No silent failures"
  and use `AppLogger.error()`.
- `switch` statements need a `default` case and no accidental fall-through
  (`MissingSwitchDefault`, `FallThrough`).
- No finalizers (`NoFinalizer`).
- Modifier order follows the JLS-recommended order (`ModifierOrder`); array types use
  `String[] args` style, not `String args[]` (`ArrayTypeStyle`).

## What's not covered here

There's no Sonar or other external static-analysis service in this pipeline — Checkstyle plus
the JaCoCo coverage gate (60% line minimum; see `TESTING.md`) are the real automated gates,
both wired into `.harness/pipeline.yaml`. Don't invent findings from tools that aren't actually
configured.
