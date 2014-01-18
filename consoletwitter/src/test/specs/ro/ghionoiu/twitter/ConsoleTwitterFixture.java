package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.command.CommandDispatcher;
import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;
import ro.ghionoiu.twitter.channels.input.ArrayBasedInputChannel;
import ro.ghionoiu.twitter.channels.output.StorageOutputChannel;
import ro.ghionoiu.twitter.context.ApplicationContext;

@RunWith(ConcordionRunner.class)
public class ConsoleTwitterFixture {
    private ArrayBasedInputChannel inputChannel;
    private StorageOutputChannel outputChannel;
    private ApplicationContext applicationContext;
    private CommandDispatcher commandDispatcher;
    
    /**
     * Resets all the variables to the empty state
     */
    public void reset() {
        inputChannel = new ArrayBasedInputChannel();
        outputChannel = new StorageOutputChannel();
        applicationContext = new ApplicationContext(inputChannel, outputChannel);
        commandDispatcher = new CommandDispatcher(applicationContext);
    }
    
    /**
     * Simulate a console input and return output
     * @param time
     * @param command
     * @return 
     */
    public String submitCommand(String time, String command) {
        inputChannel.setInputs(command);
        outputChannel.reset();
        Server server = new Server(applicationContext,commandDispatcher);
        
        //Start server
        server.start();
        
        return outputChannel.getStoredOutput();
    }
}
