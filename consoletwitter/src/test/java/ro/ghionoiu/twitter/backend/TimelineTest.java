/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import ro.ghionoiu.twitter.backend.message.Message;
import org.junit.Test;
import org.mockito.InOrder;
import ro.ghionoiu.twitter.context.output.OutputChannel;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class TimelineTest {
    public static final long CURRENT_TIME = 2;
    
    @Test
    public void display_will_output_all_messages_in_reverse_creation_order() {
        Timeline instance = new Timeline();
        Message firstMessage = createSomeMessage(0);
        Message secondMessage = createSomeMessage(2);
        instance.add(firstMessage);
        instance.add(secondMessage);
        OutputChannel outputChannel = mock(OutputChannel.class);
        
        instance.displayTo(outputChannel, CURRENT_TIME);
        
        InOrder inOrder = inOrder(secondMessage, firstMessage);
        inOrder.verify(secondMessage).displayTo(outputChannel, CURRENT_TIME);
        inOrder.verify(firstMessage).displayTo(outputChannel, CURRENT_TIME);
    }

    //~~~~~~~ Test helper
    
    protected Message createSomeMessage(long creationTime) {
        Message message = mock(Message.class);
        when(message.getCreationTime()).thenReturn(creationTime);
        return message;
    }
}