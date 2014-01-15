package ro.ghionoiu.twitter;

import java.util.Arrays;
import java.util.Iterator;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ro.ghionoiu.twitter.channels.InputChannel;
import ro.ghionoiu.twitter.channels.OutputChannel;

@RunWith(ConcordionRunner.class)
public class ConsoleTwitterFixture {
    private Engine engine;
    private StorageOutputChannel outputChannel;
    
    class ArrayBasedInputChannel implements InputChannel {
        private Iterator<String> inputIterator;

        public ArrayBasedInputChannel(String ... inputs) {
            inputIterator = Arrays.asList(inputs).iterator();
        }
        
        @Override
        public String readCommand() {
            if (inputIterator.hasNext()) {
                return inputIterator.next();
            } else {
                return null;
            }
        }
    }
    
    class StorageOutputChannel implements OutputChannel {
        StringBuilder outputBuffer;

        public void reset() {
            outputBuffer = new StringBuilder();
        }
        
        @Override
        public void writeMessage(String message) {
            outputBuffer.append(message);
        }
        
        
        
        public String getOutput() {
            return outputBuffer.toString();
        }
        
    }
    
    /**
     * Resets all the variables to the empty state
     */
    public void reset() {
        outputChannel = new StorageOutputChannel();
        engine = new Engine(outputChannel);
    }
    
    /**
     * Simulate a console input and return output
     * @param time
     * @param command
     * @return 
     */
    public String submitCommand(String time, String command) {
        InputChannel inputChannel = new ArrayBasedInputChannel(command);
        outputChannel.reset();
        Server server = new Server(inputChannel, engine);
        
        //Start server
        server.start();
        
        return outputChannel.getOutput();
    }
}
