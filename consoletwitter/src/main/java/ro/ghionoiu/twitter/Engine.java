/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import com.sun.corba.se.impl.activation.CommandHandler;
import ro.ghionoiu.twitter.channels.OutputChannel;
import ro.ghionoiu.twitter.channels.output.SystemConsoleOutput;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Engine {
    private OutputChannel outputChannel;

    //~~~~~~~ Construct

    public Engine() {
        outputChannel = new SystemConsoleOutput();
    }

    public void setOutputChannel(OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
    }
    
    //~~~~~~~ Run
    
    public void processCommand(String command) {
        
//        detect -> build -> execute
//        DetectTypeOfCommand -> Command -> execute
        
        outputChannel.writeMessage(command);
    }
}
