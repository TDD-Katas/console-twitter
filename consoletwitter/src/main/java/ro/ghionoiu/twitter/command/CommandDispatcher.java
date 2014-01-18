/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command;

import ro.ghionoiu.twitter.backend.Backend;
import ro.ghionoiu.twitter.command.handlers.FollowCommandHandler;
import ro.ghionoiu.twitter.command.handlers.PostCommandHandler;
import ro.ghionoiu.twitter.command.handlers.ReadCommandHandler;
import ro.ghionoiu.twitter.command.handlers.WallCommandHandler;
import ro.ghionoiu.twitter.context.ApplicationContext;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class CommandDispatcher {
    private Backend backend;
    private CommandHandler[] commandHandlers;

    //~~~~~~~ Construct

    public CommandDispatcher(ApplicationContext applicationContext) {
        backend = new Backend(applicationContext);
        commandHandlers= new CommandHandler[]{
            new PostCommandHandler(backend),
            new FollowCommandHandler(),
            new WallCommandHandler(),
            new ReadCommandHandler(backend)
        };
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
    }
}
