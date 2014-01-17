/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command.handlers;

import ro.ghionoiu.twitter.command.CommandHandler;
import ro.ghionoiu.twitter.storage.StorageSystem;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class PostCommandHandler implements CommandHandler {
    private static final String TAG = "->";
    private static final String ANY_SPACE = "\\s*";
    
    //Members
    private StorageSystem storageSystem;

    public PostCommandHandler() {
        this.storageSystem = null;
    }

    public void setStorageSystem(StorageSystem storageSystem) {
        this.storageSystem = storageSystem;
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
        String username = parts[0];
        String message = "";
        if (parts.length > 1) {
            message = parts[1];
        }
        storageSystem.storeMessageForUser(username, message);
    }
}
