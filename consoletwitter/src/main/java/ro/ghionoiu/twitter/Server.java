package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.command.CommandDispatcher;
import ro.ghionoiu.twitter.context.input.SystemConsoleInput;
import ro.ghionoiu.twitter.context.output.SystemConsoleOutput;
import ro.ghionoiu.twitter.context.ApplicationContext;
import ro.ghionoiu.twitter.context.time.RealClock;

/**
 * Hello world!
 *
 */
public class Server {
    private ApplicationContext applicationContext;
    private CommandDispatcher commandDispatcher;
    
    //~~~~~~~ Construct
    
    public Server() {
        this(new ApplicationContext(
                new SystemConsoleInput(), 
                new SystemConsoleOutput(),
                new RealClock()));
    }
    
    public Server(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        this.commandDispatcher = new CommandDispatcher(applicationContext);
    }

    public Server(ApplicationContext applicationContext, CommandDispatcher commandDispatcher) {
        this.applicationContext = applicationContext;
        this.commandDispatcher = commandDispatcher;
    }

    //~~~~~~~ Run
    
    public void start() {
        String command;
        do {
            command = applicationContext.getInputChannnel().readLine();
            if (command != null) {
                commandDispatcher.processCommand(command);
            } else {
                break;
            }
        } while (true);
    }
}
