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
import static org.mockito.Mockito.when;
import ro.ghionoiu.twitter.backend.follow.FollowList;
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
    private static final String OTHER_USER = "Bob";
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
        
        verify(timeline).add(new Message(CURRENT_TIME, SOME_USER, SOME_PLAIN_MESSAGE));
    }
    
    @Test
    public void first_follow_for_user_will_create_its_follow_list() {
        Map<String,FollowList> followListOfUser = new HashMap<String, FollowList>();
        Backend instance = createInstanceWithGivenFollowedMap(followListOfUser);
        
        instance.oneUserFollowsAnother(SOME_USER, OTHER_USER);
        
        assertThat("Username should have been stored in map",
                followListOfUser.containsKey(SOME_USER),is(true));
    }
    
    @Test
    public void following_a_user_will_add_him_to_the_user_follow_list() {
        FollowList followList = mock(FollowList.class);
        Backend instance = createBackendWithPreparedFollowedList(followList, SOME_USER);
        
        instance.oneUserFollowsAnother(SOME_USER, OTHER_USER);
        
        verify(followList).add(OTHER_USER);
    }
    
    @Test
    public void display_timeline_command_will_be_forwarded_to_the_users_timeline() {
        Timeline timeline = mock(Timeline.class);
        Backend instance = createBackendWithPreparedTimeline(timeline, SOME_USER);
        
        instance.displayTimelineFor(SOME_USER);
        
        verify(timeline).displayAsOwnTo(OUTPUT_CHANNEL, CURRENT_TIME);
    }
    
    //~~~~~~~~~~~ Private helpers 
    
    private Backend createBackendWithPreparedTimeline(Timeline timeline, String user) {
        ApplicationContext applicationContext = createApplicationContext();
        Map<String,Timeline> timelinesOfUsers = new HashMap<String, Timeline>();
        timelinesOfUsers.put(user, timeline);
        Backend instance = new Backend(applicationContext, timelinesOfUsers, emptyFollowList());
        return instance;
    }

    private Backend createInstanceWithGivenTimelineMap(Map<String, Timeline> timelinesOfUsers) {
        ApplicationContext applicationContext = createApplicationContext();
        Backend instance = new Backend(applicationContext, timelinesOfUsers, emptyFollowList());
        return instance;
    }
    
    private Backend createBackendWithPreparedFollowedList(FollowList followList, String user) {
        ApplicationContext applicationContext = createApplicationContext();
        Map<String,FollowList> followListsOfUsers = new HashMap<String, FollowList>();
        followListsOfUsers.put(user, followList);
        Backend instance = new Backend(applicationContext, emptyTimelines(), followListsOfUsers);
        return instance;
    }
    
    private Backend createInstanceWithGivenFollowedMap(Map<String, FollowList> followListsOfUsers) {
        ApplicationContext applicationContext = createApplicationContext();
        Backend instance = new Backend(applicationContext, emptyTimelines(), followListsOfUsers);
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

    protected HashMap<String, Timeline> emptyTimelines() {
        return new HashMap<String, Timeline>();
    }
    
    protected HashMap<String, FollowList> emptyFollowList() {
        return new HashMap<String, FollowList>();
    }
}