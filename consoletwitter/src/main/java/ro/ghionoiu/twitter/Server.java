package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.channels.InputChannel;
import ro.ghionoiu.twitter.channels.input.SystemConsoleInput;

/**
 * Hello world!
 *
 */
public class Server {
    private InputChannel inputChannnel;
    private Engine engine;
    
    //~~~~~~~ Construct
    
    protected Server(InputChannel inputChannel, Engine engine) {
        this.inputChannnel = inputChannel;
        this.engine = engine;
    }

    public Server() {
        inputChannnel = new SystemConsoleInput();
        engine = new Engine();
    }

    //~~~~~~~ Run
    
    public void start() {
        String command;
        do {
            command = inputChannnel.readCommand();
            engine.processCommand(command);
        } while (command != null);
    }
}
