package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.input.SystemConsoleInput;

/**
 * Hello world!
 *
 */
public class ConsoleTwitter {
    private InputChannel consoleInput;

    public ConsoleTwitter(InputChannel consoleInput) {
        this.consoleInput = consoleInput;
    }

    public ConsoleTwitter() {
        consoleInput = new SystemConsoleInput();
    }

    public void start() {
        consoleInput.readCommand();
    }
}
