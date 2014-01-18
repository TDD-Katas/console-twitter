/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import java.util.LinkedList;
import ro.ghionoiu.twitter.channels.OutputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Timeline extends LinkedList<String>{

    public void displayTo(OutputChannel outputChannel) {
        for (String message : this) {
            outputChannel.writeMessage(message);
        }
    }
}
