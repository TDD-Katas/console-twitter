/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command.handlers;

import ro.ghionoiu.twitter.backend.Backend;
import ro.ghionoiu.twitter.command.CommandHandler;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FollowCommandHandler implements CommandHandler {
    private static final String TAG = "follows";
    private static final String ANY_SPACE = "\\s*";
    
    private Backend backend;

    public FollowCommandHandler(Backend backend) {
        this.backend = backend;
    }
    
    @Override
    public boolean canHandle(String command) {
        boolean canHandle = false;
        if(command.contains(TAG)) {
            canHandle = true;
        }
        
        return canHandle;
    }

    @Override
    public void processCommand(String command) {
        String[] parts = command.split(ANY_SPACE+TAG+ANY_SPACE);
        String user = parts[0];
        String followedUser = "";
        if (parts.length > 1) {
            followedUser = parts[1];
        }
        backend.oneUserFollowsAnother(user, followedUser);
    }
}
