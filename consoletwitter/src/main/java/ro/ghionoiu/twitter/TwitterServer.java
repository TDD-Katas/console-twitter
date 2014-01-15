package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.input.InputChannel;
import ro.ghionoiu.twitter.input.SystemConsoleInput;

/**
 * Hello world!
 *
 */
public class TwitterServer {
    private InputChannel inputChannnel;

    public TwitterServer(InputChannel inputChannel) {
        this.inputChannnel = inputChannel;
    }

    public TwitterServer() {
        inputChannnel = new SystemConsoleInput();
    }

    public void start() {
        String command;
        do {
            command = inputChannnel.readCommand();
        } while (command != null);
    }
}
