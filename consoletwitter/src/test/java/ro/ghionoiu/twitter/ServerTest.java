/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import java.util.Arrays;
import ro.ghionoiu.twitter.channels.InputChannel;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ServerTest {
    private static final String SOME_COMMAND = "command";
    private static final String EOF = (String) null;
    
    @Test
    public void reads_lines_from_input_channel() {
        InputChannel preparedInputChannel = mockInputChannel(EOF);
        
        startServerUsing(preparedInputChannel);
        
        verify(preparedInputChannel,times(1)).readLine();
    }
    
    
    @Test
    public void exits_when_receives_eof_input() {
        InputChannel preparedInputChannel = mockInputChannel(SOME_COMMAND, EOF);
        
        startServerUsing(preparedInputChannel);
        
        verify(preparedInputChannel,times(2)).readLine();
    }
    
    @Test
    public void forwards_read_lines_to_engine() {
        InputChannel preparedInputChannel = mockInputChannel(SOME_COMMAND, EOF);
        Engine engine = mock(Engine.class);
        
        startServerUsing(preparedInputChannel, engine);
        
        verify(engine).processCommand(SOME_COMMAND);
    }
    
    //~~~~~~~~~~ Private helpers

    private InputChannel mockInputChannel(String ... inputs) {
        InputChannel mockInputChannel = mock(InputChannel.class);
        String firstInput = inputs[0];
        String[] restOfInputs = Arrays.copyOfRange(inputs, 1, inputs.length);
        when(mockInputChannel.readLine())
                .thenReturn(firstInput, restOfInputs);
        return mockInputChannel;
    }

    private void startServerUsing(InputChannel mockInputChannel) {
        startServerUsing(mockInputChannel, mock(Engine.class));
    }
    
    private void startServerUsing(InputChannel mockInputChannel, Engine engine) {
        Server consoleTwitter = new Server();
        consoleTwitter.setInputChannnel(mockInputChannel);
        consoleTwitter.setEngine(engine);
        consoleTwitter.start();
    }
}