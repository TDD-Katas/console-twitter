/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter;

import ro.ghionoiu.twitter.channels.OutputChannel;
import ro.ghionoiu.twitter.channels.output.SystemConsoleOutput;
import ro.ghionoiu.twitter.commands.CommandHandler;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Engine {
    private OutputChannel outputChannel;
    private CommandHandler[] commandHandlers;

    //~~~~~~~ Construct

    public Engine() {
        outputChannel = new SystemConsoleOutput();
    }

    public void setOutputChannel(OutputChannel outputChannel) {
        this.outputChannel = outputChannel;
    }

    public void setCommandHandlers(CommandHandler[] commandHandlers) {
        this.commandHandlers = commandHandlers;
    }
    
    //~~~~~~~ Run
    
    public void processCommand(String command) {
        
//        detect -> build -> execute
//        DetectTypeOfCommand -> Command -> execute
        
        outputChannel.writeMessage(command);
    }
}
