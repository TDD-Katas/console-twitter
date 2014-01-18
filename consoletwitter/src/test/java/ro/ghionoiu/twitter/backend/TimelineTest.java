/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import java.util.Arrays;
import org.junit.Test;
import ro.ghionoiu.twitter.channels.OutputChannel;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class TimelineTest {
    public static final String SOME_MESSAGE = "message1";
    public static final String OTHER_MESSAGE = "message2";
    
    @Test
    public void display_will_output_all_messages() {
        Timeline instance = new Timeline();
        String[] messages = new String[] { 
            SOME_MESSAGE, OTHER_MESSAGE };
        instance.addAll(Arrays.asList(messages));
        OutputChannel outputChannel = mock(OutputChannel.class);
        
        instance.displayTo(outputChannel);
        
        for (String message : messages) {
            verify(outputChannel).writeMessage(message);
        }
    }
}