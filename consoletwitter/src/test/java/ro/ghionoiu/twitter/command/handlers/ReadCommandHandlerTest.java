/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command.handlers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import ro.ghionoiu.twitter.command.CommandHandler;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ReadCommandHandlerTest {
    private CommandHandler instance;
    
    @Before
    public void setUp() {
        instance = new ReadCommandHandler();
    }
    
    @Test
    public void can_handle_any_command() {
        
        boolean canHandle = instance.canHandle("Alice");
        
        assertThat(canHandle, is(true));
    }
}