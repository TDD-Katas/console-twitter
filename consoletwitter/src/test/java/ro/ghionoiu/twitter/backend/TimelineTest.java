/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import ro.ghionoiu.twitter.backend.message.Message;
import java.util.Arrays;
import org.junit.Test;
import ro.ghionoiu.twitter.context.output.OutputChannel;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class TimelineTest {
    public static final long CURRENT_TIME = 2;
    
    @Test
    public void display_will_output_all_messages() {
        Timeline instance = new Timeline();
        Message[] messages = new Message[] { 
            createSomeMessage(), createSomeMessage() };
        instance.addAll(Arrays.asList(messages));
        OutputChannel outputChannel = mock(OutputChannel.class);
        
        instance.displayTo(outputChannel, CURRENT_TIME);
        
        for (Message message : messages) {
            verify(message).displayTo(outputChannel, CURRENT_TIME);
        }
    }

    //~~~~~~~ Test helper
    
    protected Message createSomeMessage() {
        return mock(Message.class);
    }
}