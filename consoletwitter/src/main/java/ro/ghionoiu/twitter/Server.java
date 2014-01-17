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
    
    public Server() {
        inputChannnel = new SystemConsoleInput();
        engine = new Engine();
    }

    public void setInputChannnel(InputChannel inputChannnel) {
        this.inputChannnel = inputChannnel;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    //~~~~~~~ Run
    
    public void start() {
        String command;
        do {
            command = inputChannnel.readLine();
            if (command != null) {
                engine.processCommand(command);
            } else {
                break;
            }
        } while (true);
    }
}
