/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.context.input;

import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ArrayBasedInputChannel implements InputChannel {
    private Iterator<String> inputIterator;

    public ArrayBasedInputChannel() {
        inputIterator = Arrays.asList("").iterator();
    }

    public void setInputs(String... inputs) {
        inputIterator = Arrays.asList(inputs).iterator();
    }
    
    @Override
    public String readLine() {
        if (inputIterator.hasNext()) {
            return inputIterator.next();
        } else {
            return null;
        }
    }
}
