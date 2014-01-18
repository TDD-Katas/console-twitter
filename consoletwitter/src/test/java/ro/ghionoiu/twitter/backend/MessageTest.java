/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class MessageTest {
    
    @Test
    public void equals_should_respect_contract() {
        EqualsVerifier.forClass(Message.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }
}