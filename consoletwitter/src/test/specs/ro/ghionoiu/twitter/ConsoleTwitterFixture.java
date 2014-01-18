package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.command.CommandDispatcher;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ro.ghionoiu.twitter.channels.InputChannel;
import ro.ghionoiu.twitter.channels.input.ArrayBasedInputChannel;
import ro.ghionoiu.twitter.channels.output.StorageOutputChannel;
import ro.ghionoiu.twitter.context.ApplicationContext;

@RunWith(ConcordionRunner.class)
public class ConsoleTwitterFixture {
    private StorageOutputChannel outputChannel;
    
    /**
     * Resets all the variables to the empty state
     */
    public void reset() {
        outputChannel = new StorageOutputChannel();
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
        ApplicationContext applicationContext = 
                new ApplicationContext(inputChannel, outputChannel);
        Server server = new Server(applicationContext);
        
        //Start server
        server.start();
        
        return outputChannel.getStoredOutput();
    }
}
