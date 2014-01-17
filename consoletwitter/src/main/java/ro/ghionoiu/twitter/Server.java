package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.command.CommandDispatcher;
import ro.ghionoiu.twitter.channels.InputChannel;
import ro.ghionoiu.twitter.channels.input.SystemConsoleInput;

/**
 * Hello world!
 *
 */
public class Server {
    private InputChannel inputChannnel;
    private CommandDispatcher commandDispatcher;
    
    //~~~~~~~ Construct
    
    public Server() {
        inputChannnel = new SystemConsoleInput();
        commandDispatcher = new CommandDispatcher();
    }

    public void setInputChannnel(InputChannel inputChannnel) {
        this.inputChannnel = inputChannnel;
    }

    public void setEngine(CommandDispatcher engine) {
        this.commandDispatcher = engine;
    }

    //~~~~~~~ Run
    
    public void start() {
        String command;
        do {
            command = inputChannnel.readLine();
            if (command != null) {
                commandDispatcher.processCommand(command);
            } else {
                break;
            }
        } while (true);
    }
}
