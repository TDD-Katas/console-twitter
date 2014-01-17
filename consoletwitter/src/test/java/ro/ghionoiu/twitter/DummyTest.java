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


    
    public static final String COMMAND_POST = "post";
    public static final String COMMAND_FOLLOW = "follow";
    public static final String COMMAND_WALL = "typewall";
    public static final String COMMAND_READ = "read";
    
    @Test
    public void a_line_that_contains_keyword_arrow_is_a_post_command() {
        assertThatCommandTypeFor("Alice -> Hello !", is(COMMAND_POST));
    }
    
    @Test
    public void a_line_that_contains_keyword_follows_is_a_follow_command() {
        assertThatCommandTypeFor("Alice follows Bob", is(COMMAND_FOLLOW));
    }
    
    @Test
    public void a_line_that_contains_keyword_wall_is_a_wall_command() {
        assertThatCommandTypeFor("Alice wall", is(COMMAND_WALL));
    }
    
    @Test
    public void a_line_that_contains_no_keywords_a_read_command() {
        assertThatCommandTypeFor("Alice", is(COMMAND_READ));
    }
    
    //~~~~~~~~ Production methods
    
    protected String getCommandType(String line) {
        String command = COMMAND_READ;
        
        if(line.contains(KEYWORD_ARROW)) {
            command = COMMAND_POST;
        }
        if(line.contains(KEYWORD_FOLLOWS)) {
            command = COMMAND_FOLLOW;
        }
        if(line.contains(KEYWORD_WALL)) {
            command = COMMAND_WALL;
        }
        
        return command;
    }
    
    enum CommandType {
        POST,
        FOLLOW,
        WALL,
        READ;
    }
    
    //~~~~~~~~~ Private assertions
    
    private <T> void assertThatCommandTypeFor(String line, Matcher<String> matcher) {
        String commandType = getCommandType(line);
        assertThat(commandType, matcher);
    }
}
