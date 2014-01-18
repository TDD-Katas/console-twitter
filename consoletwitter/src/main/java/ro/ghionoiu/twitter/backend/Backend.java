/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

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
    
    public void storeMessageForUser(String user, String plainMessage) {
        Timeline userTimeline;
        if (storageMap.containsKey(user)) {
            userTimeline = storageMap.get(user);
        } else {
            userTimeline = new Timeline();
            storageMap.put(user, userTimeline);
        }
        
        userTimeline.add(new Message(plainMessage));
    }
    
    public void displayTimelineFor(String user) {
        if (storageMap.containsKey(user)) {
            Timeline timeline = storageMap.get(user);
            timeline.displayTo(applicationContext.getOutputChannel());
        }
    }
}
