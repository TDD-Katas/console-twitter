/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.context.input;

import ro.ghionoiu.twitter.context.input.ArrayBasedInputChannel;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ArrayBasedInputChannelTest {
    public static final String INPUT_1 = "one";
    public static final String INPUT_2 = "two";
    
    
    @Test
    public void answers_with_inputs_from_prepared_array() {
        ArrayBasedInputChannel instance = new ArrayBasedInputChannel();
        instance.setInputs(INPUT_1,INPUT_2);
        
        assertThat(instance.readLine(), is(INPUT_1));
        assertThat(instance.readLine(), is(INPUT_2));
        assertThat(instance.readLine(), nullValue());
    }
}