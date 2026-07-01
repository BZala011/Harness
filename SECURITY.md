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

- Dependency vulnerabilities (addressed via OWASP Dependency-Check, to be added in a future release).
- Misconfigured CI secrets (addressed by Harness secret management).
