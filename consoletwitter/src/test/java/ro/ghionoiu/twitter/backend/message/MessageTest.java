/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend.message;

import ro.ghionoiu.twitter.backend.message.Message;
import ro.ghionoiu.twitter.backend.message.TimeDifferenceFormater;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;
import ro.ghionoiu.twitter.context.output.OutputChannel;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class MessageTest {
    public static final String SOME_CONTENT = "content";
    private static final String TIME_DIFFERENCE = "time";
    public static final int SOME_TIME = 0;
    
    @Test
    public void equals_should_respect_contract() {
        EqualsVerifier.forClass(Message.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }
    
    
    @Test
    public void should_display_content_and_time_difference() {
        Message instance = createMessage(SOME_CONTENT);
        OutputChannel outputChannel = mock(OutputChannel.class);
        
        instance.displayTo(outputChannel, SOME_TIME);
        
        String expectedMessage = SOME_CONTENT+" "+TIME_DIFFERENCE;
        verify(outputChannel).writeMessage(expectedMessage);
    }

    //~~~~~~~~~ Test helpers
    
    protected Message createMessage(String content) {
        TimeDifferenceFormater timeDifferenceFormater =
                mock(TimeDifferenceFormater.class);
        when(timeDifferenceFormater.formatTimeDifference(0))
                .thenReturn(TIME_DIFFERENCE);
        Message instance = new Message(timeDifferenceFormater, SOME_TIME, content);
        return instance;
    }
}