/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.context;

import ro.ghionoiu.twitter.channels.InputChannel;
import ro.ghionoiu.twitter.channels.OutputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ApplicationContext {
    private InputChannel inputChannnel;
    private OutputChannel outputChannel;

    public ApplicationContext(
            InputChannel inputChannnel, 
            OutputChannel outputChannel) {
        this.inputChannnel = inputChannnel;
        this.outputChannel = outputChannel;
    }

    public InputChannel getInputChannnel() {
        return inputChannnel;
    }

    public OutputChannel getOutputChannel() {
        return outputChannel;
    }
}
