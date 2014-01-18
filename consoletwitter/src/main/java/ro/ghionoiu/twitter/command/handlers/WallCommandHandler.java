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
public class WallCommandHandler implements CommandHandler {
    private static final String TAG = "wall";
    private static final String ANY_SPACE = "\\s*";
    
    private Backend backend;

    public WallCommandHandler(Backend backend) {
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
        String username = parts[0];
        backend.displayWallFor(username);
    }
}
