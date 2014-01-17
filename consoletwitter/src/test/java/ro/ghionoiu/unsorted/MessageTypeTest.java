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
public class MessageTypeTest {
    public static final String KEYWORD_ARROW = "->";
    public static final String KEYWORD_FOLLOWS = "follows";
    public static final String KEYWORD_WALL = "wall";
    
    @Test
    public void a_line_that_contains_keyword_arrow_is_a_post_command() {
        assertThatCommandTypeFor("Alice -> Hello !", is(CommandType.POST));
    }
    
    @Test
    public void a_line_that_contains_keyword_follows_is_a_follow_command() {
        assertThatCommandTypeFor("Alice follows Bob", is(CommandType.FOLLOW));
    }
    
    @Test
    public void a_line_that_contains_keyword_wall_is_a_wall_command() {
        assertThatCommandTypeFor("Alice wall", is(CommandType.WALL));
    }
    
    @Test
    public void a_line_that_contains_no_keywords_is_a_read_command() {
        assertThatCommandTypeFor("Alice", is(CommandType.READ));
    }
    
    //~~~~~~~~ Production methods
    
    private static enum CommandType {
        POST,
        FOLLOW,
        WALL,
        READ;
    }
    
    private CommandType getCommandType(String line) {
        CommandType type = CommandType.READ;
        
        if(line.contains(KEYWORD_ARROW)) {
            type = CommandType.POST;
        }
        if(line.contains(KEYWORD_FOLLOWS)) {
            type = CommandType.FOLLOW;
        }
        if(line.contains(KEYWORD_WALL)) {
            type = CommandType.WALL;
        }
        
        return type;
    }
    
    //~~~~~~~~~ Private assertions
    
    private <T> void assertThatCommandTypeFor(String line, Matcher<CommandType> matcher) {
        CommandType type = getCommandType(line);
        assertThat(type, matcher);
    }
}
