/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

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
        String line = "Alice "+KEYWORD_ARROW+" ole";
        
        String type = "";
        if(line.contains(KEYWORD_ARROW)) {
            type = COMMAND_POST;
        }
        
        assertThat(type, is(COMMAND_POST));
    }
    
    @Test
    public void a_line_that_contains_keyword_follows_is_a_follow_command() {
        String line = "Alice "+KEYWORD_FOLLOWS+" Bob";
        
        String type = "";
        if(line.contains("follows")) {
            type = "follow";
        }
        
        assertThat(type, is(COMMAND_FOLLOW));
    }
    
    @Test
    public void a_line_that_contains_keyword_wall_is_a_wall_command() {
        String line = "Alice "+KEYWORD_WALL;
        
        String type = "";
        if(line.contains(KEYWORD_WALL)) {
            type = COMMAND_WALL;
        }
        
        assertThat(type, is(COMMAND_WALL));
    }
    
    @Test
    public void a_line_that_contains_no_keywords_a_read_command() {
        String line = "Alice";
        
        String type = "";
        if(!line.contains(KEYWORD_ARROW) && 
            !line.contains(KEYWORD_FOLLOWS) && 
            !line.contains(KEYWORD_WALL)) {
            type = COMMAND_READ;
        }
        
        assertThat(type, is(COMMAND_READ));
    }
}
