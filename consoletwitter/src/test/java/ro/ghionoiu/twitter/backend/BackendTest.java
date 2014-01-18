/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import ro.ghionoiu.twitter.channels.OutputChannel;
import ro.ghionoiu.twitter.context.ApplicationContext;


/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BackendTest {
    public static final String SOME_MESSAGE = "message";
    private static final String SOME_USER = "Alice";
    
    
    @Test
    public void first_message_for_user_will_create_its_timeline() {
        Map<String,Timeline> timelinesOfUsers = new HashMap<String, Timeline>();
        Backend instance = createInstanceWithGivenTimelineMap(timelinesOfUsers);
        
        instance.storeMessageForUser(SOME_USER, SOME_MESSAGE);
        
        assertThat("Username should have been stored in map",
                timelinesOfUsers.containsKey(SOME_USER),is(true));
    }
    
    @Test
    public void message_is_added_to_user_timeline() {
        Timeline timeline = mock(Timeline.class);
        Backend instance = createBackendWithPreparedTimeline(timeline, SOME_USER);
        
        instance.storeMessageForUser(SOME_USER, SOME_MESSAGE);
        
        verify(timeline).add(SOME_MESSAGE);
    }
    
    @Test
    public void display_command_will_be_forwarded_to_the_users_timeline() {
        Timeline timeline = mock(Timeline.class);
        Backend instance = createBackendWithPreparedTimeline(timeline, SOME_USER);
        
        instance.displayTimelineFor(SOME_USER);
        
        verify(timeline).displayTo(any(OutputChannel.class));
    }

    //~~~~~~~~~~~ Private helpers 
    
    protected Backend createBackendWithPreparedTimeline(Timeline timeline, String user) {
        ApplicationContext applicationContext = mock(ApplicationContext.class);
        Map<String,Timeline> timelinesOfUsers = new HashMap<String, Timeline>();
        timelinesOfUsers.put(user, timeline);
        Backend instance = new Backend(applicationContext, timelinesOfUsers);
        return instance;
    }

    protected Backend createInstanceWithGivenTimelineMap(Map<String, Timeline> storageMap) {
        ApplicationContext applicationContext = mock(ApplicationContext.class);
        Backend instance = new Backend(applicationContext, storageMap);
        return instance;
    }
    
}