# Harness CI/CD Setup

## Prerequisites

- A Harness account (free tier at https://app.harness.io)
- A GitHub Personal Access Token (PAT) with `repo` scope

## Step 1 — Create a Harness Organisation and Project

1. Log in to https://app.harness.io.
2. Navigate to **Account Settings → Organisations** and create `default` (or use the existing one).
3. Inside the organisation, create a project named **HelloWorld** with identifier `HelloWorld`.

## Step 2 — Create a GitHub Connector

1. Go to **Account Settings → Connectors → New Connector → GitHub**.
2. Name: `GitHubConnector`, Identifier: `GitHubConnector`.
3. Select **Repository** for connection type and enter your GitHub PAT as a secret.
4. Test the connection — it should succeed.

## Step 3 — Import the Pipeline

### Option A — Harness Git Experience (recommended)

1. In your project, go to **Pipelines → Create Pipeline → Import from Git**.
2. Select the GitHub connector, set repo to `BZala011/Harness`, branch `main`, path `.harness/pipeline.yaml`.
3. Harness syncs the pipeline automatically on every push.

### Option B — Manual YAML paste

1. **Pipelines → Create Pipeline → YAML**.
2. Paste the contents of `.harness/pipeline.yaml`.
3. Click **Save**.

## Step 4 — Run the Pipeline

1. Click **Run Pipeline**.
2. Select input set `default_input_set` or manually enter branch `main`.
3. Watch the stages execute in the **Execution** view.

## Step 5 — Configure Triggers (optional)

1. Go to **Triggers → Add New Trigger → GitHub**.
2. Event: **Push** — Branch filter: `main`.
3. The pipeline now triggers automatically on every push to `main`.

## Pipeline Stages Explained

| Stage | Step | Image | Command |
|-------|------|-------|---------|
| Build and Test | Compile | `maven:3.9-eclipse-temurin-17` | `mvn compile` |
| Build and Test | Unit Tests | `maven:3.9-eclipse-temurin-17` | `mvn test` |
| Build and Test | Checkstyle | `maven:3.9-eclipse-temurin-17` | `mvn checkstyle:check` |
| Build and Test | Package | `maven:3.9-eclipse-temurin-17` | `mvn package -DskipTests` |
| Build and Test | Cache Maven | — | SaveCacheS3 |

## Secrets Management

Store sensitive values in **Harness Secrets** (Account → Security → Secrets), not in `.env` or source code. Reference them in pipeline YAML as `<+secrets.getValue("MY_SECRET")>`.

## Troubleshooting

| Problem | Resolution |
|---------|-----------|
| Pipeline fails at "Clone" | Verify GitHub connector credentials and repo visibility |
| `mvn: command not found` | Ensure the Docker image is `maven:3.9-eclipse-temurin-17` |
| Checkstyle failures in CI | Run `mvn checkstyle:check` locally first and fix violations |
| Cache step fails | Create the S3 bucket and AWS connector, or remove the `SaveCacheS3` step |
