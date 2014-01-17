package ro.ghionoiu.twitter;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ro.ghionoiu.twitter.channels.InputChannel;
import ro.ghionoiu.twitter.channels.input.ArrayBasedInputChannel;
import ro.ghionoiu.twitter.channels.output.StorageOutputChannel;

@RunWith(ConcordionRunner.class)
public class ConsoleTwitterFixture {
    private CommandDispatcher dispatcher;
    private StorageOutputChannel outputChannel;
    
    /**
     * Resets all the variables to the empty state
     */
    public void reset() {
        outputChannel = new StorageOutputChannel();
        dispatcher = new CommandDispatcher();
        dispatcher.setOutputChannel(outputChannel);
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
        Server server = new Server();
        server.setInputChannnel(inputChannel);
        server.setEngine(dispatcher);
        
        //Start server
        server.start();
        
        return outputChannel.getStoredOutput();
    }
}
