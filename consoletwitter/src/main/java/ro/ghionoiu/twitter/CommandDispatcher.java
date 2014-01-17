/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.channels.OutputChannel;
import ro.ghionoiu.twitter.channels.output.SystemConsoleOutput;
import ro.ghionoiu.twitter.command.CommandHandler;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class CommandDispatcher {
    private OutputChannel outputChannel;
    private CommandHandler[] commandHandlers;

    //~~~~~~~ Construct

    public CommandDispatcher() {
        outputChannel = new SystemConsoleOutput();
        commandHandlers= new CommandHandler[]{};
    }

    public void setOutputChannel(OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    public void setCommandHandlers(CommandHandler[] commandHandlers) {
        this.commandHandlers = commandHandlers;
    }
    
    //~~~~~~~ Run
    
    public void processCommand(String command) {
        for (CommandHandler commandHandler : commandHandlers) {
            if (commandHandler.canHandle(command)) {
                commandHandler.processCommand(command);
                break;
            }
        }
        
        outputChannel.writeMessage(command);
    }
}
