/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import ro.ghionoiu.twitter.channels.OutputChannel;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BackendTest {
    
    @Test
    public void first_message_for_user_will_be_stored_in_map() {
        String username = "Alice";
        String firstMessage = "message1";
        OutputChannel outputChannel = mock(OutputChannel.class);
        Map<String,List<String>> storageMap = new HashMap<String, List<String>>();
        Backend instance = new Backend(outputChannel, storageMap);
        
        instance.storeMessageForUser(username, firstMessage);
        
        assertThat("Username should have been stored in map",
                storageMap.containsKey(username),is(true));
        assertThat("First message should have been found in the user timeline",
                storageMap.get(username).contains(firstMessage),is(true));
    }
    
    @Test
    public void second_message_will_be_added_to_existing_messages() {
        String username = "Alice";
        String secondMessage = "message2";
        OutputChannel outputChannel = mock(OutputChannel.class);
        Map<String,List<String>> storageMap = new HashMap<String, List<String>>();
        List<String> existingMessages = new LinkedList<String>();
        existingMessages.add("message1");
        storageMap.put(username, existingMessages);
        Backend instance = new Backend(outputChannel, storageMap);
        
        instance.storeMessageForUser(username, secondMessage);
        
        assertThat("Size of list should have increased",
                storageMap.get(username).size(),is(2));
        assertThat("Second message should have been found in the user timeline",
                storageMap.get(username).contains(secondMessage),is(true));
    }
    
    
    @Test
    public void displaying_timeline_for_user_will_send_all_its_messages_to_output_channel() {
        String username = "Alice";
        List<String> messages = Arrays.asList(new String[] {
            "message1",
            "message2"
        });
        OutputChannel outputChannel = mock(OutputChannel.class);
        Map<String,List<String>> storageMap = new HashMap<String, List<String>>();
        storageMap.put(username, messages);
        Backend instance = new Backend(outputChannel, storageMap);
        
        instance.displayTimelineFor(username);
        
        for (String message : messages) {
            verify(outputChannel).writeMessage(message);
        }
    }
    
}