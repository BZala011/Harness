package com.harness.helloworld;

import com.harness.helloworld.util.AppLogger;

/**
 * Entry point for the Harness Hello World application.
 */
public class App {

    private static final AppLogger LOGGER = new AppLogger(App.class.getName());

    public static void main(String[] args) {
        LOGGER.info("Application starting");
        try {
            HelloWorld helloWorld = new HelloWorld();
            String message = helloWorld.getGreeting();
            System.out.println(message);
            LOGGER.info("Application completed successfully");
        } catch (Exception e) {
            LOGGER.error("Unexpected error: " + e.getMessage());
            System.exit(1);
        }
    }
}
