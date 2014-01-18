/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import ro.ghionoiu.twitter.backend.message.Message;
import java.util.HashMap;
import java.util.Map;
import ro.ghionoiu.twitter.backend.follow.FollowList;
import ro.ghionoiu.twitter.context.ApplicationContext;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Backend {
    private ApplicationContext applicationContext;
    private Map<String,Timeline> timelinesOfUsers;
    private Map<String,FollowList> followListsOfUsers;
    
    public Backend(ApplicationContext applicationContext) {
        this(applicationContext, 
             new HashMap<String, Timeline>(),
             new HashMap<String, FollowList>());
    }
    
    protected Backend(ApplicationContext applicationContext, 
            Map<String, Timeline> timelinesOfUsers,
            Map<String, FollowList> followListsOfUsers) {
        this.applicationContext = applicationContext;
        this.timelinesOfUsers = timelinesOfUsers;
        this.followListsOfUsers = followListsOfUsers;
    }
    
    //~~~~~~~~ Public methods
    
    public void storeMessageForUser(String user, String plainMessage) {
        Timeline userTimeline = getUserTimeline(user);
        
        long currentTime = applicationContext.getClock().currentTimeMillis();
        userTimeline.add(new Message(currentTime, user, plainMessage));
    }
    
    public void oneUserFollowsAnother(String user, String followedUser) {
        FollowList userFollowList = getUserFollowList(user);
        
        userFollowList.add(followedUser);
    }
    
    
    public void displayTimelineFor(String user) {
        if (timelinesOfUsers.containsKey(user)) {
            long currentTime = applicationContext.getClock().currentTimeMillis();
            Timeline timeline = timelinesOfUsers.get(user);
            timeline.displayAsOwnTo(applicationContext.getOutputChannel(), currentTime);
        }
    }
    
    public void displayWallFor(String user) {
        Timeline agregateTimeline = new Timeline();
        
        //Add self
        agregateTimeline.addAll(getUserTimeline(user));
        
        //Add followed list
        FollowList followList = getUserFollowList(user);
        for (String followedUser : followList) {
            agregateTimeline.addAll(getUserTimeline(followedUser));
        }
        
        //Display agregate timeline
        long currentTime = applicationContext.getClock().currentTimeMillis();
        agregateTimeline.displayAsWallTo(applicationContext.getOutputChannel(), currentTime);
    }

    //~~~~~~~~~~ Helper methods
    
    private Timeline getUserTimeline(String user) {
        Timeline userTimeline;
        if (timelinesOfUsers.containsKey(user)) {
            userTimeline = timelinesOfUsers.get(user);
        } else {
            userTimeline = new Timeline();
            timelinesOfUsers.put(user, userTimeline);
        }
        return userTimeline;
    }

    private FollowList getUserFollowList(String user) {
        FollowList userFollowList;
        if (followListsOfUsers.containsKey(user)) {
            userFollowList = followListsOfUsers.get(user);
        } else {
            userFollowList = new FollowList();
            followListsOfUsers.put(user, userFollowList);
        }
        return userFollowList;
    }
}
