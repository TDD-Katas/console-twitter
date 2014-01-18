/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend.message;

import java.util.concurrent.TimeUnit;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Before;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class TimeDifferenceFormaterTest {
    private TimeDifferenceFormater instance;
    
    @Before
    public void setUp() {
        instance = new TimeDifferenceFormater();
    }
    
    @Test
    public void if_difference_is_less_than_a_minute_should_display_seconds() {
        long timeDifference = TimeUnit.SECONDS.toMillis(1);  
        
        String result = instance.formatTimeDifference(timeDifference);
        
        assertThat(result, is("(1 seconds ago)"));
    }
    
    @Test
    public void if_difference_is_grater_than_a_minute_should_display_minutes() {
        long timeDifference = TimeUnit.MINUTES.toMillis(3);  
        
        String result = instance.formatTimeDifference(timeDifference);
        
        assertThat(result, is("(3 minutes ago)"));
    }
}