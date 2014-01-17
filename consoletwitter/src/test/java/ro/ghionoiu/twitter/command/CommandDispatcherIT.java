/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.command;

import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import ro.ghionoiu.twitter.channels.OutputChannel;
import ro.ghionoiu.twitter.command.handlers.FollowCommandHandler;
import ro.ghionoiu.twitter.command.handlers.PostCommandHandler;
import ro.ghionoiu.twitter.command.handlers.ReadCommandHandler;
import ro.ghionoiu.twitter.command.handlers.WallCommandHandler;


/**
 * Integration test to ensure correct handling
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class CommandDispatcherIT {
    private CommandDispatcher instance;
    private Map<Class, CommandHandler> spyMap;
    
    @Before
    public void setUp() {
        instance = new CommandDispatcher();
        instance.setOutputChannel(mock(OutputChannel.class));
        spyMap = createSpyMapFor(instance);
    }
    
    @Test
    public void wall_command() {
        assertThatCommand("Alice wall", isProcessedBy(WallCommandHandler.class));
    }
    
    @Test
    public void post_command() {
        assertThatCommand("Alice -> Hello!", isProcessedBy(PostCommandHandler.class));
    }
    
    @Test
    public void follow_command() {
        assertThatCommand("Alice follows Bob", isProcessedBy(FollowCommandHandler.class));
    }
    
    @Test
    public void read_command() {
        assertThatCommand("Alice", isProcessedBy(ReadCommandHandler.class));
    }
    
    //~~~~~~~~ test helpers
    
    private static Map<Class, CommandHandler> 
            createSpyMapFor(CommandDispatcher commandDispatcher) {
        Map<Class, CommandHandler> spyMap = new HashMap<Class, CommandHandler>();
        CommandHandler[] handlers = commandDispatcher.getCommandHandlers();
        CommandHandler[] spyHandlers = new CommandHandler[handlers.length];
        for (int i = 0; i < spyHandlers.length; i++) {
            CommandHandler normalHandler = handlers[i];
            CommandHandler spyHandler = spy(normalHandler);
            spyHandlers[i] = spyHandler;
            spyMap.put(normalHandler.getClass(), spyHandler);
        }
        
        //Replace original handlers with spies
        commandDispatcher.setCommandHandlers(spyHandlers);
        
        return spyMap;
    }
    
    private Class isProcessedBy(Class expectedClass) {
        return expectedClass;
    }
    
    private void assertThatCommand(String command, Class expectedClass) {
        //Run
        instance.processCommand(command);
        
        //Assert
        if (spyMap.containsKey(expectedClass)) {
            CommandHandler spyHandler = spyMap.get(expectedClass);
            verify(spyHandler).processCommand(any(String.class));
        } else {
            fail("Handlers are not configured corectly !");
        }
    }
}
