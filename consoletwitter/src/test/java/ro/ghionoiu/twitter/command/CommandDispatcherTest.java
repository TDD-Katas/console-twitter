/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command;

import org.junit.Test;
import org.mockito.Mockito;
import ro.ghionoiu.twitter.context.output.OutputChannel;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import ro.ghionoiu.twitter.context.input.InputChannel;
import ro.ghionoiu.twitter.context.ApplicationContext;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class CommandDispatcherTest {
    public static final String SOME_COMMAND = "test command";
    
    @Test
    public void sends_command_to_handler_that_can_handle_it() {
        CommandHandler capableHandler = capableHandler();
        CommandDispatcher instance = createDispatcher(capableHandler);
        
        instance.processCommand(SOME_COMMAND);
        
        verify(capableHandler).processCommand(SOME_COMMAND);
    }
    
    @Test
    public void do_not_send_command_to_handler_that_cannot_handle_it() {
        CommandHandler uncapableHandler = uncapableHandler();
        CommandDispatcher instance = createDispatcher(uncapableHandler);
        
        instance.processCommand(SOME_COMMAND);
        
        verify(uncapableHandler,never()).processCommand(SOME_COMMAND);
    }
    
    @Test
    public void sends_command_to_first_handler_that_can_handle_it() {
        CommandHandler firstHandler = capableHandler();
        CommandHandler secondHandler = capableHandler();
        CommandDispatcher instance = createDispatcher(firstHandler, secondHandler);
        
        instance.processCommand(SOME_COMMAND);
        
        verify(firstHandler).processCommand(SOME_COMMAND);
        verify(secondHandler,never()).processCommand(SOME_COMMAND);
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

    protected CommandDispatcher createDispatcher(CommandHandler ... handlers) {
        ApplicationContext applicationContext = 
                mock(ApplicationContext.class, Mockito.RETURNS_MOCKS);
        CommandDispatcher instance = new CommandDispatcher(applicationContext);
        instance.setCommandHandlers(handlers);
        return instance;
    }
}