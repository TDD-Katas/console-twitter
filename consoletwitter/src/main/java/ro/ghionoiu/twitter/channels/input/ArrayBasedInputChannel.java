/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.channels.input;

import java.util.Arrays;
import java.util.Iterator;
import ro.ghionoiu.twitter.channels.InputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ArrayBasedInputChannel implements InputChannel {
    private Iterator<String> inputIterator;

    public ArrayBasedInputChannel(String... inputs) {
        inputIterator = Arrays.asList(inputs).iterator();
    }

    @Override
    public String readCommand() {
        if (inputIterator.hasNext()) {
            return inputIterator.next();
        } else {
            return null;
        }
    }
}
