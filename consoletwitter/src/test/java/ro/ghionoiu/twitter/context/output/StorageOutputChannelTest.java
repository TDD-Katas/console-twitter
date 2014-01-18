/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.context.output;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class StorageOutputChannelTest {
    private static final String SOME_MESSAGE = "message";
    private static final String EMPTY = "";
    public static final String NEWLINE = "\n";
    
    @Test
    public void will_store_received_messages() {
        StorageOutputChannel instance = new StorageOutputChannel();
        
        instance.writeMessage(SOME_MESSAGE);
        
        assertThat(instance.getStoredOutput(), is(SOME_MESSAGE+NEWLINE));
    }
    
    @Test
    public void reset_wil_clear_the_storage() {
        StorageOutputChannel instance = new StorageOutputChannel();
        instance.writeMessage(SOME_MESSAGE);
        
        instance.reset();
        
        assertThat(instance.getStoredOutput(), is(EMPTY));
    }
}