/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import java.util.Collections;
import java.util.Comparator;
import ro.ghionoiu.twitter.backend.message.Message;
import java.util.LinkedList;
import ro.ghionoiu.twitter.context.output.OutputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Timeline extends LinkedList<Message>{

    
    
    public void displayAsOwnTo(OutputChannel outputChannel, long currentTime) {
        sortByCreationDate();
        for (Message message : this) {
            message.displayAsOwnTo(outputChannel, currentTime);
        }
    }
    
    public void displayAsWallTo(OutputChannel outputChannel, long currentTime) {
        sortByCreationDate();
        for (Message message : this) {
            message.displayAsWallTo(outputChannel, currentTime);
        }
    }

    //~~~~~~~ Helpers
    
    private void sortByCreationDate() {
        Collections.sort(this, new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                //Reverse sort
                return (int)(o2.getCreationTime() - o1.getCreationTime());
            }
        });
    }
}
