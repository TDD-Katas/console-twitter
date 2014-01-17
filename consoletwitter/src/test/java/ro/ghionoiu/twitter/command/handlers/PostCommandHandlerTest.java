/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command.handlers;

import ro.ghionoiu.twitter.storage.StorageSystem;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import org.junit.Before;
import ro.ghionoiu.twitter.command.CommandHandler;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class PostCommandHandlerTest {
    private PostCommandHandler instance;
    
    @Before
    public void setUp() {
        instance = new PostCommandHandler();
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
    public void forwards_username_and_message_to_the_storage_system() {
        String command = "Alice -> Hello!";
        StorageSystem storage = mock(StorageSystem.class);
        instance.setStorageSystem(storage);
        
        instance.processCommand(command);
        
        verify(storage).storeMessageForUser("Alice", "Hello!");
    }
}