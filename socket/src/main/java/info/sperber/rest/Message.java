package info.sperber.rest;

/**
 * Created by Dodo on 09.03.2017.
 */
public class Message {
    public String sender;
    public String content;

    public Message() {
    }

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
    }
}
