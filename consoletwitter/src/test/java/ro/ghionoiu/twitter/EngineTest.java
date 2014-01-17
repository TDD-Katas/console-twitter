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

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class EngineTest {
    private static final String SOME_COMMAND = "message";
    
    interface CommandHandler {
        boolean canHandle(String command);
        
        void processCommand(String command);
    }
    
    @Test
    public void sends_command_to_handler_that_can_handle_it() {
        String command = "test command";
        CommandHandler matchingHandler = mock(CommandHandler.class);
        when(matchingHandler.canHandle(command)).thenReturn(true);
        CommandHandler[] handlers = new CommandHandler[]{matchingHandler};
        
        for (CommandHandler commandHandler : handlers) {
            if (commandHandler.canHandle(command)) {
                commandHandler.processCommand(command);
            }
        }
        
        verify(matchingHandler).processCommand(command);
    }
    
    @Test
    public void sends_command_to_output_channel() {
        OutputChannel outputChannel = mock(OutputChannel.class);
        Engine engine = new Engine();
        engine.setOutputChannel(outputChannel);
        
        engine.processCommand(SOME_COMMAND);
        
        verify(outputChannel).writeMessage(SOME_COMMAND);
    }
}