/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import ro.ghionoiu.twitter.backend.message.Message;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import ro.ghionoiu.twitter.context.output.OutputChannel;
import ro.ghionoiu.twitter.context.ApplicationContext;
import ro.ghionoiu.twitter.context.time.ManualClock;


/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BackendTest {
    public static final String SOME_PLAIN_MESSAGE = "message";
    private static final String SOME_USER = "Alice";
    private static final OutputChannel OUTPUT_CHANNEL = mock(OutputChannel.class);
    private static final long CURRENT_TIME = 3;
    
    
    @Test
    public void first_message_for_user_will_create_its_timeline() {
        Map<String,Timeline> timelinesOfUsers = new HashMap<String, Timeline>();
        Backend instance = createInstanceWithGivenTimelineMap(timelinesOfUsers);
        
        instance.storeMessageForUser(SOME_USER, SOME_PLAIN_MESSAGE);
        
        assertThat("Username should have been stored in map",
                timelinesOfUsers.containsKey(SOME_USER),is(true));
    }
    
    @Test
    public void message_is_added_to_user_timeline_together_with_the_current_time() {
        Timeline timeline = mock(Timeline.class);
        Backend instance = createBackendWithPreparedTimeline(timeline, SOME_USER);
        
        instance.storeMessageForUser(SOME_USER, SOME_PLAIN_MESSAGE);
        
        verify(timeline).add(new Message(CURRENT_TIME, SOME_PLAIN_MESSAGE));
    }
    
    @Test
    public void display_command_will_be_forwarded_to_the_users_timeline() {
        Timeline timeline = mock(Timeline.class);
        Backend instance = createBackendWithPreparedTimeline(timeline, SOME_USER);
        
        instance.displayTimelineFor(SOME_USER);
        
        verify(timeline).displayTo(OUTPUT_CHANNEL, CURRENT_TIME);
    }

    //~~~~~~~~~~~ Private helpers 
    
    private Backend createBackendWithPreparedTimeline(Timeline timeline, String user) {
        ApplicationContext applicationContext = createApplicationContext();
        Map<String,Timeline> timelinesOfUsers = new HashMap<String, Timeline>();
        timelinesOfUsers.put(user, timeline);
        Backend instance = new Backend(applicationContext, timelinesOfUsers);
        return instance;
    }

    private Backend createInstanceWithGivenTimelineMap(Map<String, Timeline> storageMap) {
        ApplicationContext applicationContext = createApplicationContext();
        Backend instance = new Backend(applicationContext, storageMap);
        return instance;
    }

    private ApplicationContext createApplicationContext() {
        ManualClock localClock = new ManualClock();
        localClock.setCurrentTimeMillis(CURRENT_TIME);
        ApplicationContext applicationContext = 
                mock(ApplicationContext.class, Mockito.RETURNS_MOCKS);
        when(applicationContext.getClock()).thenReturn(localClock);
        when(applicationContext.getOutputChannel()).thenReturn(OUTPUT_CHANNEL);
        return applicationContext;
    }
    
}