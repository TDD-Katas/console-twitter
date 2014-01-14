package ro.ghionoiu.twitter;

import java.io.Console;

/**
 * Hello world!
 *
 */
public class ConsoleTwitter {

    public static void main(String[] args) {
        Console console = System.console();
        String username = console.readLine("Name?\n");

        console.printf("Welcome, %1$s.\n", username);
        console.printf("Bye.\n");
    }
}
