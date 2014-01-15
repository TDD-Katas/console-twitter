package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.input.SystemConsoleInput;

/**
 * Hello world!
 *
 */
public class ConsoleTwitter {
    private InputChannel inputChannnel;

    public ConsoleTwitter(InputChannel inputChannel) {
        this.inputChannnel = inputChannel;
    }

    public ConsoleTwitter() {
        inputChannnel = new SystemConsoleInput();
    }

    public void start() {
        String command;
        do {
            command = inputChannnel.readCommand();
        } while (command != null);
    }
}
