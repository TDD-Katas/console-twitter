/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command.handlers;

import ro.ghionoiu.twitter.command.CommandHandler;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class PostCommandHandler implements CommandHandler {

    @Override
    public boolean canHandle(String command) {
        boolean canHandle = false;
        if(command.contains("->")) {
            canHandle = true;
        }
        
        return canHandle;
    }

    @Override
    public void processCommand(String command) {
    }
}
