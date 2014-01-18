/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.context.time;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ManualClock implements Clock {
    private long currentTimeMillis;

    public ManualClock() {
        this.currentTimeMillis = 0;
    }

    public void setCurrentTimeMillis(long currentTimeMillis) {
        this.currentTimeMillis = currentTimeMillis;
    }
    
    public void setCurrentTimeSeconds(long currentTimeSeconds) {
        setCurrentTimeMillis(currentTimeSeconds*1000);
    }
    
    @Override
    public long currentTimeMillis() {
        return currentTimeMillis;
    }
}
