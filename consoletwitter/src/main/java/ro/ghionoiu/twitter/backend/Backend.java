/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ro.ghionoiu.twitter.channels.OutputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Backend {
    private Map<String,List<String>> storageMap;
    private OutputChannel outputChannel;
    
    public Backend(OutputChannel outputChannel) {
        this(outputChannel, new HashMap<String, List<String>>());
    }
    
    protected Backend(OutputChannel outputChannel, Map<String, List<String>> storageMap) {
        this.outputChannel = outputChannel;
        this.storageMap = storageMap;
    }
    
    public void storeMessageForUser(String user, String message) {
        List<String> userTimeline;
        if (storageMap.containsKey(user)) {
            userTimeline = storageMap.get(user);
        } else {
            userTimeline = new LinkedList<String>();
            storageMap.put(user, userTimeline);
        }
        
        userTimeline.add(message);
    }
    
    public void displayTimelineFor(String user) {
        if (storageMap.containsKey(user)) {
            List<String> messages = storageMap.get(user);
            for (String message : messages) {
                outputChannel.writeMessage(message);
            }
        }
    }
}
