/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.unsorted;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Matcher;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class MessageHandlingTest {
    public static final String KEYWORD_ARROW = "->";
    public static final String KEYWORD_FOLLOWS = "follows";
    public static final String KEYWORD_WALL = "wall";
    
    @Test
    public void a_message_that_contains_keyword_arrow_should_be_handled_as_post() {
        assertThatHandleFor("Alice -> Hello !", is(CommandHandler.POST));
    }
    
    @Test
    public void a_message_that_contains_keyword_follows_should_be_handled_as_follow() {
        assertThatHandleFor("Alice follows Bob", is(CommandHandler.FOLLOW));
    }
    
    @Test
    public void a_message_that_contains_keyword_wall_should_be_handled_as_wall() {
        assertThatHandleFor("Alice wall", is(CommandHandler.WALL));
    }
    
    @Test
    public void a_message_that_contains_no_keywords_should_be_handled_as_read() {
        assertThatHandleFor("Alice", is(CommandHandler.READ));
    }
    
    //~~~~~~~~ Production methods
    
    private static enum CommandHandler {
        POST,
        FOLLOW,
        WALL,
        READ;
    }
    
    private CommandHandler getCommandType(String line) {
        CommandHandler type = CommandHandler.READ;
        
        if(line.contains(KEYWORD_ARROW)) {
            type = CommandHandler.POST;
        }
        if(line.contains(KEYWORD_FOLLOWS)) {
            type = CommandHandler.FOLLOW;
        }
        if(line.contains(KEYWORD_WALL)) {
            type = CommandHandler.WALL;
        }
        
        return type;
    }
    
    //~~~~~~~~~ Private assertions
    
    private <T> void assertThatHandleFor(String line, Matcher<CommandHandler> matcher) {
        CommandHandler type = getCommandType(line);
        assertThat(type, matcher);
    }
}
