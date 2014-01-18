/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend.message;

import java.util.concurrent.TimeUnit;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class TimeDifferenceFormater {
    public static final long ONE_MINUTE = TimeUnit.MINUTES.toMillis(1);

    public String formatTimeDifference(long timeDifference) {
        long displayedDifference;
        String timeUnit;
        if (timeDifference < ONE_MINUTE) {
            displayedDifference = TimeUnit.MILLISECONDS.toSeconds(timeDifference);
            timeUnit = "seconds";
        } else {
            displayedDifference = TimeUnit.MILLISECONDS.toMinutes(timeDifference);
            timeUnit = "minutes";
        }
        
        return "("+displayedDifference+" "+timeUnit+" ago)";
    }
    
}
