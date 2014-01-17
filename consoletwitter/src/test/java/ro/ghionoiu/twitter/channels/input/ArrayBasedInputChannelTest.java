/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.channels.input;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import ro.ghionoiu.twitter.channels.InputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ArrayBasedInputChannelTest {
    public static final String INPUT_1 = "one";
    public static final String INPUT_2 = "two";
    
    
    @Test
    public void answers_with_inputs_from_received_array() {
        InputChannel instance = new ArrayBasedInputChannel(INPUT_1,INPUT_2);
        
        assertThat(instance.readLine(), is(INPUT_1));
        assertThat(instance.readLine(), is(INPUT_2));
        assertThat(instance.readLine(), nullValue());
    }
}