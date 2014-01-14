/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ConsoleTwitterTest {
    
    @Test
    public void applicationReadsFromInputChannel() {
        InputChannel mockInputChannel = mock(InputChannel.class);
        ConsoleTwitter consoleTwitter = new ConsoleTwitter(mockInputChannel);
        consoleTwitter.start();
        
        verify(mockInputChannel,times(1)).readCommand();
    }
}