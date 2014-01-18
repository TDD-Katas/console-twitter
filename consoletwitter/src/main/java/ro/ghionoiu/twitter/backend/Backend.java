/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import ro.ghionoiu.twitter.backend.message.Message;
import java.util.HashMap;
import java.util.Map;
import ro.ghionoiu.twitter.context.ApplicationContext;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Backend {
    private ApplicationContext applicationContext;
    private Map<String,Timeline> storageMap;
    
    public Backend(ApplicationContext applicationContext) {
        this(applicationContext, new HashMap<String, Timeline>());
    }
    
    protected Backend(ApplicationContext applicationContext, 
            Map<String, Timeline> storageMap) {
        this.applicationContext = applicationContext;
        this.storageMap = storageMap;
    }
    
    //~~~~~~~~ Public methods
    
    public void storeMessageForUser(String user, String plainMessage) {
        Timeline userTimeline;
        if (storageMap.containsKey(user)) {
            userTimeline = storageMap.get(user);
        } else {
            userTimeline = new Timeline();
            storageMap.put(user, userTimeline);
        }
        
        long currentTime = applicationContext.getClock().currentTimeMillis();
        userTimeline.add(new Message(currentTime, plainMessage));
    }
    
    public void oneUserFollowsAnother(String user, String followedUser) {
        
    }
    
    
    public void displayTimelineFor(String user) {
        if (storageMap.containsKey(user)) {
            long currentTime = applicationContext.getClock().currentTimeMillis();
            Timeline timeline = storageMap.get(user);
            timeline.displayTo(applicationContext.getOutputChannel(), currentTime);
        }
    }
    
    public void displayWallFor(String user) {
    }
}
