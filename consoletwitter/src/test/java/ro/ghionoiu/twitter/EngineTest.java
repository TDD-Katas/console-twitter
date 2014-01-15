/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import org.junit.Test;
import ro.ghionoiu.twitter.channels.OutputChannel;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class EngineTest {
    private static final String SOME_COMMAND = "message";
    
    @Test
    public void sends_command_to_output_channel() {
        OutputChannel outputChannel = mock(OutputChannel.class);
        Engine engine = new Engine(outputChannel);
        
        engine.processCommand(SOME_COMMAND);
        
        verify(outputChannel).writeMessage(SOME_COMMAND);
    }
}