/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command.handlers;

import ro.ghionoiu.twitter.backend.Backend;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Before;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class PostCommandHandlerTest {
    private Backend backend;
    private PostCommandHandler instance;
    
    @Before
    public void setUp() {
        backend = mock(Backend.class);
        instance = new PostCommandHandler(backend);
    }
    
    @Test
    public void can_handle_commands_that_contain_arrow() {
        
        boolean canHandle = instance.canHandle("Alice -> Hello!");
        
        assertThat(canHandle, is(true));
    }
    
    @Test
    public void cannot_handle_commands_that_do_not_contain_arrow() {
        
        boolean canHandle = instance.canHandle("Alice");
        
        assertThat(canHandle, is(false));
    }
    
    @Test
    public void asks_backend_to_store_message_for_user() {
        String command = "Alice -> Hello!";
        
        instance.processCommand(command);
        
        verify(backend).storeMessageForUser("Alice", "Hello!");
    }
}