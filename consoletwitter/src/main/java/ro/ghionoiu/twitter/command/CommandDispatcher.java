/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command;

import ro.ghionoiu.twitter.channels.OutputChannel;
import ro.ghionoiu.twitter.channels.output.SystemConsoleOutput;
import ro.ghionoiu.twitter.command.CommandHandler;
import ro.ghionoiu.twitter.command.handlers.FollowCommandHandler;
import ro.ghionoiu.twitter.command.handlers.PostCommandHandler;
import ro.ghionoiu.twitter.command.handlers.ReadCommandHandler;
import ro.ghionoiu.twitter.command.handlers.WallCommandHandler;

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
        commandHandlers= new CommandHandler[]{
            new PostCommandHandler(),
            new FollowCommandHandler(),
            new WallCommandHandler(),
            new ReadCommandHandler()
        };
    }

    public void setOutputChannel(OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    public void setCommandHandlers(CommandHandler[] commandHandlers) {
        this.commandHandlers = commandHandlers;
    }

    public CommandHandler[] getCommandHandlers() {
        return commandHandlers;
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
