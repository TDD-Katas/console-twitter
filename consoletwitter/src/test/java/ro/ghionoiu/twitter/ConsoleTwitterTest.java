/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ConsoleTwitterTest {
    
    @Test
    public void reads_commands_from_input_channel() {
        InputChannel mockInputChannel = mock(InputChannel.class);
        ConsoleTwitter consoleTwitter = new ConsoleTwitter(mockInputChannel);
        
        consoleTwitter.start();
        
        verify(mockInputChannel,times(1)).readCommand();
    }
    
    @Test
    public void exits_when_receives_null_input() {
        InputChannel mockInputChannel = mock(InputChannel.class);
        when(mockInputChannel.readCommand()).thenReturn("command", (String) null);
        ConsoleTwitter consoleTwitter = new ConsoleTwitter(mockInputChannel);
        
        consoleTwitter.start();
        
        verify(mockInputChannel,times(2)).readCommand();
    }
}