package ro.ghionoiu.twitter;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class ConsoleTwitterFixture {
    

    /**
     * Resets all the variables to the empty state
     */
    public void reset() {
    }
    
    /**
     * Simulate a console input and return output
     * @param time
     * @param line
     * @return 
     */
    public String submitCommand(String time, String line) {
        return "";
    }
}
