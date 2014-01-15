package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.input.InputChannel;
import ro.ghionoiu.twitter.input.SystemConsoleInput;

/**
 * Hello world!
 *
 */
public class Server {
    private InputChannel inputChannnel;
    private Engine engine;
    
    protected Server(InputChannel inputChannel, Engine engine) {
        this.inputChannnel = inputChannel;
        this.engine = engine;
    }

    public Server() {
        inputChannnel = new SystemConsoleInput();
        engine = null;
    }

    public void start() {
        String command;
        do {
            command = inputChannnel.readCommand();
            engine.processCommand(command);
        } while (command != null);
    }
}
