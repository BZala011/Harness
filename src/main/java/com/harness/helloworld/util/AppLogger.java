package com.harness.helloworld.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Lightweight structured logger — wraps System.out/err until a logging framework is introduced.
 */
public class AppLogger {

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String source;

    public AppLogger(String source) {
        this.source = source;
    }

    public void info(String message) {
        log("INFO ", System.out, message);
    }

    public void warn(String message) {
        log("WARN ", System.out, message);
    }

    public void error(String message) {
        log("ERROR", System.err, message);
    }

    private void log(String level, java.io.PrintStream stream, String message) {
        stream.printf("[%s] [%s] [%s] %s%n",
                LocalDateTime.now().format(FMT), level, source, message);
    }
}
