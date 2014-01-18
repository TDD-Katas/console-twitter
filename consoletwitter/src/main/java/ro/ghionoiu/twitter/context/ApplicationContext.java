/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.context;

import ro.ghionoiu.twitter.context.time.Clock;
import ro.ghionoiu.twitter.context.input.InputChannel;
import ro.ghionoiu.twitter.context.output.OutputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ApplicationContext {
    private InputChannel inputChannnel;
    private OutputChannel outputChannel;
    private Clock clock;

    public ApplicationContext(
            InputChannel inputChannnel, 
            OutputChannel outputChannel,
            Clock clock) {
        this.inputChannnel = inputChannnel;
        this.outputChannel = outputChannel;
        this.clock = clock;
    }

    public InputChannel getInputChannnel() {
        return inputChannnel;
    }

    public OutputChannel getOutputChannel() {
        return outputChannel;
    }

    public Clock getClock() {
        return clock;
    }
}
