# API Reference

This application has no HTTP or gRPC API surface — it is a command-line application.

## Public Java API

### `com.harness.helloworld.HelloWorld`

The primary domain class.

#### `getGreeting() → String`

Returns the default greeting string `"Hello everyone"`.

```java
HelloWorld hw = new HelloWorld();
String msg = hw.getGreeting(); // "Hello everyone"
```

#### `getGreeting(String name) → String`

Returns a personalised greeting. Falls back to `"Hello everyone"` when `name` is `null` or blank.

```java
hw.getGreeting("Harness");   // "Hello, Harness!"
hw.getGreeting(null);        // "Hello everyone"
hw.getGreeting("  ");        // "Hello everyone"
hw.getGreeting("  Alice  "); // "Hello, Alice!"
```

| Parameter | Type | Description |
|-----------|------|-------------|
| `name` | `String` | Recipient name; trimmed before use; nullable |

| Return | Description |
|--------|-------------|
| `String` | Non-null greeting string |

---

### `com.harness.helloworld.util.AppLogger`

Lightweight structured logger.

#### Constructor `AppLogger(String source)`

| Parameter | Description |
|-----------|-------------|
| `source` | Identifies the logging class (typically `ClassName.class.getName()`) |

#### Methods

| Method | Stream | Description |
|--------|--------|-------------|
| `info(String message)` | stdout | Informational message |
| `warn(String message)` | stdout | Warning message |
| `error(String message)` | stderr | Error message |

Log line format:

```
[yyyy-MM-dd HH:mm:ss] [LEVEL] [source] message
```

---

## Exit Codes

| Code | Meaning |
|------|---------|
| `0`  | Successful execution |
| `1`  | Unhandled exception |
