/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command.handlers;

import ro.ghionoiu.twitter.command.CommandHandler;
import ro.ghionoiu.twitter.backend.Backend;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ReadCommandHandler implements CommandHandler {
    private Backend backend;

    public ReadCommandHandler(Backend backend) {
        this.backend = backend;
    }
    
    @Override
    public boolean canHandle(String command) {
        return true;
    }

    @Override
    public void processCommand(String command) {
        String username = command.trim();
        backend.displayTimelineFor(username);
    }
}
