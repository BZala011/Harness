package com.harness.helloworld;

/**
 * Core domain class encapsulating greeting logic.
 */
public class HelloWorld {

    private static final String DEFAULT_GREETING = "Hi Steve";

    /**
     * Returns the default greeting.
     */
    public String getGreeting() {
        return DEFAULT_GREETING;
    }

    /**
     * Returns a personalised greeting, falling back to the default when name is null or blank.
     */
    public String getGreeting(String name) {
        if (name == null || name.isBlank()) {
            return DEFAULT_GREETING;
        }
        return "Hello, " + name.trim() + "!";
    }
}
