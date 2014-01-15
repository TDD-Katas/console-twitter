/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ConsoleTwitter {
    public static void main(String[] args) {
        TwitterServer consoleTwitter = new TwitterServer();
        consoleTwitter.start();
    }
}
