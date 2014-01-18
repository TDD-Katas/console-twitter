/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command.handlers;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import ro.ghionoiu.twitter.backend.Backend;
import ro.ghionoiu.twitter.command.CommandHandler;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FollowCommandHandlerTest {
    private Backend backend;
    private CommandHandler instance;
    
    @Before
    public void setUp() {
        backend = mock(Backend.class);
        instance = new FollowCommandHandler(backend);
    }
    
    @Test
    public void can_handle_commands_that_contain_follows() {
        
        boolean canHandle = instance.canHandle("Alice follows Bob");
        
        assertThat(canHandle, is(true));
    }
    
    @Test
    public void cannot_handle_commands_that_do_not_contain_follows() {
        
        boolean canHandle = instance.canHandle("Alice");
        
        assertThat(canHandle, is(false));
    }
    
    @Test
    public void notifies_backend_that_a_user_is_following_the_other() {
        String command = "Alice follows Bob";
        
        instance.processCommand(command);
        
        verify(backend).oneUserFollowsAnother("Alice", "Bob");
    }
}