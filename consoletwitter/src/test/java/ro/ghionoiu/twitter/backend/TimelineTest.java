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
    public static final Message SOME_MESSAGE = new Message("message1");
    public static final Message OTHER_MESSAGE = new Message("message2");
    
    @Test
    public void display_will_output_all_messages() {
        Timeline instance = new Timeline();
        Message[] messages = new Message[] { 
            SOME_MESSAGE, OTHER_MESSAGE };
        instance.addAll(Arrays.asList(messages));
        OutputChannel outputChannel = mock(OutputChannel.class);
        
        instance.displayTo(outputChannel);
        
        for (Message message : messages) {
            verify(outputChannel).writeMessage(message.getContent());
        }
    }
}