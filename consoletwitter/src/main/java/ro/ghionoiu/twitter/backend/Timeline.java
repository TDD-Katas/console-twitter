/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import ro.ghionoiu.twitter.backend.message.Message;
import java.util.LinkedList;
import ro.ghionoiu.twitter.context.output.OutputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Timeline extends LinkedList<Message>{

    public void displayTo(OutputChannel outputChannel, long currentTime) {
        for (Message message : this) {
            message.displayTo(outputChannel, currentTime);
        }
    }
}
