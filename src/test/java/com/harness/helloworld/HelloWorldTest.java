package com.harness.helloworld;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HelloWorld")
class HelloWorldTest {

    private HelloWorld subject;

    @BeforeEach
    void setUp() {
        subject = new HelloWorld();
    }

    @Test
    @DisplayName("getGreeting() returns 'Hello, autonomous ship!'")
    void defaultGreeting() {
        assertEquals("Hello, autonomous ship!", subject.getGreeting());
    }

    @Test
    @DisplayName("getGreeting(name) returns personalised message")
    void namedGreeting() {
        assertEquals("Hello, Harness!", subject.getGreeting("Harness"));
    }

    @Test
    @DisplayName("getGreeting(null) falls back to default")
    void nullNameFallsBack() {
        assertEquals("Hello, autonomous ship!", subject.getGreeting(null));
    }

    @Test
    @DisplayName("getGreeting(blank) falls back to default")
    void blankNameFallsBack() {
        assertEquals("Hello, autonomous ship!", subject.getGreeting("   "));
    }

    @Test
    @DisplayName("getGreeting(name) trims leading/trailing whitespace")
    void trimsName() {
        assertEquals("Hello, Harness!", subject.getGreeting("  Harness  "));
    }
}
