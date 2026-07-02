# Security Policy

## Supported Versions

| Version | Supported |
|---------|-----------|
| 1.x     | Yes       |

## Reporting a Vulnerability

**Do not open a public GitHub issue for security vulnerabilities.**

Please report security issues by emailing the maintainers directly (see the `CODEOWNERS` file or repository settings for contact details). Include:

1. A clear description of the vulnerability.
2. Steps to reproduce (proof-of-concept if possible).
3. The potential impact.
4. Any suggested mitigations.

You will receive an acknowledgement within 48 hours and a resolution timeline within 7 business days.

## Security Practices

- Dependencies are tracked in `pom.xml`; run `mvn dependency:resolve` to audit.
- Secrets are **never** committed — use `.env` (gitignored) or Harness secrets.
- The Maven enforcer plugin ensures no downgrade below the minimum tested Java version.
- Pull requests are validated by CI before merge to `main`.

## Scope

This is a demo application with no network exposure or persistent data. The primary security concerns are:

- Dependency vulnerabilities (addressed via OWASP Dependency-Check, `failBuildOnCVSS=7`, in the Harness pipeline).
- Secret leaks in the working tree (addressed via Gitleaks in the Harness pipeline).
- Misconfigured CI secrets (addressed by Harness secret management).

## Auto-Merge Gate

PRs merged via the `/ship` automation (see `CLAUDE.md`) cannot bypass these checks — GitHub
auto-merge only fires once the required CI status checks, including Checkstyle, the JaCoCo
coverage gate, OWASP Dependency-Check, and Gitleaks, report success.
