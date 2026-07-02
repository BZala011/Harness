package com.harness.helloworld;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("App (integration)")
class AppTest {

    @Test
    @DisplayName("main() prints 'Hello 888' to stdout")
    void mainPrintsGreeting() {
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(captured));
        try {
            App.main(new String[]{});
        } finally {
            System.setOut(original);
        }
        assertTrue(captured.toString().contains("Hello 888"));
    }
}
