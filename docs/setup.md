# Local Development Setup

## Prerequisites

| Tool | Version | Install |
|------|---------|---------|
| JDK | 17+ | https://adoptium.net |
| Maven | 3.8+ | https://maven.apache.org/download.cgi |
| Git | 2.40+ | https://git-scm.com |

Verify your environment:

```bash
java -version     # must show 17+
mvn --version     # must show 3.8+
git --version
```

## Clone and Configure

```bash
git clone https://github.com/BZala011/Harness.git
cd Harness
cp .env.example .env   # optional — app has no external config at v1.0
```

## Build

```bash
mvn clean package
```

Produces `target/hello-world-1.0.0.jar`.

## Run

```bash
java -jar target/hello-world-1.0.0.jar
```

## Run Tests

```bash
mvn test                 # unit tests only
mvn verify               # tests + Checkstyle
```

## IDE Setup

### IntelliJ IDEA

1. **File → Open** → select the `Harness` directory.
2. IntelliJ detects `pom.xml` and imports the Maven project automatically.
3. Set **Project SDK** to JDK 17 (**File → Project Structure → SDK**).
4. Install the **Checkstyle-IDEA** plugin and point it to `checkstyle.xml`.

### VS Code

1. Install the **Extension Pack for Java** (Microsoft).
2. Open the repository folder; VS Code auto-detects the Maven project.
3. Run tests via the **Testing** sidebar.

## Troubleshooting

| Problem | Solution |
|---------|----------|
| `JAVA_HOME` not set | Export `JAVA_HOME` to your JDK 17 installation path |
| `mvn: command not found` | Add Maven `bin/` to your `PATH` |
| Checkstyle failures | Run `mvn checkstyle:check` and fix the reported lines |
