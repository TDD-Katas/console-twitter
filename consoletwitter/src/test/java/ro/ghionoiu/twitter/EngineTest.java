/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import org.junit.Test;
import ro.ghionoiu.twitter.channels.OutputChannel;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import ro.ghionoiu.twitter.commands.CommandHandler;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class EngineTest {
    public static final String SOME_COMMAND = "test command";
    
    @Test
    public void sends_command_to_handler_that_can_handle_it() {
        String command = SOME_COMMAND;
        CommandHandler matchingHandler = capableHandler();
        CommandHandler[] handlers = new CommandHandler[]{matchingHandler};
        
        processCommand(handlers, command);
        
        verify(matchingHandler).processCommand(SOME_COMMAND);
    }
    
    @Test
    public void do_not_send_command_to_handler_that_cannot_handle_it() {
        String command = SOME_COMMAND;
        CommandHandler uncapableHandler = uncapableHandler();
        CommandHandler[] handlers = new CommandHandler[]{uncapableHandler};
        
        processCommand(handlers, command);
        
        verify(uncapableHandler,never()).processCommand(SOME_COMMAND);
    }
    
    @Test
    public void sends_command_to_first_handler_that_can_handle_it() {
        String command = SOME_COMMAND;
        CommandHandler firstHandler = capableHandler();
        CommandHandler secondHandler = capableHandler();
        CommandHandler[] handlers = new CommandHandler[]{firstHandler, secondHandler};
        
        processCommand(handlers, command);
        
        verify(firstHandler).processCommand(SOME_COMMAND);
        verify(secondHandler,never()).processCommand(SOME_COMMAND);
    }
    
    @Test
    public void sends_command_to_output_channel() {
        OutputChannel outputChannel = mock(OutputChannel.class);
        Engine engine = new Engine();
        engine.setOutputChannel(outputChannel);
        
        engine.processCommand(SOME_COMMAND);
        
        verify(outputChannel).writeMessage(SOME_COMMAND);
    }
    
    //~~~~~~ Production method

    protected void processCommand(CommandHandler[] handlers, String command) {
        for (CommandHandler commandHandler : handlers) {
            if (commandHandler.canHandle(command)) {
                commandHandler.processCommand(command);
                break;
            }
        }
    }
    
    //~~~~~~ Test helpers

    protected CommandHandler capableHandler() {
        return handlerWithAbilityToHandle(true);
    }

    protected CommandHandler uncapableHandler() {
        return handlerWithAbilityToHandle(false);
    }
    
    protected CommandHandler handlerWithAbilityToHandle(boolean canHandleStrings) {
        CommandHandler nonCapableHandler = mock(CommandHandler.class);
        when(nonCapableHandler.canHandle(any(String.class)))
                .thenReturn(canHandleStrings);
        return nonCapableHandler;
    }
}