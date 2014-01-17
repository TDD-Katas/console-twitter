/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.Matcher;
import static org.junit.Assert.assertThat;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class DummyTest {
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
    public void a_line_that_contains_no_keywords_a_read_command() {
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
        CommandType command = CommandType.READ;
        
        if(line.contains(KEYWORD_ARROW)) {
            command = CommandType.POST;
        }
        if(line.contains(KEYWORD_FOLLOWS)) {
            command = CommandType.FOLLOW;
        }
        if(line.contains(KEYWORD_WALL)) {
            command = CommandType.WALL;
        }
        
        return command;
    }
    
    //~~~~~~~~~ Private assertions
    
    private <T> void assertThatCommandTypeFor(String line, Matcher<CommandType> matcher) {
        CommandType commandType = getCommandType(line);
        assertThat(commandType, matcher);
    }
}
