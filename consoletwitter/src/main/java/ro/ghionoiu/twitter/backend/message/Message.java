/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.twitter.backend.message;

import ro.ghionoiu.twitter.context.output.OutputChannel;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Message {
    private final TimeDifferenceFormater timeDifferenceFormater;
    private final long creationTime;
    private final String content;

    public Message(long creationTime, String content) {
        this(new TimeDifferenceFormater(), creationTime, content);
    }
    
    protected Message(TimeDifferenceFormater timeDifferenceFormater,
            long creationTime, String content) {
        this.timeDifferenceFormater = timeDifferenceFormater;
        this.creationTime = creationTime;
        this.content = content;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public String getContent() {
        return content;
    }
    
    //~~~~~~ Equals
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.creationTime ^ (this.creationTime >>> 32));
        hash = 43 * hash + (this.content != null ? this.content.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        final Message other = (Message) obj;
        if (this.creationTime != other.creationTime) {
            return false;
        }
        if ((this.content == null) ? (other.content != null) : !this.content.equals(other.content)) {
            return false;
        }
        return true;
    }

    //~~~~~~ Displaying message
    
    @Override
    public String toString() {
        return "Message{" + "creationTime=" + creationTime + ", content=" + content + '}';
    }
    
    public void displayTo(OutputChannel outputChannel, long currentTime) {
        String timeDifference = timeDifferenceFormater
                .formatTimeDifference(currentTime - creationTime);
        outputChannel.writeMessage(content+" "+timeDifference+"\n");
    }
}
