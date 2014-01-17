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
import ro.ghionoiu.twitter.command.CommandHandler;
import ro.ghionoiu.twitter.backend.Backend;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ReadCommandHandlerTest {
    private Backend backend;
    private CommandHandler instance;
    
    @Before
    public void setUp() {
        backend = mock(Backend.class);
        instance = new ReadCommandHandler(backend);
    }
    
    @Test
    public void can_handle_any_command() {
        
        boolean canHandle = instance.canHandle("Alice");
        
        assertThat(canHandle, is(true));
    }
    
    @Test
    public void asks_backend_to_display_timeline_of_user() {
        String command = "Alice";
        
        instance.processCommand(command);
        
        verify(backend).displayTimelineFor(command);
    }
}