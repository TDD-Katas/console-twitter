/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.context.time;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class RealClock implements Clock {

    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    
}
